(ns fusion.workbench.ui
  (:require-macros
    [javelin.core :refer [defc defc= cell=]])
  (:require
   [hoplon.core :refer [link header footer h1 div textarea text br]]
   [javelin.core :refer [cell]]

   [boot-code.ui :as ui]
   [boot-code.jobs :as job]
   [util.html :as h]))


(def materialize-base "http://cdn.muicss.com/mui-0.9.36")
(def mz-css (str materialize-base "/css/mui.min.css"))
(def mz-js (str materialize-base "/js/mui.min.js"))


(defc main-window-content (div))

(defc= task-info
  (if (> job/number-of-tasks 0)
    (text "(~{job/current-job-name} ~{job/current-task-number}/~{job/number-of-tasks})")
    ""))



(def window-content
  {:dynamic [{:name "Materialize JS"
              :css (link :type "text/css" :rel "stylesheet" :href mz-css :media "screen,projection")
              :minjs (script :src mz-js)}
             {:name "Material icons"
              :css (link :href "https://fonts.googleapis.com/icon?family=Material+Icons" :rel "stylesheet" :type "text/css")}
             {:name "Google fonts"
              :css (link :href "https://fonts.googleapis.com/css?family=Roboto|Inconsolata" :rel "stylesheet" :type "text/css")}
             {:name "Fira Code font"
              :css (link :href "https://cdn.rawgit.com/tonsky/FiraCode/1.205/distr/fira_code.css" :rel "stylesheet" :type "text/css")}]

   :body (div :class "all-content"
              (header (text "FusionText ~{task-info}"))
              (cell= main-window-content)
              (div :class "hiddendiv common")
              (div :class "drag-target" :data-sidenav "nav-mobile" :style "left:0px ; touch-action:pan-y; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0,0,0,0;"))

   :init (fn [])})


(defn activate [] (reset! ui/root window-content))
