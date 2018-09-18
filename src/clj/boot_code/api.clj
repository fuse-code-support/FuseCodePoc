(ns boot-code.api
  (:require [castra.core :refer [defrpc *session*]]
            [clojure-watch.core :refer [start-watch]]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [boot-core.plugins :as plugin])
  (:import [java.util Date]))


;; Project metadata

(defonce project-root (atom (full-path ".")))

(defonce deltas
  (atom {:file-changes []
         :project-root @project-root
         :random (rand-int 100)
         :session (:id @*session*)}))


;; RPC

(defrpc refresh-state []
  (let [project-deltas @deltas]
    (swap! *session* update-in [:id] #(or % (rand-int 100)))
    (swap! deltas update-in [:random] (fn [x] (rand-int 100)))
    (swap! deltas update-in [:session] @*session*)
    (swap! deltas update-in [:file-changes] (fn [fc] []))
    project-deltas))


(defrpc get-config []
  @plugin/config)


(defn not-git [f]
  (not (str/includes? ".git")))


(defrpc get-files [root-directory]
  (try
    {:root root-directory
     :name (:short-name (file-details root-directory))
     :files (->> root-directory
                 io/file
                 file-seq
                 (filter not-git)
                 (map file-details))}
    (catch Exception e (.printStackTrace e))))


(defrpc get-project-files []
  (try
    {:name (:short-name (file-details @project-root))
     :files (->> @project-root
                 io/file
                 file-seq
                 (map file-details))}
    (catch Exception e (.printStackTrace e))))


(defrpc get-file [f]
  (conj (file-details f)
        {:contents (slurp f)}))


(defrpc save-file [f contents]
  (->> (slurp f) (spit (str ".#" f)))
  (spit f contents))


;; Filesystem event handling

(defn init-project [p]
  (println (str "Initializing project " p)))

(defn filesystem-change [event filename]
  (swap! deltas update-in [:file-changes] (fn [changes] (conj changes [event (file-details (io/file filename))])))
  (println @deltas))


(def filesystem-event? #{:init :create :modify :delete})


(defonce end-watch
  (start-watch [{:path @project-root
                 :event-types [:create :modify :delete]
                 :bootstrap init-project
                 :callback filesystem-change
                 :options {:recursive true}}]))
