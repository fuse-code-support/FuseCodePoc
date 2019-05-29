(ns fusecode.workbench.ui
  (:require-macros
    [javelin.core :refer [defc defc= cell=]])
  (:require
   [clojure.string :refer [split]]
   [hoplon.core :refer [link header footer h1 div textarea text br script nav a ul li i]]
   [javelin.core :refer [cell]]

   [boot-code.ui :as ui]
   [boot-code.jobs :as job]
   [util.html :as h]
   [util.codemirror :as cm]
   [util.codemirror-assets :as cm-assets]))


(def materialize-base "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0")
(def mz-css (str materialize-base "/css/materialize.min.css"))
(def mz-js (str materialize-base "/js/materialize.min.js"))

(defc logo-text "☢")

;; see http://colormind.io/bootstrap/ and http://coolors.co
;; Original blues colors #FDFDEC 054A91 3E7CB1 81A4CD 61615A
;; Original coffee colors E3D8D0 2D999A 8D5E55 738074 342D27

(defc palettes
  {:teals {:light "white" :light-accent "black" :brand "teal darken-4" :dark-accent "grey darken-2" :dark "teal lighten-4"}
   :blues {:light "lime lighten-5" :light-accent "indigo darken-1" :brand "indigo lighten-1" :dark-accent "indigo lighten-4" :dark "lime darken-4"}
   :coffee {:light "brown lighten-5" :light-accent "cyan darken-3" :brand "brown lighten-1" :dark-accent "teal lighten-2" :dark "teal darken-4"}})


(defc palette :coffee)
(defc theme-style :light)               ; :light or :dark

(defc= colors (get palettes palette (:coffee palettes)))

(defc= light (:light colors))
(defc= light-accent (:light-accent colors))
(defc= brand (:brand colors))
(defc= dark-accent (:dark-accent colors))
(defc= dark (:dark colors))

(defn txt [style]
  (let [[sty mod] (split style " ")]
    (if mod
      (str sty "-text text-" mod)
      (str sty "-text"))))

(defc= light-theme? (= theme-style :light))

(defc= background (if light-theme? light dark))
(defc= background-text (txt (if light-theme? dark light)))
(defc= background-text-callout (txt dark-accent))

(defc= inverse (if light-theme? dark light))
(defc= inverse-text (txt (if light-theme? light dark)))
(defc= inverse-text-callout (txt light-accent))


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
              :css (link :href "https://fonts.googleapis.com/css?family=Noto+Mono+JP|Noto+Sans+JP|Noto+Serif+JP|IBM+Plex+Mono|Roboto+Mono" :rel "stylesheet" :type "text/css")}
             {:name "Fira Code font"
              :css (link :href "https://cdn.rawgit.com/tonsky/FiraCode/1.205/distr/fira_code.css" :rel "stylesheet" :type "text/css")}]

   :body [(div :id "outer"
               (div :id "inner_fixed" :class "navbar-fixed"
                    (nav :class (cell= (str inverse " z-depth-2")) :role "navigation"
                         (div :class "nav-wrapper"
                              (div :class "row"
                                   (div :class "col s12 padding-left: 1em;"
                                        (a :id "logo-container" :href "#" :class "right brand-logo" (text logo-text))
                                        (ul :class "hide-on-med-and-down"
                                            (li :class "active" (a :class (cell= inverse-text) :href "#" "FuseCode"))
                                            (li (a :class (cell= inverse-text) :href "#" "Untitled Workspace")))

                                        (ul :id "nav-mobile" :class "sidenav" (li (a :class (cell= inverse-text) :href "#" "Navbar Link")))
                                        (a :href "#" :data-target "nav-mobile" :class "sidenav-trigger" (i :class "material-icons" "menu")))))))
               (div :id "inner_variable"))]

   :init #(let [editor-area (h/by-id "inner_variable")]
            (cm/CodeMirror editor-area cm/defaults))})


(defn activate [root-container] (reset! root-container window-content))
