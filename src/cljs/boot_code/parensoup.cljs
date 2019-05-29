(ns boot-code.parensoup
  (:require-macros
    [javelin.core :refer [defc defc= cell=]])
  (:require
   [hoplon.core :refer [link header footer h1 div textarea text br]]
   [javelin.core :refer [cell]]
   [paren-soup.core :as ps]

   [boot-code.ui :as ui]
   [util.html :as h]))

#_(h/inner-html! (.-body js/document) nil)

(def initial-code "(ns code.ui
  (:require [clojure.string :as s]))

(s/includes? \"ab\" \"~\")


(def repl boot-code.repl)

(def result (atom []))

(defn log-results [r]
  (.log js/console (str r))
  (reset! result r))

(repl.read-eval ['(+ 5 9 3) '(list 3 2) '(require 'foo.baz)] log-results)

@result


(partition 2 [:a 1 :b 3])

(into {} [[:a \"a\"] [:b \"b\"]])

(into {} (map vec (partition 2 [:a 1 :b 3])))

(type (eval (list + 3 2)))


(def j boot-code.jobs)

(defn hi [cont]
  (cont \"Hello!\"))

(j.with-blocking hi)

(def h util.html)

(def h hoplon.core)
(def body hoplon.core/body)
(def h1 hoplon.core/h1)
(def p hoplon.core/p)

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

(defn- fib-factory [a b]
     (lazy-seq (cons a (fib-factory b (+ a b)))))
(def fib-seq (fib-factory 0 1))

(take 20 fib-seq)

(-> environment :prod)")


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
              (div))

   :init #(ps/init-all)})


(defn activate [root-container] (reset! root-container ps-editor))

(defn init [root-container] (reset! ui/default-loader #(activate root-container)))

(defonce do-init (partial init ui/root))
