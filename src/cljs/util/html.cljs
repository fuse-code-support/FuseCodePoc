(ns util.html
  "HTML / DOM manipulation functions"
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events]
            [clojure.string :as str]
            [hoplon.core :as h :refer [script link]]
            [javelin.core :refer [cell]]

            [boot-code.jobs :as job :refer [with-blocking]])
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


(defn append-child
  "Add child-node as a child of parent-node.  child-node may be a single dom node or
  a seq of dom nodes to be added."
  [parent-node child-node]
  (if (sequential? child-node)
    (do (dom/appendChild parent-node (first child-node))
        (append-child parent-node (rest child-node)))
    (dom/appendChild parent-node child-node)))


(defn html
  "Return the html dom node itself or the html inside the specified dom node if present."
  [dom]
  (if dom
    (. dom -innerHTML)
    (.-html js/document)))


(defn head!
  "Return the head dom node.  If subnodes are specified, add them to the head node."
  [& subnodes]
  (let [h (.-head js/document)]
    (if subnodes
      (append-child h subnodes))
    h))


(defn set-html!
  "Set the html inside the specified dom node."
  [dom content]
  (set! (. dom -innerHTML) content))



(defn css
  ([url]
   (fn [] (link :rel "stylesheet" :href url)))
  ([url sri]
   (fn [] (link :rel "stylesheet" :href url :integrity sri :crossorigin "anonymous"))))


(defn js
  ([url]
   (fn [] (script :src url)))
  ([url sri]
   (fn [] (script :src url :integrity sri :crossorigin "anonymous"))))


(defn asset->dom-fn
  "For a given asset: build a fn that will create a DOM node to load that asset"
  [url sri]
  (cond
    (str/ends-with? url ".min.js") [:minjs (js url sri)]
    (str/ends-with? url ".js")  [:js (js url sri)]
    (str/ends-with? url ".min.css") [:mincss (css url sri)]
    (str/ends-with? url ".css") [:css (css url sri)]
    :default [:error url]))


;; Better:
;;
;; https://stackoverflow.com/questions/13121948/dynamically-add-script-tag-with-src-that-may-include-document-write
;;
;; See the answer with 9 +1s
;;
;; Also useful:
;;
;; https://www.html5rocks.com/en/tutorials/speed/script-loading/


;; TODO: Don't load the same JS file more than once?

(defn load-script
  "Dynamically load a JS file.  script-info can be a nullary function returning a script dom node, a vector
  of the form [\"url\" \"hash\"] from which a script node will be built, or a string containing a url
  from which a script node will be built.  When the script is done loading, script-cb will be called.

  If you want blocking behavior, use this together with jobs/with-blocking."
  [script-info script-cb]
  (let [script-dom-node (cond
                          (fn? script-info)         (script-info)
                          (sequential? script-info) (let [[url sri] script-info] (js url sri))
                          (string? script-info)     (js script-info)
                          :default                  (throw (str "ERROR: Unable to load script: " script-info)))]
    (set! (.-onload script-dom-node) #(script-cb))
    (head! script-dom-node)))


(defn load-scripts
  "Load the specified scripts one after another using job/submit.  job-name is the job name for the UI.
  script-infos is a sequence of script-info in the format accepted by load-script."
  [job-name script-infos]
  (let [loader-fns (map (fn [script-info] (partial load-script script-info)) script-infos)]
    (job/submit job-name loader-fns)))
