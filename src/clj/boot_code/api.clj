(ns boot-code.api
  (:require [castra.core :refer [defrpc *session*]]
            [clojure-watch.core :refer [start-watch]]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [java.util Date]))


(defn expand-path [p]
  (let [home (System/getProperty "user.dir")]
    (if (str/includes? p "~")
      (str/replace p "~" home)
      p)))

(def default-repo "~/.fusion")

(defonce config
  (atom {:boot
         {;; :local-repo (str default-repo "/bootstrap")
          ;; :local-repo-path (str (expand-path default-repo) "/bootstrap")
          :name "bootstrap"
          :remote-repo "git@github.com:/coconutpalm/webfusion-boot"
          :init "webfusion/boot"}       ; Maybe each repo should have a config naming the init namespace/function

         :plugins
         [{:name "materialize-light-theme"
           :remote-repo "git@github.com:/coconutpalm/webfusion-mz-light"}

          {:name "webfusion-text"
           :remote-repo "git@github.com:/coconutpalm/webfusion-text"}]}))


;; File path stuff

(defn full-path [relative-path] (.getCanonicalPath (io/file relative-path)))

(defn file-details [fileOrName]
  (let [f (if (string? fileOrName) (io/file fileOrName) fileOrName)]
    {:full-name (.getCanonicalPath f)
     :short-name (.getName f)
     :directory (.isDirectory f)
     :hidden (.isHidden f)
     :last-modified-millis (.lastModified f)}))


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
  @config)


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
