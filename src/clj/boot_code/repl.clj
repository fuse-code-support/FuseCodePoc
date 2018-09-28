(ns boot-code.repl
  (:require [clojure.java.io :as io]
            [clojure.java.classpath :as cp]
            [boot-code.api :as api]
            [boot-code.io :refer [file-details]])
  (:import [java.io File]
           [java.util.jar JarFile JarEntry]))


;; TODO: In a Boot plugin I don't think these are necessary either.  But maybe for Scala?
(defn- classpath-str->seq
  "Translate a classpath string to a sequence of classpath entries, respecting the
  platform path separator.  Returns an empty sequence if called with nil."
  [classpath]
  (if classpath (.split classpath (System/getProperty "path.separator")) []))


(defn- classpath-objs
  "Return the classpath as a map {:dirs [^File] :jars [^JarFile]} where each classpath
  entry is grouped/classified by if it is a directory or a Jar file."
  [classpath-seq]
  (->> classpath-seq
       (map #(File. ^String %))
       (map #(if (cp/jar-file? %) (JarFile. %) %))
       (group-by
        (fn [f]
          (cond
            (instance? JarFile f) :jars
            (.isDirectory f)      :dirs
            :else                 :resources)))))


(defn fetch-ns [path]
  (println (str "[FETCH] " path))
  (when-let [content (slurp (io/resource path))]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body content}))
