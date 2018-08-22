(ns util.codemirror-assets
  (:require
   [hoplon.core :as h :refer [script link]]
   [clojure.string :as str]))


;; CDN assets from viewing the Javascript source at https://cdnjs.com/libraries/codemirror

(def raw-assets
  {"addon/comment/comment.js" "sha256-PMztPd0WjBzgiJ4McuMm7XaEtech972Iz4rw3RY0ZE8="
   "addon/comment/comment.min.js" "sha256-xRNygSqAYMT9wcso0FgZEY3ROGoD6JdvYd8M9IjSuNg="
   "addon/comment/continuecomment.js" "sha256-kqfH5KHq/xKc52VRn8qxC7EWic8bGDLB5ZAlrlFH62g="
   "addon/comment/continuecomment.min.js" "sha256-k2nzqUY5pv2ik/hy+GUF8vIUwLjYSxyANCun+oRiXVQ="
   "addon/dialog/dialog.css" "sha256-XfaQ13HxIRg0hWLdKpAGBDOuLt7M0JCKvKpEgLHj5Gg="
   "addon/dialog/dialog.js" "sha256-oRCL9tU8JnTY35IYKvc/N2vtA026JwyuIUnPEeNmA08="
   "addon/dialog/dialog.min.css" "sha256-OjF42ew3ra0/zNWgjfDTydf609RXv0cXcassXEeW0O8="
   "addon/dialog/dialog.min.js" "sha256-gmjSPV/CWcF/0kQNYNmDCxwReBXjBxmz170ctjc0GQY="
   "addon/display/autorefresh.js" "sha256-ADmSAm8ny/K6dCivADkiRLfJh7AQZyKbPP/sqDZXJ8A="
   "addon/display/autorefresh.min.js" "sha256-QuGJoD9Zg8DnF/sCAziOU04TfXIXRMpeI7EjlQB5saU="
   "addon/display/fullscreen.css" "sha256-SpuaNYgDjBMdeyjrjtsC+U5fpSDpftPNv7oO8HQvG7w="
   "addon/display/fullscreen.js" "sha256-gEWmEEzRvSCXzJ+6Ev6+i5uP6jkbqm1ymNkojIZPtEY="
   "addon/display/fullscreen.min.js" "sha256-7RNoYfNeoShOS6Ry3d3ek7uRgARlr7oRYXbR1ni/ZEg="
   "addon/display/panel.js" "sha256-rxnhs8X0bTVqsfO/f+jESaxdmS+4bMJrMbrgJFDvyTI="
   "addon/display/panel.min.js" "sha256-OEWEXD18RnBNEJBK874vobxhWjduKyB11vz8vLiam9E="
   "addon/display/placeholder.js" "sha256-YUesMvUoJzrGTIkt2Z2KI+VK70JnKJARSVAnExZKjC4="
   "addon/display/placeholder.min.js" "sha256-Pf4xJn7rNEBWE4WkGU1DqrOyjdj+gTcN9U7s62nxv8Q="
   "addon/display/rulers.js" "sha256-a+3dtkQd4wfs+IRuSkADZIeQMkHChvWB3VUe9w2QLgs="
   "addon/display/rulers.min.js" "sha256-DVVIzH5rgcaxxIV9tuw7uO4iM9/WtgPZ2btEJAqsc0w="
   "addon/edit/closebrackets.js" "sha256-+5rFwVXNB9T8+LxZU9BtEnlmzDoLVSmf02ryLP9Uz2U="
   "addon/edit/closebrackets.min.js" "sha256-10obt28t3Dd+guzNQ0QNivTnHMB1VjSMrvWuYe82fzo="
   "addon/edit/closetag.js" "sha256-/2LbWiLMIJySnw4irgBBFQ4geM6kZej0YtBunJzN8Eo="
   "addon/edit/closetag.min.js" "sha256-Jjk6J1x91NZmT7qTwVYKrZgaS0Zm6EBfpv+lWJWFjR8="
   "addon/edit/continuelist.js" "sha256-S7HQorUPulxbNjmuC8+jrBzQLggg0r9QyFzS5wkJsEg="
   "addon/edit/continuelist.min.js" "sha256-582TeoSGSNIKuWpFNzosQBDK701iU0zxj83uYoewqk0="
   "addon/edit/matchbrackets.js" "sha256-1ZBoXf1PnYzxJ7zY5+7ZAJbUh9xXYGUUXHn8r21LHWg="
   "addon/edit/matchbrackets.min.js" "sha256-j1VkYOkaOkS8FYalwckgVbhOWrPyDbhPka8mHrvGMsc="
   "addon/edit/matchtags.js" "sha256-aKA2BMUxAIY3kNKzmYhxUFMCiNL/s/qF3zIxvROsnm8="
   "addon/edit/matchtags.min.js" "sha256-7+ar9rS4zfA49+LlLzDc0O7Wzf7tFqxTjo38KHBObAA="
   "addon/edit/trailingspace.js" "sha256-p9V6C891txuDw/3jGuNH2q/rRGh1ND4Q64Nz7ioIYiE="
   "addon/edit/trailingspace.min.js" "sha256-IazJOM2ma6yJ/doWlcIy+Ica+UAgYJDDyYf/qgkLUNM="
   "addon/fold/brace-fold.js" "sha256-/zTWJHS+hYSX9H0S1vHdAdG4diVKri1dYcEVFf66cFw="
   "addon/fold/brace-fold.min.js" "sha256-9uCNHOc+/qsD6onoM4E5utj87E9PYUAwA4TCho1DJjQ="
   "addon/fold/comment-fold.js" "sha256-OqzED4mNVUKhDi4J5uEVt0Sl2AGdBwHaOU0V+fcDipM="
   "addon/fold/comment-fold.min.js" "sha256-TtIRvl9L2maMD/yxnknsaMaG/x7iURw82wN3FsqIQes="
   "addon/fold/foldcode.js" "sha256-OACGugamoK8QlA6UXmMIOML5y7zlc74JBEfOKUsyfws="
   "addon/fold/foldcode.min.js" "sha256-hlPl1tY0mxsuD6uo86vcxUneOFCyVf+7hi5xdgjJMcg="
   "addon/fold/foldgutter.css" "sha256-V27800C3cLYNd9jCnp3za/WFdjkb6rUbQ5EU5O0BrFY="
   "addon/fold/foldgutter.js" "sha256-FOr7dqBJfuZIdp3ACdmg88poq2ZABUiQ9ChPgPSxGas="
   "addon/fold/foldgutter.min.css" "sha256-PAZt4Yo+uLbowOVolpiWbNrg1VUtA43Zvw/TPBABeaQ="
   "addon/fold/foldgutter.min.js" "sha256-J6o/bQsRSMPVuaYuNZ1gDn1Lhaxs5tzD3V/u6MU6kP4="
   "addon/fold/indent-fold.js" "sha256-3Gr3lvn/5E3XJkszPSbQHCb4CMGVhGeyxnCicfNStGQ="
   "addon/fold/indent-fold.min.js" "sha256-5b8X1/ZhbeSRcLqQ5tLolonYFKPzwnc+bRr1bX8pJtc="
   "addon/fold/markdown-fold.js" "sha256-R5YhVWLmpmlc0BOsNTEespwY//JxR8SPKhZiPWZWxNQ="
   "addon/fold/markdown-fold.min.js" "sha256-GHVE8Ekme517bvrZeDOx4wNreAwc8NRYcjD4VEMsUVw="
   "addon/fold/xml-fold.js" "sha256-0NR53Omilqa/gGNsbd6g0IjppcgdSabwMu8jOTk6vFE="
   "addon/fold/xml-fold.min.js" "sha256-34EpGYKdkFYSCBBmukYngXyMty3sctm+w9wx9/hYGMs="
   "codemirror.css" "sha256-o/v/5fOniPHMAww4EAIfRBFV0SeoqksPrY0Yq5x+wSM="
   "codemirror.js" "sha256-dlU448ajDtrzPvUfqkvdw6yT52NgWy0UmxUgclvPEAc="
   "codemirror.min.css" "sha256-I8NyGs4wjbMuBSUE40o55W6k6P7tu/7G28/JGUUYCIs="
   "codemirror.min.js" "sha256-uRIJ6Wfou5cTtmcCvQNA9lvsYl8sUbZXxnfG+P79ssY="
   "keymap/emacs.js" "sha256-bR6Fl6+Bebrq2MK7/vnq5OjelR1Eq1x6D61hhw0yxgo="
   "keymap/emacs.min.js" "sha256-K5BnsovpaWdNA8SHHOxxNk+Bm8fgHPzzF+XccJ+2maw="
   "keymap/sublime.js" "sha256-oiK3Q98Vy9etAHi7uN2YOTpoqdLzYA3VcEt6wyENaFU="
   "keymap/sublime.min.js" "sha256-76dUbnG4W9u2Ld89pqWUpPpY97UQfvqgOSDjoBA943c="
   "keymap/vim.js" "sha256-mbtft8/JLTBlG6Iv0pHNLqNIWegykvXH1K4ce98Ytak="
   "keymap/vim.min.js" "sha256-nCypdy82rAHiBhWviPv3ilINZsyDeiO9BTm7mKj4Dy0="
   "mode/apl/apl.js" "sha256-1VKf3DXKRsIaQwGQBsvSpy8ycA3bCUk4o1wa9JwMwfQ="
   "mode/apl/apl.min.js" "sha256-v5TBMraIHc82n/8GF009PGvQRw2GQQfKsORTf78erhM="
   "mode/asciiarmor/asciiarmor.js" "sha256-BuJhibjnh9KcgGWrFJ4V7gbM+YRjDzZqAwRLAuJZPwo="
   "mode/asciiarmor/asciiarmor.min.js" "sha256-yDS0YA90m9umlL89LNB/9OEUPdYfXJ374X3OIOUWAyE="
   "mode/asn.1/asn.1.js" "sha256-TTH6nFSFkTp6cViFTOcnZktujFz8aZL1A37jo1P7H4k="
   "mode/asn.1/asn.1.min.js" "sha256-sCyPHB+jfYjmVReYDceksz+pWxusDmNhw89fHArZKnw="
   "mode/asterisk/asterisk.js" "sha256-93bQQK4B/rf9oTLvELmHdbd6Cnk04ldS8vg/pkgwULk="
   "mode/asterisk/asterisk.min.js" "sha256-l/RpVlOm2JawS1TObXEVDzYmjn6pHK7A3GB9Q8rWmwo="
   "mode/brainfuck/brainfuck.js" "sha256-ebhE/upGwESDGmL+uLEViM7BTxQyGKEfa8F/mCAvcVg="
   "mode/brainfuck/brainfuck.min.js" "sha256-RqnBZFb+N9KMVb0Cgz+28Xo6gPcvOU/QWQhKB2ZJT3w="
   "mode/clike/clike.js" "sha256-CI6R5ojjidBZNAbYz2oL/aYpzUTybuIFhsfE/JPOMMc="
   "mode/clike/clike.min.js" "sha256-D5hZ5IeEGGBiDnq5gtYxBDaTzKH//kbCt+piuYmOPAc="
   "mode/clojure/clojure.js" "sha256-y2ULxM1oLpUXUYIYDGyGHttATyZ7opjHojyQkJdOYEI="
   "mode/clojure/clojure.min.js" "sha256-+6TM2wmRO0PiiFNUD3Fah6cb2VoLfBPoSUza1lcpQ/A="
   "mode/cmake/cmake.js" "sha256-v5wmo/qEe+L9JpvGGpTstClgY2oDrVfI36tvPK+FGxU="
   "mode/cmake/cmake.min.js" "sha256-T43UDUHxJnr43FDa46LDFLrstbJ4MyhiFetfoaXmuVE="
   "mode/cobol/cobol.js" "sha256-6RMQ5ZZXr8UZslLLDioid2LS0DlyO1Tfb1dZM5/NNpE="
   "mode/cobol/cobol.min.js" "sha256-nMT5LfUBLG8BjlLxhrMcQ6t/vabG5S1F2tvslKpXFJw="
   "mode/coffeescript/coffeescript.js" "sha256-DQbTCQDN6PLPjaZ6IQaImU776n1lMlvzK6uei7nLRYc="
   "mode/coffeescript/coffeescript.min.js" "sha256-Xvk0TUDJfgrGobkUAZzUAO/RQB3QA84XGVvp5k7bDzk="
   "mode/commonlisp/commonlisp.js" "sha256-uUFcapGpyTX5N2m5pgLUlVWl96zE1MIpwIzhVxMw85A="
   "mode/commonlisp/commonlisp.min.js" "sha256-U3cLcv1yqKHIHh7grdscea2kAL2crITxHjsQwgf9ldM="
   "mode/crystal/crystal.js" "sha256-xBPpicaVfZx0SFE+Af8NPAcQ0nRFrLITeDVceGdnEno="
   "mode/crystal/crystal.min.js" "sha256-SVO4UGDNDPgiqi9nPAPNZ/Lq2DjhaoW/rdqwsRzwf7c="
   "mode/css/css.js" "sha256-A3dmy5MnEwoDZJmeJUoj98Al4uPPcF47typ3TSLhA0g="
   "mode/css/css.min.js" "sha256-YVb7n24DLwrjN0DghcwJYL17MI/X7e3c4EJZr6PwNdY="
   "theme/3024-day.css" "sha256-fJEmodiji+cKbZaHL4rwMhUgPzxllZdTFMxPdfeSuPg="
   "theme/3024-day.min.css" "sha256-bB20Bi9tvCldTENC9u9qQCYOWImcP8UR74XoqYPCyS0="
   "theme/3024-night.css" "sha256-k62i8orMg6WJwtZRR0l+9XMiN7Kp5tq7PWnUhuHTpEc="
   "theme/3024-night.min.css" "sha256-2KFs42xyMNW4uFhgByGByfB8YZL0oHH5eE0hC5/xwgI="
   "theme/abcdef.css" "sha256-GfADyGh4N7GVp2jTmgFfrhSUhe81RyYWG1Ed76oCD5k="
   "theme/abcdef.min.css" "sha256-f9H9yKjrcNSfxN5oYO+62dzdtqvxrtU4b4JBTQbCDRM="
   "theme/ambiance-mobile.css" "sha256-NWz51TmnBGKAjXaFHiS7AElnZiHQC22FHCse4rD2rFc="
   "theme/ambiance.css" "sha256-SZzo3Yuty19sYiggzqMv42hJc4jafF1FhpmULwew1VU="
   "theme/ambiance.min.css" "sha256-XkpQJvUWez5zVFHMRprEmjXK/c4Fo9GS3lpKfwrckIk="
   "theme/base16-dark.css" "sha256-emGSdkvT2hWPB1YJQvR8bvsxXcxGK3fjLU+hQMf9IkE="
   "theme/base16-dark.min.css" "sha256-X67YRu1NMnnvJJ1h9B7aBcAizvn0rFgUk14ftb4Yfgg="
   "theme/base16-light.css" "sha256-x0GJ1Ckd16hagKRmo0FooXDFm9sSMEww+NRtMcoHANc="
   "theme/base16-light.min.css" "sha256-FNhflbWy2O+7IqyCpTQfnMyjrVuGSc5DycCrt1krcEg="
   "theme/bespin.css" "sha256-1D2lWnhZ6r2Up3Ne/4IFwTaBLivbA3+FqwJjIP2be0M="
   "theme/bespin.min.css" "sha256-1ERF9HPWazRfJdHaxIjJCI9zZoTcmt1Du33oaxEplUw="
   "theme/blackboard.css" "sha256-VPCBHwDRMLPDKDz2eef73Lj5Om2oiuQBFzkiCoFs5FE="
   "theme/blackboard.min.css" "sha256-leZaLFrsfIwu5sPvJhg10z3vXVAPKRVX3A5sneuWm6o="
   "theme/cobalt.css" "sha256-Os0qLNmu+uyjtBpFHiZAhADg2Vi46EWtS81e31/5AeA="
   "theme/cobalt.min.css" "sha256-w+8VeOcoKzd5XqQTdITPf1v7o1OGGNWu0CUVEFVSMOc="
   "theme/colorforth.css" "sha256-R/laTMhLZM7bA9yvUzdCfO6F3USVgirLMPfhurg71oc="
   "theme/colorforth.min.css" "sha256-pgzYXfYyPJYWjahb4JPg7LLiMWWVWsMY0NrG3xhNhgU="
   "theme/darcula.css" "sha256-ZWlMXbF4SdWiqz5rvRpldZ/+Rqdp89BLC1TP5yYCVOc="
   "theme/darcula.min.css" "sha256-vKtJ4KmIcVYUNgC/ykIcrpR5LQeUVfwHpVaCVpL34jM="
   "theme/dracula.css" "sha256-Av32BJYI3djQ189kA7blslEzB0Dfw5N3T2i7ZHJ7PG4="
   "theme/dracula.min.css" "sha256-Dq8vPj3GNwTg9rflLnPQ8bDSLndsRxjmtWRXOwcWR4g="
   "theme/duotone-dark.css" "sha256-Q4Y20vpbJa3+O5FESBoCnm9Lwn9ab4wuLDlN1c/h/7Q="
   "theme/duotone-dark.min.css" "sha256-ogC2zMsvsul0YSNVfC6awS4n3oJletA9pl6Y7AwNANo="
   "theme/duotone-light.css" "sha256-UQ0Q46O9H2lcnrA0qBhoNPmyCYb9jEG8FISljJ8648k="
   "theme/duotone-light.min.css" "sha256-20mLYcyo60y1P1Zib5qlTqzuiCTnr2vyiQetUqiFzVI="
   "theme/eclipse.css" "sha256-WhzJFLQmMeYNz3p+/UEqGJczEHrgZQiYawzX76gjnuo="
   "theme/eclipse.min.css" "sha256-C2k2AGTJsrRfmmxnfxARIMz6/LK8tCUuFcWsG4kMhu8="})



(def cdn-base "https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.39.2/")
(defn url [asset] (str cdn-base asset-url))


(defn css
  ([asset]
   (let [url (url asset)
         sri (get raw-assets asset)]
     (if (and url sri)
       (css url sri))))
  ([url sri]
   (fn [] (link :rel "stylesheet" :href url :integrity sri :crossorigin "anonymous"))))


(defn js
  ([asset]
   (let [url (url asset)
         sri (get raw-assets asset)]
     (if (and url sri)
       (script url sri))))
  ([url sri]
   (fn [] (script :src url :integrity sri :crossorigin "anonymous"))))


(defn asset->dom-fn
  "For a given asset, build a fn that will create a DOM node to load that asset"
  [asset sri]
  (cond
    (str/ends-with? asset ".min.js") [:minjs (js (url asset) sri)]
    (str/ends-with? asset ".js")  [:js (js (url asset) sri)]
    (str/ends-with? asset ".min.css") [:mincss (css (url asset) sri)]
    (str/ends-with? asset ".css") [:css (css (url asset) sri)]
    :default [:error asset]))


(defn split-path [p] (str/split p "/"))
(defn split-file [f] (str/split f "."))


(defn asset-name
  "Return the asset's name from it's path"
  [asset-path]
  (-> asset-path
     split-path
     last
     split-file
     first))


;; Reducers to build the {"thing-name" {:js ...}} map for a category
;; of raw assets

(defn add-new-asset-type [asset-map next-asset dom-fn]
  (let [updated-asset-code (conj {} dom-fn)
        new-asset-map (conj asset-map [next-asset updated-asset-code])]
    [new-asset-map next-asset]))


(defn add-asset-to-asset-type [asset-map next-asset dom-fn]
  (let [asset-code (get asset-map next-asset {})
        updated-asset-code (conj asset-code dom-fn)
        new-asset-map (conj asset-map [next-asset updated-asset-code])]
    [new-asset-map next-asset]))


(defn compute-asset-map [[asset-map last-asset] [asset-path sri]]
  (let [next-asset (asset-name asset-path)
        dom-fn (asset->dom-fn asset-path sri)]
    (if (= last-asset next-asset)
      (add-asset-to-asset-type asset-map next-asset dom-fn)
      (add-new-asset-type asset-map next-asset dom-fn))))


;; Query the raw assets for all assets matching a given type

(defn is-asset-of-type? [asset-type [path sri]]
  (str/starts-with? path asset-type))

(def theme? (partial is-asset-of-type? "theme"))
(def mode? (partial is-asset-of-type? "mode"))
(def addon? (partial is-asset-of-type? "addon"))
(def keymap? (partial is-asset-of-type? "keymap"))
(def codemirror? (partial is-asset-of-type? "codemirror"))

(defn select-assets-matching [predicate]
  (let [raw-assets (sort (filter predicate raw-assets)) ; Have to sort because map keys don't preserve order
        categorized-assets (first (reduce compute-asset-map [{} ""] raw-assets))]
    categorized-assets))


;; Maps {"thing-name" {:js jsfn :min-js minjsfn ...}}

(def all-theme-assets (select-assets-matching theme?))
(def all-mode-assets (select-assets-matching mode?))
(def all-addon-assets (select-assets-matching addon?))
(def all-keymap-assets (select-assets-matching keymap?))

(defn assets-for
  "Get the specific assets for a given asset name and type"
  [asset-type specific-name]
  (get asset-type specific-name))


;; And now the stuff intended to be used from outside!

(def theme-names (sort (keys all-theme-assets)))
(def mode-names (sort (keys all-mode-assets)))
(def addon-names (sort (keys all-addon-assets)))
(def keymap-names (sort (keys all-keymap-assets)))

(def theme (partial assets-for all-theme-assets))
(def mode (partial assets-for all-mode-assets))
(def addon (partial assets-for all-addon-assets))
(def keymap (partial assets-for all-keymap-assets))

(def codemirror-assets (get (select-assets-matching codemirror?) "codemirror"))
