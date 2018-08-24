(ns boot-code.ui
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [hoplon.core :refer [link header h1 div textarea text br button]]
   [javelin.core :refer [cell]]
   [boot-code.parensoup :as ps]))


(def materialize-base "//cdn.muicss.com/mui-0.9.36")
(def mz-css (str materialize-base "/css/mui.min.css"))
(def mz-js (str materialize-base "/js/mui.min.js"))

;; TODO: Make this work like the map built in codemirror-assets.cljs
(def materialize {:minjs (str materialize-base "/js/mui.min.js")
                  :mincss (str materialize-base "/css/mui.min.css")})


(defc root
  {:dynamic []

   :body (div :class "all-content"
              (button :click #(ps/activate root) "Clojurescript Notebook"))

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
