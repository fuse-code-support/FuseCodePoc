(ns util.model
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [javelin.core :refer [cell]]
   [castra.core :refer [mkremote]]
   [util.codemirror :as editor]
   [util.html :as html]))



;; Init
(set! cljs.core/*print-fn* #(.log js/console %))



;; Code Mirror -- temporary hack
(defc codemirror nil)

(defn editor-doc []
  (when-let [editor @codemirror]
    (.-doc editor)))


;; Session state from the server

(defc state {})
(defc error nil)
(defc= error-message (when error (.-message error)))
(defc loading [])
(def clear-error! #(reset! error nil))

;; RPC status
(defc= loaded? (not= {} state))
(defc= loading? (seq loading))

(defn mountpoint-name [dir] (if (string? dir) (last (.split dir "/"))))

;; Destructure the session state map using cells
(defc= workspace-path      (:current-workspace state))
(defc= current-workspace   (get (:workspaces state) workspace-path))
(defc= sorted-workspaces   (sorted-map-by (fn [k1 k2] (< (mountpoint-name k1) (mountpoint-name k2))) (seq (:workspaces state))))
(defc= project-tree        (:project-tree current-workspace))
(defc= path                (:file-path current-workspace))
(defc= filename            (:filename current-workspace))
(defc= contents            (:saved-file-contents current-workspace))

;; -- CodeMirror concerns are getting mixed in here


(defc= editor-file
  (when (and filename contents (editor-doc) codemirror)
    (let [editor-file (.getValue (editor-doc))]
      (when (empty? editor-file)
        (.setValue (editor-doc) contents)
        (.markClean (editor-doc))
        (editor/set-mode codemirror filename)
        (html/refresh-layout)
        (.focus codemirror)))
    contents))


(defn set-editor! [editor]
  (reset! codemirror editor))


;; RPC API
(def save-editor! nil #_(mkremote 'editorclj.api/save-file! state error loading))
(def get-state    nil #_(mkremote 'editorclj.api/get-state state error loading))


(defn save-if-dirty! [save!]
  (when-let [doc (editor-doc)]
    (when-not (.isClean doc)
      (save! path filename (.getValue doc))
      (.markClean doc)
      (println (str "Saved " path "/" filename)))))


(defn init []
  (get-state)
  (js/setInterval save-if-dirty! 5000))
