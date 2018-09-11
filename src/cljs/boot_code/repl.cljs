(ns boot-code.repl
  (:import goog.net.XhrIo)
  (:require
   [eval-soup.core :refer [code->results]]

   [clojure.string :as str]
   [clojure.pprint :as p]))


(def load-endpoint "/require?namespace=")


;; Adapted from https://github.com/oakes/eval-soup/blob/master/src/eval_soup/core.cljs
(defn- fix-goog-path [path]
                                        ; goog/string -> goog/string/string
                                        ; goog/string/StringBuffer -> goog/string/stringbuffer
  (let [parts (str/split path #"/")
        last-part (last parts)
        new-parts (concat
                   (butlast parts)
                   (if (= last-part (str/lower-case last-part))
                     [last-part last-part]
                     [(str/lower-case last-part)]))]
    (str/join "/" new-parts)))


;; Adapted from https://github.com/oakes/eval-soup/blob/master/src/eval_soup/core.cljs
(defn- ns-load!
  ([opts cb]
   (if (re-matches #"^goog/.*" (:path opts))
     (ns-load!
      (update opts :path fix-goog-path)
      [".js"]
      cb)
     (ns-load!
      opts
      (if (:macros opts)
        [".clj" ".cljc"]
        [".cljs" ".cljc" ".js"])
      cb)))

  ([opts extensions cb]
   (if-let [extension (first extensions)]
     (try
       (let [endpoint (str load-endpoint (:path opts) extension)]
         (.log js/console (str "[ns-load!] " endpoint opts))
         (.send XhrIo
                endpoint
                (fn [e]
                  (if (.isSuccess (.-target e))
                    (cb {:lang (if (= extension ".js") :js :clj)
                         :source (.. e -target getResponseText)})
                    (ns-load! opts (rest extensions) cb)))))
       (catch js/Error _
         (ns-load! opts (rest extensions) cb)))
     (cb {:lang :clj :source ""}))))


(def default-repl-options
  {:timeout 10000
   :custom-load ns-load!})


(defn read-eval
  "Evaluate forms, calling callback when results are ready.  If present, opts is a map :key value
  matching the options accepted by eval-sup.core/code->results.  If any keys in opts are the same
  as default repl options, the passed opts will override the default-repl-options."
  ([forms callback]
   (read-eval nil forms callback))

  ([opts forms callback]
   (let [options (if opts (into default-repl-options (vec opts)) default-repl-options)]
     (code->results forms callback options))))
