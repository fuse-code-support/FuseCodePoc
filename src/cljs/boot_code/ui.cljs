(ns boot-code.ui
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [hoplon.core :refer [link header h1 div textarea text br]]
   [javelin.core :refer [cell]]
   [paren-soup.core :as ps]))


(def materialize-base "//cdn.muicss.com/mui-0.9.36")
(def mz-css (str materialize-base "/css/mui.min.css"))
(def mz-js (str materialize-base "/js/mui.min.js"))

;; TODO: Make this work like the map built in codemirror-assets.cljs
(def materialize {:js (str materialize-base "/js/mui.min.js")
                  :css (str materialize-base "/css/mui.min.css")})

(def initial-code "(ns code.ui)

(def h hoplon.core)
(def body hoplon.core.body)
(def h1 hoplon.core.h1)
(def p hoplon.core.p)

(def project boot-code.rpc.project)
(def files (:files @project))

(first files)
(map #(:full-name %) files)

(def environment {:prod
                  (fn []
                    (body
                      (h1 :class \"foo\" \"Hello page\")))})

(.appendChild (-> js/document .-body) (p \"Hello, world\"))

(name \"foo\")

(-> environment :prod)")


(defn parensoup-init [] (ps/init-all))


(defn init-later [f] (fn []
                       (js/setTimeout
                        (fn [] (f))
                        2000)
                       ""))


(defc environment
  {:prod {:css (link :type "text/css" :rel "stylesheet" :href "http://oakes.github.io/paren-soup/paren-soup-light.css")

          :body (div :class "all-content"
                     (header (text "Header"))
                     (div :class "paren-soup"
                          (div :class "instarepl")
                          (div :class "numbers")
                          (div :class "content" :contenteditable "true" (text initial-code)))
                     (div))

          :page-init (init-later parensoup-init)}})


(defc current :prod)

(defc= css (-> environment current :css))

(defc= body (-> environment current :body))

(defc= page-init (-> environment current :page-init))
