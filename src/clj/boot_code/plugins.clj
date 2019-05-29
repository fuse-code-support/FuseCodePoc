(ns boot-code.plugins
  (:require [boot-code.io :as io]))


(def default-repo "~/.fusecode")

(defonce config
  (atom {:boot
         {;; :local-repo (str default-repo "/bootstrap")
          ;; :local-repo-path (str (io/expand-path default-repo) "/bootstrap")
          :name "bootstrap"
          :remote-repo "git@github.com:/fuse-code/FuseCodePoc.git"
          :init "fusecode/boot"}       ; Maybe each repo should have a config naming the init namespace/function

         :plugins
         [#_{:name "Code Notebook"
           :init "boot-code.parensoup/do-init"}

          #_{:name "Workbench"
           :remote-repo "git@github.com:/fuse-code/fusecode-mz-light"
           :init "fusecode.workbench.ui/do-init"}

          #_{:name "CodeMirror Editor"
           :remote-repo "git@github.com:/fuse-code/fusecode-cm"
           :init "fusecode.editor.ui/activate"}]}))
