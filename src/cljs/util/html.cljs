(ns util.html
  "HTML / DOM manipulation functions"
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events]
            [hoplon.core :as h :refer [script link]]
            [javelin.core :refer [cell]])
  (:require-macros
   [javelin.core :refer [defc defc=]])
  (:import [goog Timer]))


;; Find / process HTML elements

(defn by-id
  "Get an element by its id"
  [id]
  (.getElementById js/document (name id)))


(defn length
  "Return the number of elements in an HTMLCollection"
  [nodes]
  (. nodes -length))


(defn item
  "Get the item at offset n in HTMLCollection"
  [nodes n] (.item nodes n))


(defn as-seq
  "Explicitly coerce an HTML collection to a seq"
  [nodes]
  (for [i (range (length nodes))] (item nodes i)))


(defn by-tag
  "Returns the seq of elements with the specified tag name"
  [tag]
  (as-seq
   (.getElementsByTagName js/document (name tag))))



(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))


(extend-type js/HTMLCollection
  ISeqable
  (-seq [array] (array-seq array 0)))


(extend-type js/FileList
  ISeqable
  (-seq [array] (array-seq array 0)))


(defn refresh-layout
  "Force the browser to relayout the page"
  []
  (set! (-> js/document .-body .-style .-zIndex) 1))



(defn html
  "Return the html inside the specified dom node."
  [dom] (. dom -innerHTML))


(defn set-html!
  "Set the html inside the specified dom node."
  [dom content]
  (set! (. dom -innerHTML) content))


;; Better:
;;
;; https://stackoverflow.com/questions/13121948/dynamically-add-script-tag-with-src-that-may-include-document-write
;;
;; See the answer with 9 +1s
;;
;; Also useful:
;;
;; https://www.html5rocks.com/en/tutorials/speed/script-loading/


(defn get-script
  ([script-info script-cb final-cb script-number script-total]
   (let [script-dom-node (cond
                           (fn? script-info) (script-info)
                           (vector? script-info) (let [[url sri] script-info]
                                                   (h/script :src url :integrity sri :crossorigin "anonymous"))
                           (string? script-info) (h/script :src script-info)
                           :default              (throw (str "ERROR: Unable to load script: " script-info)))]
     (set! (.-onload script-dom-node)
           (fn []
             (script-cb script-number script-total)
             (when (= script-number script-total)
               (final-cb script-number script-total))))
     (.appendChild (-> js/document .-head) script-dom-node)))

  ([script-info script-cb]
   (get-script script-info (fn [a b]) script-cb 1 1))

  ([scripts script-cb final-cb]
   (letfn [chain-next [scripts script-cb]])))


(defn get-script
  "Load the specified .js file

  script - The script to load.
  continuation-fn - The function to call when the script is loaded."
  ([script continuation-fn]
   (.getScript js/$ (str script) #(js/setTimeout (fn [] (continuation-fn)) 0)))
  ([script]
   (get-script script identity)))


(defn get-scripts
  "Use JQuery to load scripts in order.  on-complete is called when all scripts
  are loaded.

  baseurl is a base URL to apply to the head of each script URL.
  scripts is a vector of scripts to load.
  script-complete is called repeatedly when each script is done loading.
  all-complete is the continuation function to call when scripts are all loaded."
  [baseurl scripts script-complete all-complete]
  (if (empty? scripts)
    (js/setTimeout all-complete 4000)
    (get-script (str baseurl (first scripts))
                (fn []
                  (script-complete (first scripts) (count scripts))
                  (get-scripts baseurl (rest scripts) all-complete)))))


(defn append-child
  "Add child-node as a child of parent-node.  child-node may be a single dom node or
  a seq of dom nodes to be added."
  [parent-node child-node]
  (if (seq? child-node)
    (do (dom/appendChild parent-node (first child-node))
        (append-child parent-node (rest child-node)))
    (dom/appendChild parent-node child-node)))
