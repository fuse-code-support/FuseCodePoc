(ns boot-code.io
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


;; File path stuff

(defn expand-path [p]
  (let [home (System/getProperty "user.home")]
    (if (str/includes? p "~")
      (str/replace p "~" home)
      p)))

(defn full-path [relative-path] (.getCanonicalPath (io/file relative-path)))

(defn file-details [fileOrName]
  (let [f (if (string? fileOrName) (io/file fileOrName) fileOrName)]
    {:full-name (.getCanonicalPath f)
     :short-name (.getName f)
     :directory (.isDirectory f)
     :hidden (.isHidden f)
     :last-modified-millis (.lastModified f)}))
