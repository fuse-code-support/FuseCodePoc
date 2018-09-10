(ns boot-code.jobs
  (:require-macros
   [cljs.core.async.macros :refer [go]]
   [javelin.core :refer [defc defc= cell=]])
  (:require [cljs.core.async :refer [<! put! chan]]))

;; A central place to get in-progress task information for all UIs
(defc current-job-name "")
(defc current-task-number 0)
(defc number-of-tasks 0)


(def job-schema
  {:name string?
   :initial-size number?
   :steps [(-> nil ['continuation-fn])]})


(defn with-blocking
  "Run (f args), supplying all but the last argument of f, for functions where the last argument of f is the function
  to call with f's result.  Automatically calls f, blocks, captures f's results, and returns a sequence containing
  the arguments passed to f's continuation function.  If nil is the result passed to f's continuation function,
  \"<<SUCCESS>>\" is returned instead."
  [f & args]
  (let [pf (if args (apply partial f args) f)
        continuation (chan)]
    (go
      (pf (fn [& result] (put! continuation (or result "<<SUCCESS>>"))))
      (<! continuation))))


(defn- run-job [job]
  (reset! current-job-name (:name job))
  (reset! current-task-number 1)
  (reset! number-of-tasks (:initial-size job))

  (doseq [run-task (:steps job)]
    (with-blocking run-task)
    (swap! current-task-number inc))

  (reset! current-job-name "")
  (reset! current-task-number 0)
  (reset! number-of-tasks 0))


(defonce jobs
  (let [js (chan)]
    (go
      (loop []
        (run-job (<! js))
        (recur)))
    js))


(defn submit
  "Submit a job.  job-name is a String identifying the job in the UI.  step-fns may either be
  a 1-ary function accepting a continuation function or a vector of these functions."
  [job-name step-fns]
  (let [steps (cond
                (fn? step-fns)         [step-fns]
                (sequential? step-fns) step-fns
                :else                  (throw (str "Expected a fn or a vector of fns but found " (type step-fns))))
        job {:name job-name
             :initial-size (count steps)
             :steps steps}]
    (put! jobs job)))
