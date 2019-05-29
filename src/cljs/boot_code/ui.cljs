(ns boot-code.ui
  (:require-macros
   [javelin.core :refer [defc defc= cell=]])
  (:require
   [hoplon.core :refer [link header h1 ul li div textarea text p br button]]
   [javelin.core :refer [cell]]

   [boot-code.jobs :as j]
   [util.html :refer [load-scripts!]]))


(def default-loader (atom #(js/alert js/console "default-loader not overridden")))


;; To set the tab title, set this cell
(defc tab-title (text "FuseCode"))

;; To replace the main UI, set this cell to your replacement
(defc root
  {:dynamic []

   :body (div :class "all-content"
              (h1 "FuseCode - Browser-based code editing implemented in itself")
              (p "Initializing, standby...")
              (ul
               (li (text "Loading ~{j/current-job-name}"))
               (li (text "Task #~{j/current-task-number} of ~{j/number-of-tasks}")))
              (p (button :click #(@default-loader) "Clojurescript Notebook")))

   :init (fn [])})


(defn dynamic [first-key second-key element]
  (let [c (or (get element first-key) (get element second-key))]
    (cond
      (nil? c) []
      (fn? c)  [c]
      :else    [#(identity c)])))


(defc= css (->> (-> root :dynamic) (mapcat (partial dynamic :mincss :css)) (map apply)))
(defc= body (-> root :body))

(defc= js (let [job-name (or (-> root :name) "Loading Javascript")
                script-infos (mapcat (partial dynamic :minjs :js) (-> root :dynamic))]
            (load-scripts! job-name script-infos)))
(defc= page-init (-> root :init))
