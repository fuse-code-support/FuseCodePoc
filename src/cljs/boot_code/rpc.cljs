(ns boot-code.rpc
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [javelin.core]
   [castra.core :refer [mkremote]]

   [boot-code.ui :as ui]
   [boot-code.parensoup :as ps]))

;; server-pushed-events
(defc push-events {})

;; Config from the server
(defc config {})

;; The results of asking for all files starting at a given root
(defc files-at {:root ""
                :name ""
                :files []})

;; The current project and the file we're currently loading
(defc project {:name ""
               :files []})

(defc loading-file {})

;; Server status messages are placed here
(defc server-status-message "")

;; AJAX stuff
(defc error nil)
(defc= error-message (some-> error .-message))
(defc loading [])
(def clear-error! #(reset! error nil))

;; Pull random stuff out of push-events when it changes
(defc= random-number  (get push-events :random))
(defc= session-number (get push-events :session))

(defc= project-name (get project :name))

;; Server API
(def refresh-state (mkremote 'boot-code.api/refresh-state push-events error loading))
(def get-config (mkremote 'boot-code.api/get-config config error loading))
(def get-files (mkremote 'boot-code.api/get-files files-at error loading))
(def get-project-files (mkremote 'boot-code.api/get-project-files project error loading))
(def get-file (mkremote 'boot-code.api/get-file loading-file error loading))
(def save-file (mkremote 'boot-code.api/save-file save-file error loading))

;; Where everything starts
(defn init []
  (reset! ui/default-loader #(ps/activate ui/root))
  (get-config)
  (refresh-state)
  (get-project-files)
  (js/setInterval refresh-state 1000))
