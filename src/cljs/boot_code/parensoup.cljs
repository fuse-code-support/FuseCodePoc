(ns boot-code.parensoup
  (:require-macros
    [javelin.core :refer [defc defc= cell=]])
  (:require
   [hoplon.core :refer [link header footer h1 div textarea text br]]
   [javelin.core :refer [cell]]
   [paren-soup.core :as ps]

   [boot-code.ui :as ui]
   [boot-code.rpc :as rpc]))

(def initial-code "(ns code.ui
  (:require [clojure.string :as s]))

(s/includes? \"ab\" \"~\")

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

;(.appendChild (-> js/document .-body) (p \"Hello, world\"))

(name \"foo\")

(def fib-seq
  ((fn fib [a b]
     (lazy-seq (cons a (fib b (+ a b)))))
   0 1))

(take 20 fib-seq)

(-> environment :prod)")


(defn init-later [f]
  (fn []
    (js/setTimeout
     (fn [] (f))
     2000)
    ""))


(def ps-editor
  {:dynamic [{:name "Paren Soup Clojurescript Notebook"

              :css (link :type "text/css"
                         :rel "stylesheet"
                         :href "http://oakes.github.io/paren-soup/paren-soup-light.css")}]

   :body (div :class "all-content"
              (header (text "Header"))
              (div :class "paren-soup"
                   (div :class "instarepl")
                   (div :class "numbers")
                   (div :class "content" :contenteditable "true" (text initial-code)))
              (div)
              (footer (cell= (str (:message rpc/error)))))

   :init (init-later (fn [] (ps/init-all)))})


(defn activate [container] (reset! container ps-editor))
