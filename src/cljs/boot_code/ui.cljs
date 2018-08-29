(ns boot-code.ui
  (:require-macros
    [javelin.core :refer [defc defc= cell=]])
  (:require
   [hoplon.core :refer [link header h1 ul li div textarea text p br button]]
   [javelin.core :refer [cell]]

   [boot-code.jobs :as j]))


(def materialize-base "http://cdn.muicss.com/mui-0.9.36")
(def mz-css (str materialize-base "/css/mui.min.css"))
(def mz-js (str materialize-base "/js/mui.min.js"))

;; TODO: Make this work like the map built in codemirror-assets.cljs
(def materialize {:name "Materialize"
                  :minjs (str materialize-base "/js/mui.min.js")
                  :mincss (str materialize-base "/css/mui.min.css")})


(def default-loader (atom #(js/alert js/console "default-loader not overridden")))


;; To replace the main UI, set this cell to your replacement
(defc root
  {:dynamic []

   :body (div :class "all-content"
              (h1 "Fusion Text - The next step in web-based code editing")
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
      (fn? c)  [(c)]
      :else    [c])))


(defc= css (mapcat (partial dynamic :mincss :css) (-> root :dynamic)))
(defc= body (-> root :body))
(defc= js (mapcat (partial dynamic :minjs :js) (-> root :dynamic)))

(defc= page-init (-> root :init))
