(ns boot-code.plugins
  (:require [boot-code.io :as io]))


(def default-repo "~/.fusion")

(defonce config
  (atom {:boot
         {;; :local-repo (str default-repo "/bootstrap")
          ;; :local-repo-path (str (io/expand-path default-repo) "/bootstrap")
          :name "bootstrap"
          :remote-repo "git@github.com:/coconutpalm/fusion-boot"
          :init "fusion/boot"}       ; Maybe each repo should have a config naming the init namespace/function

         :plugins
         [#_{:name "Code Notebook"
           :init "boot-code.parensoup/do-init"}

          {:name "Workbench"
           :remote-repo "git@github.com:/coconutpalm/fusion-mz-light"
           :init "fusion.workbench.ui/do-init"}

          {:name "CodeMirror Editor"
           :remote-repo "git@github.com:/coconutpalm/fusion-cm"
           :init "fusion.editor.ui/activate"}]}))
