(ns boot-code.core
  (:require
   [boot-code.handler  :as handler]
   [ring.adapter.jetty :refer [run-jetty]]))


(def server (atom nil))

(defn app [port public-path]
  (run-jetty handler/app {:join? false :port port}))

(defn start-server
  "Start castra server (port 33333)."
  [port public-path]
  (swap! server #(or % (app port public-path))))
