(ns boot-code.repl
  (:require [clojure.java.io :as io]
            [boot-code.api :as api]
            [boot-code.io :refer [file-details]]))


(defn fetch-ns [path]
  (let [full-path (str (:full-name (file-details ".")) "/src/cljs/" path)]
    (println (str "[FETCH] " full-path))
    (when-let [content (slurp full-path)]
      {:status 200
       :headers {"Content-Type" "text/plain"}
       :body content})))
