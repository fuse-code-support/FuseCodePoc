(def task-options
  {:project 'coconutpalm/boot-code
   :version "0.1.1"
   :project-name "boot-code"
   :project-openness :open-source

   :description "A programmers' editor intended to be embedded inside of build tools."

   :scm-url "https://github.com/coconutpalm/boot-code"

   :test-sources "test"
   :test-resources nil})

(set-env!
 :dependencies '[[coconutpalm/boot-boot     "LATEST" :scope "test"]
                 [clojure-watch             "LATEST"]
                 [adzerk/boot-cljs          "2.1.4"]
                 [adzerk/boot-reload        "0.5.2"]
                 [compojure                 "1.6.1"]
                 [hoplon/castra             "3.0.0-alpha7"]
                 [hoplon/hoplon             "7.2.0"]

                 [cljsjs/pouchdb            "6.0.4-0"]
                 [cljsjs/codemirror         "5.24.0-1"]
                 [eval-soup                 "1.4.3"]
                 [paren-soup                "2.12.3"]

                 [org.clojure/clojurescript "1.10.238"]
                 [adzerk/boot-cljs-repl     "0.3.3"]
                 [ring/ring-defaults        "0.3.2"]

                 [cider/piggieback          "0.3.6"  :scope "test"]
                 [weasel                    "0.7.0"  :scope "test"]
                 [org.clojure/tools.nrepl   "0.2.13" :scope "test"]]

 :resource-paths #{"resources" "src/clj"}
 :source-paths   #{"src/cljs" "src/hl"})


(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[adzerk.boot-reload    :refer [reload]]
  '[hoplon.boot-hoplon    :refer [hoplon prerender]]
  '[pandeiro.boot-http    :refer [serve]])


(deftask web-dev
  "Build boot-code for local development."
  []
  (comp (sift :add-jar {'cljsjs/codemirror #"cljsjs/codemirror/development/codemirror.css"})
     (sift :move {#"cljsjs/codemirror/development/codemirror.css" "vendor/codemirror/codemirror.css"})
     (serve
      :port    7000
      :handler 'boot-code.handler/app
      :reload  true)
     (watch)
     (speak)
     (hoplon)
     (reload)
     (cljs)))


(deftask prod
  "Build boot-code for production deployment."
  []
  (comp (sift :add-jar {'cljsjs/codemirror #"cljsjs/codemirror/development/codemirror.css"})
     (sift :move {#"cljsjs/codemirror/development/codemirror.css" "vendor/codemirror/codemirror.css"})
     (hoplon)
     (cljs :optimizations :advanced)
     (prerender)))


(deftask make-war
  "Build a war for deployment"
  []
  (comp (sift :add-jar {'cljsjs/codemirror #"cljsjs/codemirror/development/codemirror.css"})
     (sift :move {#"cljsjs/codemirror/development/codemirror.css" "vendor/codemirror/codemirror.css"})
     (hoplon)
     (cljs :optimizations :advanced)
     (uber :as-jars true)
     (web :serve 'sbt_hoplon.handler/app)
     (war)
     (target :dir #{"target"})))
