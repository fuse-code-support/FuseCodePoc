(ns util.codemirror-assets
  (:require
   [hoplon.core :as h :refer [script link]]
   [clojure.string :as str]))


;; CDN assets from viewing the Javascript source at https://cdnjs.com/libraries/codemirror

(def raw-assets
  {"addon/comment/comment.js" "sha256-m4vfDNTDDOkxNX441ppPWxDY90338x/uSmhcBUdDkaA="
   "addon/comment/comment.min.js" "sha256-0RU7z/sM2ecHGrMHbzBLykyHXnyORskLO+ZG7+/X9kU="
   "addon/comment/continuecomment.js" "sha256-E/55/R81YF/7UYf/69jUOfQmdE4hcx+vYHp+a4fGPoQ="
   "addon/comment/continuecomment.min.js" "sha256-k2nzqUY5pv2ik/hy+GUF8vIUwLjYSxyANCun+oRiXVQ="
   "addon/dialog/dialog.css" "sha256-XfaQ13HxIRg0hWLdKpAGBDOuLt7M0JCKvKpEgLHj5Gg="
   "addon/dialog/dialog.js" "sha256-HuoOUA3OENhZTY1oGpHRtxpCCTVcnCmIXobiay12aF4="
   "addon/dialog/dialog.min.css" "sha256-OjF42ew3ra0/zNWgjfDTydf609RXv0cXcassXEeW0O8="
   "addon/dialog/dialog.min.js" "sha256-gmjSPV/CWcF/0kQNYNmDCxwReBXjBxmz170ctjc0GQY="
   "addon/display/autorefresh.js" "sha256-89Qn7AoxeZe7iWFOtjeCgk6rh/3KVrb6QwOTxVSqINE="
   "addon/display/autorefresh.min.js" "sha256-QuGJoD9Zg8DnF/sCAziOU04TfXIXRMpeI7EjlQB5saU="
   "addon/display/fullscreen.css" "sha256-SpuaNYgDjBMdeyjrjtsC+U5fpSDpftPNv7oO8HQvG7w="
   "addon/display/fullscreen.js" "sha256-HhKG06Ib+xZ6RYvmBap8s0os+CEXWPArOCet4VaME6Q="
   "addon/display/fullscreen.min.js" "sha256-7RNoYfNeoShOS6Ry3d3ek7uRgARlr7oRYXbR1ni/ZEg="
   "addon/display/panel.js" "sha256-TZ0dxEjqLqrNHZREsCLcxFIelHA+zQXsi5E0VLFNJ1A="
   "addon/display/panel.min.js" "sha256-tc4PE97H4QEdVZaShPreLwsIjwrL7EOBYR5tQ9PIH08="
   "addon/display/placeholder.js" "sha256-xm/MND4oN1e0jGTpIf86P37allaNioHPI5ebd8TuCEg="
   "addon/display/placeholder.min.js" "sha256-Pf4xJn7rNEBWE4WkGU1DqrOyjdj+gTcN9U7s62nxv8Q="
   "addon/display/rulers.js" "sha256-ASsvhX75qHft6uu8tOc/foj43Ex2Poxy5nGTdWMq0vM="
   "addon/display/rulers.min.js" "sha256-DVVIzH5rgcaxxIV9tuw7uO4iM9/WtgPZ2btEJAqsc0w="
   "addon/edit/closebrackets.js" "sha256-J+IeaQd1P4WqdI6BNbLIiQ8QuzlexywhSmLrFd4H2g0="
   "addon/edit/closebrackets.min.js" "sha256-HPvRTTRfYgD8d6FKSLC4E6YIx4C8l605Y3j2BnZLV5M="
   "addon/edit/closetag.js" "sha256-VstTy/cqhGCnt7pZM0Cw3+U5D4Vje8XLoFxVhzqQWBE="
   "addon/edit/closetag.min.js" "sha256-gp3SAGPzPf3FzLvgCK3DNSXjNDQlJhTnrIsO9klqIv0="
   "addon/edit/continuelist.js" "sha256-89apvgtbojOaNDMePIxCCphhOt0sYXV0PFEfVtpHB0M="
   "addon/edit/continuelist.min.js" "sha256-anRWeQfOX09lfvAi9lB90xQkvZS0aNnr4R1jZf8mEek="
   "addon/edit/matchbrackets.js" "sha256-UGjEwHiqdVBYUNK3mTvYBajQOWIC3fw3QpuCRwEPmow="
   "addon/edit/matchbrackets.min.js" "sha256-hfP/jiEfynvmq3cvlRWLdhKYZhpVVhYc2g+/ajTXdH0="
   "addon/edit/matchtags.js" "sha256-oCAwj6P1/BzATEuHMQxLOWONXkQHh4FLz8JFcIH/+hQ="
   "addon/edit/matchtags.min.js" "sha256-7+ar9rS4zfA49+LlLzDc0O7Wzf7tFqxTjo38KHBObAA="
   "addon/edit/trailingspace.js" "sha256-5g9l84KmkjFiH+r5guZmdC/0PdAUbJpBlNAA/mgk0l4="
   "addon/edit/trailingspace.min.js" "sha256-IazJOM2ma6yJ/doWlcIy+Ica+UAgYJDDyYf/qgkLUNM="
   "addon/fold/brace-fold.js" "sha256-YFLlUsHPZ5tK/MXEDkMNwC16NsibGybfvMz5BObcDTk="
   "addon/fold/brace-fold.min.js" "sha256-yU8gt2vCLYMiXXHIIvCXk1zMZNKab9nIBHQzu1P8wjg="
   "addon/fold/comment-fold.js" "sha256-uxrqMfzpwpnrdXW7neuSYBrF0dSkzHKe2uwYQImDHlE="
   "addon/fold/comment-fold.min.js" "sha256-TtIRvl9L2maMD/yxnknsaMaG/x7iURw82wN3FsqIQes="
   "addon/fold/foldcode.js" "sha256-cG1CfEDgfCxxgt1PNAMDDJBbeAV9sjklRWz8UOUcOnA="
   "addon/fold/foldcode.min.js" "sha256-hlPl1tY0mxsuD6uo86vcxUneOFCyVf+7hi5xdgjJMcg="
   "addon/fold/foldgutter.css" "sha256-V27800C3cLYNd9jCnp3za/WFdjkb6rUbQ5EU5O0BrFY="
   "addon/fold/foldgutter.js" "sha256-6JnDv3NtTca6ETbEeQXXA+5ndic6q+IszpQ6vd4whtU="
   "addon/fold/foldgutter.min.css" "sha256-PAZt4Yo+uLbowOVolpiWbNrg1VUtA43Zvw/TPBABeaQ="
   "addon/fold/foldgutter.min.js" "sha256-ROn4cKxQv2LtyYVg95ht+ZIVgM10D5W1J9Yw5yUlZpo="
   "addon/fold/indent-fold.js" "sha256-p6UXqJ/3akeE6fz3C7cKa2Qeerv0OE8veISk2cn9a6o="
   "addon/fold/indent-fold.min.js" "sha256-5b8X1/ZhbeSRcLqQ5tLolonYFKPzwnc+bRr1bX8pJtc="
   "addon/fold/markdown-fold.js" "sha256-6BRmDlvmBp7yvAmlF5oKxCNmmOUITJOKHnNGPwjtkTs="
   "addon/fold/markdown-fold.min.js" "sha256-GHVE8Ekme517bvrZeDOx4wNreAwc8NRYcjD4VEMsUVw="
   "addon/fold/xml-fold.js" "sha256-6qrza98BMjZqcPGsh7xpweWNL52WV7KmoCz6NUkA1qo="
   "addon/fold/xml-fold.min.js" "sha256-34EpGYKdkFYSCBBmukYngXyMty3sctm+w9wx9/hYGMs="
   "addon/hint/anyword-hint.js" "sha256-lCVMIcSF33YZe03bIZs2h/Cs2TIWgZGvmIs8FTYO3e0="
   "addon/hint/anyword-hint.min.js" "sha256-RAnsThvT8u3giTsRpAwZ+KWs/LPrEdNtwq2GyPb/RsQ="
   "addon/hint/css-hint.js" "sha256-F2pC6RSR6ImMWNJHgzBtWhllOtvM+0RH/kEiwXOxE4o="
   "addon/hint/css-hint.min.js" "sha256-YgbeYaYduJx72c9/5AE5qNzTVpRP0Lnz1SicS0Uz2Tw="
   "addon/hint/html-hint.js" "sha256-oj0nTCTQ8xIHuPFIqkCPNfpPGpBfFUE0PFEeDXKJM0Y="
   "addon/hint/html-hint.min.js" "sha256-g0y16Vk9iPD2xtByNDm5z5yzmSoDThnQun+70PmrxuA="
   "addon/hint/javascript-hint.js" "sha256-HEmCT6oFscubH3iyKcGwh2hIyVgazLm69PCO0hSDzGQ="
   "addon/hint/javascript-hint.min.js" "sha256-DN3hOGdPXi9N3/wFgcfbu/MN+d6CC1H1W/8Jci6p0f8="
   "addon/hint/show-hint.css" "sha256-9PjI1IwVXcv44E37KbDVAJdEYNCCBK3+Ddo757s6L8g="
   "addon/hint/show-hint.js" "sha256-IW35kIaT3WokVwV4gKdLrrYsbQgXdsxm4jKtoZ664oc="
   "addon/hint/show-hint.min.css" "sha256-Ng5EdzHS/CC37tR7tE75e4Th9+fBvOB4eYITOkXS22Q="
   "addon/hint/show-hint.min.js" "sha256-Bhy/CLlCXzJBWfLGNvFfJwTHtb1lEBjbryp7+zs6zjg="
   "addon/hint/sql-hint.js" "sha256-qnGT3vc+JHEtdL+OUz6AmZT0ecHLjpT8CDdqh6lpe7I="
   "addon/hint/sql-hint.min.js" "sha256-+lZ34RlzAR6AHKaFLiwYZFM+yJwSzADpjwC40QoIfds="
   "addon/hint/xml-hint.js" "sha256-UgSoVCWjs0Yf1Sf+7Ih5jYE5RUOj0APTjBfjfgKPf44="
   "addon/hint/xml-hint.min.js" "sha256-PHpb8Qfj1wrnE1kU3/ho8Opy7cMhn2PzWlksG5qr15w="
   "addon/lint/coffeescript-lint.js" "sha256-Ma7XGjkVpa8h/MXM9Y9Lj9WRJe0UvxUZi5dMfxkYC3c="
   "addon/lint/coffeescript-lint.min.js" "sha256-rX5wcTB4h0OiU92sHr4ncmGorrUBG/6nPGbMVC6D9Xo="
   "addon/lint/css-lint.js" "sha256-oAsVJYTK/LQYolZOvyc+cbPcJRsN9iAklo+XSQcMpUw="
   "addon/lint/css-lint.min.js" "sha256-N5PPJ6e515MNm9c5UIl2tf+xPeAvsuz/WtWX1m04AgI="
   "addon/lint/html-lint.js" "sha256-Im7AOkS0KTJk2v72z57Jff5sv487ZOZ7BR/jWIcGbuU="
   "addon/lint/html-lint.min.js" "sha256-vOWYBWvJsde6+FhqzeSDEDQ9zPr+nxddU/aNFZZlAnU="
   "addon/lint/javascript-lint.js" "sha256-wRj9cgIH2AkaH+AVN4nUZMEMk+1g5m/o/ZVqzQ0Ym4g="
   "addon/lint/javascript-lint.min.js" "sha256-7g6WCLN/19S+qrWEyfNjYJWsFJWvZuvRFpIBlp0GmOo="
   "addon/lint/json-lint.js" "sha256-rcDdyQSIdb46g+DkSEVB5qk/TL2lalz48Ffp1nR+0lc="
   "addon/lint/json-lint.min.js" "sha256-mWRA+eRb2A3v8WLC4QSEVKYYgWuy1jYrLoi4+tbWrnU="
   "addon/lint/lint.css" "sha256-dDBnEiZOZC2E52rfFN3yqILyvNpxc1Q4Z0cI23L+wgQ="
   "addon/lint/lint.js" "sha256-CgWPQKACSubz5tLvYSKAqssyorDcQLLhOenNooEmV/k="
   "addon/lint/lint.min.css" "sha256-h8ssETVF7g7LAuYcBn+6AQDL1DLfLVwuiBrLrk3yHks="
   "addon/lint/lint.min.js" "sha256-Xkhbv2H3s/jfeegKuWx55JnoHvyGdyD4qwREBntjsPU="
   "addon/lint/yaml-lint.js" "sha256-zK7NbTiTupJgJQEMmuhiynzegksFiywFUDYVLhF++pc="
   "addon/lint/yaml-lint.min.js" "sha256-B7OpmpvKrBEjxbKHiZaBlMt+fHOPP+9cgKC6dK6EzqA="
   "addon/merge/merge.css" "sha256-BZOB0QESGE77WpAGfDlvsk4lepfw8RkN50Ck6uu/4qM="
   "addon/merge/merge.js" "sha256-kwLhssPaAsdef/21RuvL8t8HhK6qFD90xo/+bJwSy+M="
   "addon/merge/merge.min.css" "sha256-hcRlCyibrk1rvO7+Jlq4zPmSiW7boTyySkEYtHpG5kc="
   "addon/merge/merge.min.js" "sha256-PpqolQ/4eh9Fg8QDMQmftxq0Lj7YIJk60QIvHz6AX7E="
   "addon/mode/loadmode.js" "sha256-FUiE+wlCEXQFUy8sn3HhxGwIy1HbblIMpCDt1o/4kJw="
   "addon/mode/loadmode.min.js" "sha256-lV0GDqbsqZWyDhAX82N2eYbx+qydYmXOitWgRqQI9Ao="
   "addon/mode/multiplex_test.js" "sha256-8RGnqpxCTp6FB8FV9vmdZFUV/ieWecCFhyoOjYxpj0k="
   "addon/mode/multiplex_test.min.js" "sha256-lCFQLNTQYkUOiYwUvzu9TP/tps6884UtPffaVZhCsd0="
   "addon/mode/multiplex.js" "sha256-wppZTY9OU+rmNLAHtVOhJUDPU5NIsK1UFIbwUyJaypg="
   "addon/mode/multiplex.min.js" "sha256-ZYMRxAJ9yXVRKbUsR0fytS+XWg6tLE1ltIuFx9LvIjM="
   "addon/mode/overlay.js" "sha256-/sdtVfzeo4ObwYqYkjPb5vD5UBdT0RM5CbOp8n/ubnY="
   "addon/mode/overlay.min.js" "sha256-YxRLL5230ecE9nov7s/keP4PRXTIoWEPVDW2DDdrbts="
   "addon/mode/simple.js" "sha256-5UrW8bYk5508xYjP4fekJ1+PyaJlTT8snbZwjmIqEjI="
   "addon/mode/simple.min.js" "sha256-CphEmWJK72DxAEhbSbZfOchUOnlFF8wmwv9GrerOGZI="
   "addon/runmode/colorize.js" "sha256-/eAy95ThPJtx9R2mBjf3GWGqGiFGhaJDhElTx5gPNzY="
   "addon/runmode/colorize.min.js" "sha256-SBZCwL2uLGWKTMqzecOp2O/ES1NHSKt5QSwtGBVZEoE="
   "addon/runmode/runmode-standalone.js" "sha256-DGsbmxyEhtsWI2HxDpvEu9U7x2S2GLS9yhu4GX+Z05I="
   "addon/runmode/runmode-standalone.min.js" "sha256-zjrvyZ8pA1ecePF3cAzyexm7McEld2GV8CZD6K+oWrc="
   "addon/runmode/runmode.js" "sha256-QX9rfzMLGLO/7kTIKYB/+HaPDU6HiU4bPgnorGuBHzI="
   "addon/runmode/runmode.min.js" "sha256-Y2iV9vZZs9GsyRIV5HgmKgmfGIsXHpdcknYcbIFkz4M="
   "addon/runmode/runmode.node.js" "sha256-PDnXNUcyprkD1cCQq2Zh3EQrUtACFvVftzjUC3hbasQ="
   "addon/runmode/runmode.node.min.js" "sha256-B6hWgt4QfweJOkzn2uSD7G1Gpm5nWkVvzA4n/Q1piEY="
   "addon/scroll/annotatescrollbar.js" "sha256-6VKDxACEG/VBEEoHB/i9Bpm701ewbxleeQ/jQbGIwZQ="
   "addon/scroll/annotatescrollbar.min.js" "sha256-oJXLGidjXaMY+KagVULyv8+Qyl0IwthDioXjYm6aTu4="
   "addon/scroll/scrollpastend.js" "sha256-cmWh1ILZriATVeFQYHH6LTD2o8abdfxEQD98mmKA5L4="
   "addon/scroll/scrollpastend.min.js" "sha256-C0Xd8WaQkc6EDRzXRBqFusr5cwuQ2H6W+J3LECsbrEs="
   "addon/scroll/simplescrollbars.css" "sha256-4mk7VKvNIWjYnA+rExxtLNsjGaPPI5ql2BRAgmFxQ0E="
   "addon/scroll/simplescrollbars.js" "sha256-/7o4/NT46vmK0Bq8q0HwLzU8pOxBkx7dv6y5NStFZnI="
   "addon/scroll/simplescrollbars.min.css" "sha256-mwlFNgqVxNf433eTT8tkECnE+Eo1kHwy3Un2T1vXL+8="
   "addon/scroll/simplescrollbars.min.js" "sha256-GiemzoAX3ad1pIWASRpa5jC6/gdjB8laEUsH8gOYZfU="
   "addon/search/jump-to-line.js" "sha256-sMswWLNouxW7Fh3iOm5ozacSAnrp9shgUaJomWMlDtQ="
   "addon/search/jump-to-line.min.js" "sha256-6hE+UvbWF7EVpwVlstz+DltSX0qu32C/v5neucv+f0E="
   "addon/search/match-highlighter.js" "sha256-PtGq+Pjt1eqhreHfFTNKzfdrX59WufE+PQRJIJop9XM="
   "addon/search/match-highlighter.min.js" "sha256-nT7pP8v8mTw7Ys942IYNcZc29d8TINebm3UOMmhmbok="
   "addon/search/matchesonscrollbar.css" "sha256-7k376bbRF3oe2V8vbJRGVHFlfWW2fmcZTeUdsG3Hdfs="
   "addon/search/matchesonscrollbar.js" "sha256-kdCvtWaNUvrVp2n20mQKLTYeZkzt5qlqUP7dIJQzD0U="
   "addon/search/matchesonscrollbar.min.css" "sha256-TrmQO7NczQagS8xzbjbgGAhtCl3w+Bo8M9iTvx9uaVM="
   "addon/search/matchesonscrollbar.min.js" "sha256-tdQx6PU3sfGTQFOEx4FvtfXwGR072n1rrlSLTLshsFw="
   "addon/search/search.js" "sha256-BR2INYrPFVWkQhwExZ4kucaIBMtLr0XfADM62n5JoTY="
   "addon/search/search.min.js" "sha256-/czGuANTrW1J7vOx8L81FNl1gTSXJDgjtMK4N5cjHTk="
   "addon/search/searchcursor.js" "sha256-CYMVSAOY9xjY1R8ZusDwQpGXq6wTQ7HR8uW1cjPSSqI="
   "addon/search/searchcursor.min.js" "sha256-HAe97uFbmd4iTdzH4OZE+y8NrO4LX/ee3LBzlN7JP2M="
   "addon/selection/active-line.js" "sha256-iohiSDy7CbDI8RU22sh2ZNTkIQb44IjCHHCHHL4oZrA="
   "addon/selection/active-line.min.js" "sha256-D6+2VpcCER+2VNRAVaEznj9zfwUlvgCp9q+K+ELnry4="
   "addon/selection/mark-selection.js" "sha256-NnFqr8e0P5V7WnDLUZ3hXXR2hNKTmikbwzizACXl6Cc="
   "addon/selection/mark-selection.min.js" "sha256-nXoOAiLRLkLHTSEYfKQLMt38z/jxtS06mpBDzURqGgo="
   "addon/selection/selection-pointer.js" "sha256-JQYMP+6kys+iARNZpsQh7v3O5Wjyb3R9vZhwPTtpRiI="
   "addon/selection/selection-pointer.min.js" "sha256-4u4VoixfrblCaVrBNZPItJHM/0bWK4sXzVRvPpD55Ng="
   "addon/tern/tern.css" "sha256-EHVCqzFKHbXrnuhPfyVD+fz34D5HKjXt7yxf2E+jCPc="
   "addon/tern/tern.js" "sha256-fqhXCyvYSl/dkcIM/nXFV3MCiWTpglSWh5ApaSjX3rQ="
   "addon/tern/tern.min.css" "sha256-2dzoWHy365VBzDpLd3cSZK7JJLX2J0RWVQCMFDsXbnI="
   "addon/tern/tern.min.js" "sha256-kRtSApetx3XsMARB1gKlrabs71WBWa1ZGmQyQtecauQ="
   "addon/tern/worker.js" "sha256-fzSe6hA/umpsaL1TC2RdLS6aQCXLwz7PVilEVD9Dfx0="
   "addon/tern/worker.min.js" "sha256-vRVnv44pgI7qov55lr1QAy2tRCCppxWNuCA/BspqcnQ="
   "addon/wrap/hardwrap.js" "sha256-dnzBzO3COIQVHqDgZjvDvR2osboDtP0CIuYAmmeDuQE="
   "addon/wrap/hardwrap.min.js" "sha256-H+/2mo0c1mdaM8n8PHGEDFBCJ3xp5Biw2h8HGiqiYrA="
   "codemirror.css" "sha256-o/v/5fOniPHMAww4EAIfRBFV0SeoqksPrY0Yq5x+wSM="
   "codemirror.js" "sha256-ysK1w5XQxrYyZ5VZ/n4tOm0j1orTRiV8o07z0CnPhdk="
   "codemirror.min.css" "sha256-I8NyGs4wjbMuBSUE40o55W6k6P7tu/7G28/JGUUYCIs="
   "codemirror.min.js" "sha256-OMbqhJ5GYA+UQ2a9UE9iXHA1kn3hlZCFL5aZmpSp/+M="
   "keymap/emacs.js" "sha256-mxC5HNWo0SH+2xE5jP7gdQS+r5utwcLgngMyt258o4M="
   "keymap/emacs.min.js" "sha256-K5BnsovpaWdNA8SHHOxxNk+Bm8fgHPzzF+XccJ+2maw="
   "keymap/sublime.js" "sha256-dc87Pn7+EZeJ3esJyY/ST5d7TvLRFSs4jpNUmPpkSWU="
   "keymap/sublime.min.js" "sha256-zJxWvNrxO5omOttTETUZ19CMC/4xMifdbJY23gkILyo="
   "keymap/vim.js" "sha256-5C0P+b9ooz39QT9meg2T0BrEZxiC2ZvmrbdJEJnGyLg="
   "keymap/vim.min.js" "sha256-6vu8XtsEGhuc0G1of7xKj6I0/Xj2sJD4DckX9W3MYHw="
   "mode/apl/apl.js" "sha256-7m9IzxiU1IdNzw0TylQfNeSaEoSBE77PbPxtCw9+e6M="
   "mode/apl/apl.min.js" "sha256-v5TBMraIHc82n/8GF009PGvQRw2GQQfKsORTf78erhM="
   "mode/asciiarmor/asciiarmor.js" "sha256-LfTDggjBOsb2f1AfuK4A5AtsZKAzHDECmmvcfEmacNg="
   "mode/asciiarmor/asciiarmor.min.js" "sha256-ftpU+UmxHnCpd+o8se6YUzvFxTbwZa5cV7twrdLPJiY="
   "mode/asn.1/asn.1.js" "sha256-c7oZwuJqkTPZI2/6lH+mU6X7ray/JiBhv/m0wavToBU="
   "mode/asn.1/asn.1.min.js" "sha256-sCyPHB+jfYjmVReYDceksz+pWxusDmNhw89fHArZKnw="
   "mode/asterisk/asterisk.js" "sha256-BYdsupIN2ljW9hEDoPsbLnwL63dojbJt8aPYEif+imA="
   "mode/asterisk/asterisk.min.js" "sha256-l/RpVlOm2JawS1TObXEVDzYmjn6pHK7A3GB9Q8rWmwo="
   "mode/brainfuck/brainfuck.js" "sha256-2OY6a0sYponb3I437b2/4JZjaoi8KhvRAdE3VDZXNvw="
   "mode/brainfuck/brainfuck.min.js" "sha256-RqnBZFb+N9KMVb0Cgz+28Xo6gPcvOU/QWQhKB2ZJT3w="
   "mode/clike/clike.js" "sha256-+Cb9441iMe+C/jLLFLHjwY+nWSAyqx/DA6T9lqShvbU="
   "mode/clike/clike.min.js" "sha256-HnYJC51WJ0XHacXrq46EjSN7w8oLeRkecFIN8fVl0D8="
   "mode/clojure/clojure.js" "sha256-mxGs7dknqLX2+pGr8JI2v83YVoZF/8eolNYD3mqa0II="
   "mode/clojure/clojure.min.js" "sha256-Ug5uOAckc/MdY3ZQYBULxxDucTLqtWK3m5c78WutiB8="
   "mode/cmake/cmake.js" "sha256-kmDuQI4yNY9rHLBI5ZkeHs8HDsfbeY5MghH8rF/o91Q="
   "mode/cmake/cmake.min.js" "sha256-T43UDUHxJnr43FDa46LDFLrstbJ4MyhiFetfoaXmuVE="
   "mode/cobol/cobol.js" "sha256-9Ic/+ob0IV6x/v1yZ5SAerX7ZSZsL1mpHbHRMkxUbYs="
   "mode/cobol/cobol.min.js" "sha256-nMT5LfUBLG8BjlLxhrMcQ6t/vabG5S1F2tvslKpXFJw="
   "mode/coffeescript/coffeescript.js" "sha256-y1Akd93isc7rAMQMsnwT9P5HnMT9HVDQamLkgB2pGAo="
   "mode/coffeescript/coffeescript.min.js" "sha256-Ik6eiCImzxKYiS/38KT+GbDtCJDmRx/fIRYfwPr2XNU="
   "mode/commonlisp/commonlisp.js" "sha256-LzwOjMvF/6WjnR9U0BoC/va1c1J4SlBUfI6sE4nNZL0="
   "mode/commonlisp/commonlisp.min.js" "sha256-U3cLcv1yqKHIHh7grdscea2kAL2crITxHjsQwgf9ldM="
   "mode/crystal/crystal.js" "sha256-uewZ7eltLgYF+6qJbq/6tkXP9LtaYvAjqU2E9SV6GVc="
   "mode/crystal/crystal.min.js" "sha256-vwJaJns46CJ6/CG+lNZfGb4Auo8kiwQgwszoaawt/NA="
   "mode/css/css.js" "sha256-X/teJqHXUccfWWBeziAxGjeC7jl07Gaw/FkzK78UMs0="
   "mode/css/css.min.js" "sha256-mSK/ZI2z8KrKSjKaCmUIVLJVH5ocYo92K8Zjam/tCyc="
   "mode/cypher/cypher.js" "sha256-js4Ckpl/2qFEvY16WRUpCw/ObuUtsNCvl9TzfFbd8Fg="
   "mode/cypher/cypher.min.js" "sha256-FIPOJ1xAuSVyHEYkj4x0YQxqgu1iD0LKtMtmrPujuns="
   "mode/d/d.js" "sha256-Jkxc/DYsxYmdbymb05i2qDDe01RFKlsdGpIC83Ayueo="
   "mode/d/d.min.js" "sha256-pl6HzecQQBVh7FEMs59sX9wvV7en4ApIKWcrqt9sPGU="
   "mode/dart/dart.js" "sha256-qQ1cAEErxrvvLb/lxeHjnjMrDZD1QeS66/0YFGlCENc="
   "mode/dart/dart.min.js" "sha256-/Pse7MG9t7ZNF0KmGmqCl/X8XT/Sv+WnUYAj0oMQaWs="
   "mode/diff/diff.js" "sha256-2F31g3FYKtnqJWaH4rWXEOFqiXdHNXS53t9nFMv160E="
   "mode/diff/diff.min.js" "sha256-ZENzvXTQ4tcn++uUN8/QVlDGfcpN2ETaXPWVQTV1DYw="
   "mode/django/django.js" "sha256-DVVviz0fsQP0GdHl/+rpKrNHRTM1+C9U7b6f78brOIM="
   "mode/django/django.min.js" "sha256-nLr6+psgWgm4BnA7O+W9T0gldFRHCI+H3cByx/TSabE="
   "mode/dockerfile/dockerfile.js" "sha256-GM1iFMzwyJlorCHHfU9rBbSZFuwKQ8YK2IiogUHFHtY="
   "mode/dockerfile/dockerfile.min.js" "sha256-Dm0fFVaHZ+xK5PSOEB8wZehrxcwppok4UwGOktjqyWc="
   "mode/dtd/dtd.js" "sha256-XDJsJa99pw5AkMhvR8XMs0pBY6A1B0N9fLwqPkJynSc="
   "mode/dtd/dtd.min.js" "sha256-Wmm4Pcudd05vW+DL0Ne4fhHoiy3HXLLuXrjJ3SJnJRM="
   "mode/dylan/dylan.js" "sha256-M2W3wVSzLFU0fwCfJtIvysnpmVhzJIgPue4qY3SD+Ig="
   "mode/dylan/dylan.min.js" "sha256-MNnwM5klvXXTwJdmkXsYVVfutY4VgvB18UWS9ZHL22g="
   "mode/ebnf/ebnf.js" "sha256-7BD+VM8SA1naAdV//WhzAnOnAYykv52D4W6qWQ89bpg="
   "mode/ebnf/ebnf.min.js" "sha256-YaJ+YIJ0qAXZ2caX/Eu1d3o1ZSWYMOilMlj/1gnVLzM="
   "mode/ecl/ecl.js" "sha256-yyQugsnBKS2c8H4+h/21QZsnX5LVDxW9gOopYfIYHHE="
   "mode/ecl/ecl.min.js" "sha256-LM6M0iwtHyTZIst+yw4Ru8alv9F3nQvyuZGTn1V8JLQ="
   "mode/eiffel/eiffel.js" "sha256-5LH6gZiss0LNAFll0pCJ8Qs7I5adrxMFMROp6eto5hA="
   "mode/eiffel/eiffel.min.js" "sha256-GnD9fKPR5Vhj/fnw+FvTTZ/4Z1CpYIAlxiyHXagx+Bc="
   "mode/elm/elm.js" "sha256-ZCKOe87ofDt8grtxVPgrGUNAcKoiAn8Ac2JRHGTt/Jo="
   "mode/elm/elm.min.js" "sha256-t1FyVYQRRKAr/N2iQ+NVLsegEyh3OXjkPdcz591lQMM="
   "mode/erlang/erlang.js" "sha256-Gmfz/lZv+yY1RGD9SUMWc/DzzRCDUlR60gCZ3lKvgPg="
   "mode/erlang/erlang.min.js" "sha256-kbjapBaVQkbdYH7kLAZxhWmoAmLYy9XqHS0ouvre7wI="
   "mode/factor/factor.js" "sha256-qBfHep8EhMYTH4DbED6BvwMKXugMqEQntC0nHY9+mj4="
   "mode/factor/factor.min.js" "sha256-RyZM/j9xXCn5KslgW+r9Qc8QW3+fJbpc838i1XnRUtI="
   "mode/fcl/fcl.js" "sha256-oaW/EMukeXCgDu68Klf6wM55V0mPb6ZtdT6lObesTBM="
   "mode/fcl/fcl.min.js" "sha256-J56mpD20q27/MZT+PR4bTXdXMcYkcxw/X0hByTwgGTQ="
   "mode/forth/forth.js" "sha256-KdVmlslyc3eUXMKjLP0M1bF2FmSvYKgRc+1B8n96rqo="
   "mode/forth/forth.min.js" "sha256-SMpW+K7IrQTpo9Rhee50Lk9WNBscWwpVG2itRfk7P9Q="
   "mode/fortran/fortran.js" "sha256-KMThBYnhEbXF0drQclyqR6YDqQDbEVVq7J2SPxCF16E="
   "mode/fortran/fortran.min.js" "sha256-BNcYTQIXoebDkqh4neDvL+HUQ0zkxEVqULLzOBc+oOo="
   "mode/gas/gas.js" "sha256-eakSwZaipV1g2288l2/s3DRasn2FICWCysd8ETg4/W8="
   "mode/gas/gas.min.js" "sha256-uKCSFcQfvUi1FEYA7D1B0o4A7iqXdlOqvcpjpPtreFk="
   "mode/gfm/gfm.js" "sha256-rO+CCK4DSzAcvmVDtTatnf0/IoX2EHtzpzrTYQbkAqk="
   "mode/gfm/gfm.min.js" "sha256-0Ggqa3GGGOXc57TIkMr4P/4SwLhOtAoxLeTa7OLgsfY="
   "mode/gherkin/gherkin.js" "sha256-XLsiqzzJYYDlkE1DxxewHt+HkeXWd+fs7zbJgWC8Wog="
   "mode/gherkin/gherkin.min.js" "sha256-OWfXxlA8DUk202dsfol7EkslGU14KfJwHckx4BbAxZ4="
   "mode/go/go.js" "sha256-Tgg9NnYSrp+/RdVXp/tw7yqwdONCRJNxfvavRgdjzdg="
   "mode/go/go.min.js" "sha256-LBL1h22WucB9QjKe005hUT+iIFxsmkeJsUPeJx2rxXg="
   "mode/groovy/groovy.js" "sha256-TBPRYPsGT7V13sbHcI7xysTqN4O/cfpEklrtMXCGeOc="
   "mode/groovy/groovy.min.js" "sha256-CPVyKVPDvc3yfoSRWHdq91eLJrynLVCvg0vqXaq+D5w="
   "mode/haml/haml.js" "sha256-t4KY8rrGelrQJ514hL6wD7IPEEyveglplJP7+380u6w="
   "mode/haml/haml.min.js" "sha256-jXsjizyBsOnU+/uMGFU12LreuLcOD/Htf7tEDUO6PTc="
   "mode/handlebars/handlebars.js" "sha256-wQp3HlFMesBfFA/jgcZ1NCvtIecAThOo606f4UoEViE="
   "mode/handlebars/handlebars.min.js" "sha256-ey308bGpWaKwYU1pzUoHhJQXN6opTj9B8MykGAgRHsk="
   "mode/haskell-literate/haskell-literate.js" "sha256-DPzGmqNM/kJqiNGT9NRkdKaDO1zO77bwp9tb+TbPqb0="
   "mode/haskell-literate/haskell-literate.min.js" "sha256-F0rTRP39gAJdof3ivAgqjWP0AU7xXlSuJB7buvluZmk="
   "mode/haskell/haskell.js" "sha256-vUABrk9gwU4gUnJKkPd2qBp6zM8+g/t9HfYrBN/7dJU="
   "mode/haskell/haskell.min.js" "sha256-NooFCpCL2KxZYrq4kbRtakn2QThAheUXgL9Sp0O5pso="
   "mode/haxe/haxe.js" "sha256-4Cefc5K5EB9QtBqbiL28tKVVH9zFs4oTB10UTVDWT4w="
   "mode/haxe/haxe.min.js" "sha256-5bl0JuzdxwGn3tfH3Zln9c4bm1HbT9eNJ9TxCFQbIT4="
   "mode/htmlembedded/htmlembedded.js" "sha256-gHZjcn6f753YtxMQ2D/C9U3IQfZJ18sV0YZfOch6SkQ="
   "mode/htmlembedded/htmlembedded.min.js" "sha256-4AUZAaMrhDJ2U9C5HDOe+v/ZGe7n4gr3LDNgvOI3gQk="
   "mode/htmlmixed/htmlmixed.js" "sha256-f8d4CdOwbOelZ6ITbg0IfZXi5beKJ67Ptpo0INBjcGQ="
   "mode/htmlmixed/htmlmixed.min.js" "sha256-9Dta/idKg17o/o0a3PEsL6JjkYvijj9UMh3Z86HhUcg="
   "mode/http/http.js" "sha256-NC95aZoajOMJXd3aBJx9BOLH86QeGXt/1mc4s/Hz4qg="
   "mode/http/http.min.js" "sha256-KO2W78tiaz/x326AcuwED8jZFqztNaV5xxX323uJS8Q="
   "mode/idl/idl.js" "sha256-JeyGb+cekh2Oy7RYLXYPLC70ERanSWhFKNdqqjECM5k="
   "mode/idl/idl.min.js" "sha256-7zsENAPAZvrWI2V2+p/EyMbvAtWBpD7U5IzJJusmN2k="
   "mode/javascript/javascript.js" "sha256-Wyfz01iV9zeqL2igt5tF1xuVBaRZJhsd/6N9Ap/i5sU="
   "mode/javascript/javascript.min.js" "sha256-h2CaV12bheEEc7Ao3zF6MntAbDLJkPoFR+h+nHvQUqA="
   "mode/jinja2/jinja2.js" "sha256-zBpDN6op9Pn+qPoIqV7twhnGXQ8/FQSpVNBrg31IYSE="
   "mode/jinja2/jinja2.min.js" "sha256-OHerIXQDYTPcLnBOdiYExMCL84uq+oXB2VXyMwOGWlo="
   "mode/jsx/jsx.js" "sha256-Xidu5x6m2078lwW01dBOsxtHBSgCIE0uu6Gs8f6fgwQ="
   "mode/jsx/jsx.min.js" "sha256-zE62zttncjkGwzl9bcnOVS+zs64/mcP2Q2gYhNEqDZ8="
   "mode/julia/julia.js" "sha256-7wzTFVaSuom0gfmC8EW9ehOTcKfYwpQkjPn3JT/uGQM="
   "mode/julia/julia.min.js" "sha256-QMBPrPmXc9pywSyFvaOtOCBfdVJ5dvgPJu53XlK0ktY="
   "mode/livescript/livescript.js" "sha256-BklPcL+Cbz7IZRNAhLm3x7YbRUgWVCXl+3Ij65GGwrY="
   "mode/livescript/livescript.min.js" "sha256-IAR1P6+/2sDKlI90bBik/OvvWQqkljXE5EW5uattgHY="
   "mode/lua/lua.js" "sha256-kzZj+xtGOwxUdlNV8A140tPlUEiJLVDDLzLxWVXc4V8="
   "mode/lua/lua.min.js" "sha256-LOPzZDM7I7GCUcDVYX03VUXJVrKe0xvmf16sON+7QFs="
   "mode/markdown/markdown.js" "sha256-TQLqKhdPfzkZof0fueYFPUq3pt2Tsb16PyW8z06zM8c="
   "mode/markdown/markdown.min.js" "sha256-BZXkUzlSBobUXEiSFbDIbTc/DOqhNdegF/iK5m99kbk="
   "mode/mathematica/mathematica.js" "sha256-Kib1dtZGJ24G6lfSevKtrak6SRv1yNO3FBsJnUHV6Fc="
   "mode/mathematica/mathematica.min.js" "sha256-HOYpK8yAw/yPBCR4PWnvm84wl1hI2Bf+hWPhkwZH6cw="
   "mode/mbox/mbox.js" "sha256-AediJu1405X7Zyt8G0+noKrKN8nI6pqUmBG3SVB+LVg="
   "mode/mbox/mbox.min.js" "sha256-ZdZUpyL4ABqtNuINvijx5zshbEN0G3oAB9cU4sPiWqI="
   "mode/meta.js" "sha256-RM2UdNwztCIJ51VS9I53w7iw8HlgmkhvjvUp1fowK4M="
   "mode/meta.min.js" "sha256-wGlUSYXJ68CdXy1F6BnR0q0LVwOfoyJ16J2g+sRDoJY="
   "mode/mirc/mirc.js" "sha256-zXYc9wiuv65xnsax0m/iFgPWhtV7AyN17oTIVEcjfJU="
   "mode/mirc/mirc.min.js" "sha256-IPAM0dEJ6HL96WQe4T2ESim3bFwAnvkKcDznGiM0DcE="
   "mode/mllike/mllike.js" "sha256-6xHwY7yy3aN63BZmPfYQYhWB803zQZag7JrDVt+9mtE="
   "mode/mllike/mllike.min.js" "sha256-pR3NmDIw6/wqnIcagjMIqnBCO00rwNFLtouSM2FZi1Y="
   "mode/modelica/modelica.js" "sha256-xrmBeXZdwHoRos/0Ug0VN48+43V6hzPQmmRqWEq2FKo="
   "mode/modelica/modelica.min.js" "sha256-JeCKDfN/Hw5IwbXmNlQ4LHlybg+fe7Fl/iW+DwuvvSU="
   "mode/mscgen/mscgen.js" "sha256-b4T+J4KjV0xt2xQJK3lZdZr80FY5t95cKBdptN0hlF0="
   "mode/mscgen/mscgen.min.js" "sha256-MrBl1dRVjoQ0qP/8a75lUhWY39XhCYNzXUGORu/2vmA="
   "mode/mumps/mumps.js" "sha256-nJd6G0CvKA43BvJ11CzxU+V1ClPpEGWPo70BEbuA+Fc="
   "mode/mumps/mumps.min.js" "sha256-1S0bcoMrlYcsrE32jKsPbn6RLKsQ9uJ7Vbg/4b/WWdg="
   "mode/nginx/nginx.js" "sha256-G+Ug3RrMPyQ0mK9TncuCjUmxGnsWhobUaE5biut5H8Q="
   "mode/nginx/nginx.min.js" "sha256-oY7WnIzINBrIAx8iUdYkoT+eIluav0jRdms+sQ1Iwgk="
   "mode/nsis/nsis.js" "sha256-JGyFBFPXCmZJRpZuazVqw//wuYd5PjFctJO5GERmF8g="
   "mode/nsis/nsis.min.js" "sha256-sVZ64cxQaCKXVV/p7baSn46YzISKz2JooqSfjgnSxhk="
   "mode/ntriples/ntriples.js" "sha256-UdCrgf3MvKbmYzyE/HBleWyJSa/Y7gialVIoHr/VOQg="
   "mode/ntriples/ntriples.min.js" "sha256-So5FrHrkJ08b2RhKCBB4HjJFEkHliQPh7zzaHPK8uSU="
   "mode/octave/octave.js" "sha256-8BuxDGPTOKM1S+148ZTw0ZF9S2NBp89T1spcIqdQmLI="
   "mode/octave/octave.min.js" "sha256-c/EYralg6B3NyvuknL+/ly57AXutIUlRgHHBk8RBhpQ="
   "mode/oz/oz.js" "sha256-DAcdTwphHCai+LbIkfFhMIxeroqEo20tvybuFeWtYHo="
   "mode/oz/oz.min.js" "sha256-SgTB8DEd9eXWPk0GQyJOc1+2ZPvnV7GuExlr1eiVqbU="
   "mode/pascal/pascal.js" "sha256-8PSOhS43QSc+EJP51QRqeTre3LrV29nzVLEuFozx/Ks="
   "mode/pascal/pascal.min.js" "sha256-RCTcAYEOeOOvHbaIi0CMlRZ6qL1xc7mubaj/xLWxCKQ="
   "mode/pegjs/pegjs.js" "sha256-/QAVfHVu5vysqmL9iIeWSPL7VpiKYE27XPGheUJ+b3U="
   "mode/pegjs/pegjs.min.js" "sha256-X3RIVKGjKe0szzN0v9Dr1SaWbGe1hcVH+Hn8m/1phcU="
   "mode/perl/perl.js" "sha256-BDdsIFq92XDfSvMqxm36Z9U+wSFTw99ixYrX2onjLX4="
   "mode/perl/perl.min.js" "sha256-Uu9QBfi8gU6J/MzQunal8ewmY+i/BbCkBrcAXA5bcCM="
   "mode/php/php.js" "sha256-/cMV1RtNvAFIFZiNvaAaREDmRLiv2DEHfRwrTd0SE5U="
   "mode/php/php.min.js" "sha256-+nxP4L++LvoKp83WfkYStNk2opxIDQ0KkSk2mUs8Ln8="
   "mode/pig/pig.js" "sha256-9g7tkzTi5zhloKENl+fvAtuVs3Q0QoxyMsnz6kadkH4="
   "mode/pig/pig.min.js" "sha256-zh4PyxFhWZty2+gsTa+gI3VRzA35pk1mCB3hOar0+PI="
   "mode/powershell/powershell.js" "sha256-44/7hHdSPWE6h4BmhrVVLd2vnZDd+/UmLsq6Il2V0IQ="
   "mode/powershell/powershell.min.js" "sha256-t8/Yp43jA07gtrK/wW3lZ1m0jLkaUokxWTfM9eqeVGw="
   "mode/properties/properties.js" "sha256-tuSc0pjtdlwFrTSPQorKqpT8IMM3FV4EkAt6voQ/SXU="
   "mode/properties/properties.min.js" "sha256-CRuGXtKp4hp5bWTQLlLsx7I6wlGwlNEUYQdSLD7oi84="
   "mode/protobuf/protobuf.js" "sha256-3oZEgrTFFRM0v47KPtKhls3N2/4snudNc7z8xLrrkdQ="
   "mode/protobuf/protobuf.min.js" "sha256-PUAd0XL+BDIs0dglROiN7ObPy0GDCHN/Xx52M5JYYS0="
   "mode/pug/pug.js" "sha256-l6vO3E2GiQdvlrXk59hTKXJdGlyzu7E5KaE+1jeMd2A="
   "mode/pug/pug.min.js" "sha256-haHxKGolhWowe2iovrcvdQZFcPMZgAk10V2TQrajLto="
   "mode/puppet/puppet.js" "sha256-HfkBBP6kx/bnTHLMFXHr0BiP6R/tml4VWN4+chwgRMI="
   "mode/puppet/puppet.min.js" "sha256-GAgyTBPiGKBvGqslLr5TN/wEWK1Sif6Jj4uYySjBay8="
   "mode/python/python.js" "sha256-FRVUIbIjaaxpXJa4Cc5X/w5K8XwApX2GDYZREuxVm/c="
   "mode/python/python.min.js" "sha256-yFrghLn37/4Ty8nvE3g0oiMD/dOQQIUotOhdfLNKnrs="
   "mode/q/q.js" "sha256-HkuyCHnu0Gema3+BoMwH95Faj52wecdD28pCq1v9K9I="
   "mode/q/q.min.js" "sha256-syyToslHBgMynZumsA/i8l/+fXOTwyEfU4mnG1xWwWE="
   "mode/r/r.js" "sha256-Mvz4t9qXDnh5kDPUhyQJIn+/fQjGhyhSS+2aNklG5NY="
   "mode/r/r.min.js" "sha256-F861WR5O9uAXP9EtuZRdpRuG+KbVqBMZb7W/E9o64+4="
   "mode/rpm/rpm.js" "sha256-LgAKZtLi5No2amAkxxj6RdZgjZHT8G/EhMWkHmoqsv4="
   "mode/rpm/rpm.min.js" "sha256-btS2A4zqsaQllHWc2gcirfVjWo/zBdkP1hPqUexBDHs="
   "mode/rst/rst.js" "sha256-32KdpgNzc1dI/iJ7HGfThRzlgfoq4nmP/mhmKLPdVa4="
   "mode/rst/rst.min.js" "sha256-tCPqdXBPvgD5VyJLnwNBI+c/mrJtfu7CrJQ5d0OjlrY="
   "mode/ruby/ruby.js" "sha256-XsxQVi6Rv8/QmqJxbkvP+r8cnUca2QL9YjjGAePpwag="
   "mode/ruby/ruby.min.js" "sha256-Cg92nHa1qI8QU9ztRAJCsopIlipxNScLdRkFrgwT8e0="
   "mode/rust/rust.js" "sha256-5RvaZ2p5S8wz66x0YZMgAGrxMdTyyY7UcfOpTD51rEA="
   "mode/rust/rust.min.js" "sha256-4BJbmA2Jlt5uizywYWvKL8s+73AOcLz0+iOLHdHXtoI="
   "mode/sas/sas.js" "sha256-jVS7y0mdown0M+slJijLxcG8PV2BiiNiZJunBn5cZQ8="
   "mode/sas/sas.min.js" "sha256-L6Aa/gPLpr9U6hPkkH5mvdrlrP5L7LjofgS4UAAPBp0="
   "mode/sass/sass.js" "sha256-VhvX6GwY3LvSQ+r4FIoRXaKUb3PdCl76C+KcXE0b7ew="
   "mode/sass/sass.min.js" "sha256-/Bb0rTLIsg53ZVkRmn9McWj3sohgWHfbnF+YcMjNYNg="
   "mode/scheme/scheme.js" "sha256-bcqmXT9I8favXrdMZuUA3fKem0w+A4EKcbl1pNUaEw4="
   "mode/scheme/scheme.min.js" "sha256-g+y4XEJlbmT3QaaBQ81S2468quXBaS6Tn0V8lyq0DJw="
   "mode/shell/shell.js" "sha256-kqBOdk2o5u+T6JYCIgJUImg9MKMHZLcl8g/GycBCg5o="
   "mode/shell/shell.min.js" "sha256-mpiU0F7OE/j8c2wz0cWckLOtA1kQgV/LFJyAIM4+uZE="
   "mode/sieve/sieve.js" "sha256-BeYzWk7UlTLYM47FiCnfwhGZq4pLjkILBJSPS0Wu5tI="
   "mode/sieve/sieve.min.js" "sha256-d5d9B7sq57dIvW1mks5tG05rlszW7pna3bq3f0DHE6M="
   "mode/slim/slim.js" "sha256-bvFaw/8A6fLqmCa7bALlsq4sC4h7PBtqx2NhkYeNPIs="
   "mode/slim/slim.min.js" "sha256-NQQ7ZawhjFwkDeAHCC8+Fio+VsI7Mj0071NkIXCes/M="
   "mode/smalltalk/smalltalk.js" "sha256-tB94w4/b1l8uWGJzqdiCuS/QwjmDG6DgeSJroWlu7bc="
   "mode/smalltalk/smalltalk.min.js" "sha256-TQSER/Z8bT1ZH99JAQd6sVfcoZ7JmBeFyNlHDYmt6CA="
   "mode/smarty/smarty.js" "sha256-srY6M0W1f5gd5bV/WvHcHkIK9n4IxdEmjqeylOxfZZA="
   "mode/smarty/smarty.min.js" "sha256-kFOvH6cbxPQteuNGzMtngXGCPRgXlZAZomitfr0Ouz0="
   "mode/solr/solr.js" "sha256-pyWCCsYw+3al2dBxAtJqT2BkFhc/z0tp6O6T3+U0r5Q="
   "mode/solr/solr.min.js" "sha256-sOZReGhRnV+Eur3YThOpvTrycylS7RsYMs6orhHb8y8="
   "mode/soy/soy.js" "sha256-OL7ekHBrXUtABVwiKg8Mqo8DKxeHH/9kg4RfU3Q0Oxc="
   "mode/soy/soy.min.js" "sha256-6UfQBi9nRQEpvn/wpA5+o+w+Daby0xG4bFJjI5EmRcs="
   "mode/sparql/sparql.js" "sha256-CBouRoqXfCd6lRbgav+79+hTi+7k/gZhi12QKAZlonI="
   "mode/sparql/sparql.min.js" "sha256-/cSMA3JfHae8lrjq6Sye7RL5Rv7GIVATB7l5PZEYvos="
   "mode/spreadsheet/spreadsheet.js" "sha256-/e7XXTRoogvCLVDnBMG2xBZphzU8YC5HRFjjFir28XA="
   "mode/spreadsheet/spreadsheet.min.js" "sha256-ocC+phZaMbC7qwcc0vpmdaYESb2qCowDb95IIKj9qxE="
   "mode/sql/sql.js" "sha256-4/XWqvWMy8Rf9I7wUkKh0byWJYvFlldAYiqvLgRCeho="
   "mode/sql/sql.min.js" "sha256-RwVNSHygrfS4dnVU+7yEla2Ql7jpfL/f7+acXtxuVC8="
   "mode/stex/stex.js" "sha256-SvVg6WIBDwsx4tSUtGj3aNlQoZdf0IxhrK7KMs/AHro="
   "mode/stex/stex.min.js" "sha256-eI15TQyAbDpV0VDgZK9hxGk+0ULcB7jxVr5MB38qvb8="
   "mode/stylus/stylus.js" "sha256-XZtgyRdCI9CCkp6Tq02t4Pyq6QiZOAej7pozlmnm1J4="
   "mode/stylus/stylus.min.js" "sha256-agYI8q0vKKBLlMdu3nolrICdzOwVV3PN+e2MEV5nN6A="
   "mode/swift/swift.js" "sha256-EDGat/0yd+jljKGs5b4fTBrOqDMKNZMspl5PbC+FJpk="
   "mode/swift/swift.min.js" "sha256-RCUukMBDCOKL5/n/ADhelLfHM4jchgD0mSX0awdFZIg="
   "mode/tcl/tcl.js" "sha256-4Bm5M2dtc2L4MmVKm6hg3N4TS6pA7p+LWharTlT5xkA="
   "mode/tcl/tcl.min.js" "sha256-mF4IDWE3FHrNlH6YnwOJ+R4Qpd5H8zprSaweaFcHKiE="
   "mode/textile/textile.js" "sha256-NjGpOeRQ4Gajrf9Re1XxUZ17RwqOHUyEs8ko5WYFF/0="
   "mode/textile/textile.min.js" "sha256-LnLTR3yurvHONXESm2wjbByWWWfA64d3umwmNHMCAPU="
   "mode/tiddlywiki/tiddlywiki.css" "sha256-EueEt6mPYxnjtV/48UugkNe9KqLIrA3z68joSMSDAdI="
   "mode/tiddlywiki/tiddlywiki.js" "sha256-1PTjd2RyfDF897Nw5LFSBbJeOa5zKP0IDa42Tqj3aSg="
   "mode/tiddlywiki/tiddlywiki.min.css" "sha256-KJKM47gC6ZWu3z+fx5KkC1c/d7lJWuhpuw0i2ncWYGM="
   "mode/tiddlywiki/tiddlywiki.min.js" "sha256-CdTxouHN/1lh58ti/cE/ZUBPb5dIOjOBwObRHZk0tEA="
   "mode/tiki/tiki.css" "sha256-aa1s7Itfa0aIoEIl0Oa7XrXQJxshXXxhEyPYCTsacdQ="
   "mode/tiki/tiki.js" "sha256-34gX3f0BBF24Ol/HC0YsJiFTm+3j7xOJb/TGDkxcRcE="
   "mode/tiki/tiki.min.css" "sha256-li4iks5iigovkcqNDBEp/XMYh+vzPzgTHi088YxG3ak="
   "mode/tiki/tiki.min.js" "sha256-VVG4BA+WXPvkhRSTILISbduTVZDpXtP/Lj7jStBYLZQ="
   "mode/toml/toml.js" "sha256-n55NxJXCJWNPvrdQ2+w6bbbk5R9WZ8dLvC574HuOE1A="
   "mode/toml/toml.min.js" "sha256-V9wPED6oG548ufRnRr2glmV2GbLGWFXzstoWMLhpnZ4="
   "mode/tornado/tornado.js" "sha256-Vl7hlpnZSrBWXOJE5e5DjbDEvbDJNY58Bi+/02hf5dQ="
   "mode/tornado/tornado.min.js" "sha256-+/TZ4ojEt4SNzMTdGjR7L1acCLGmBtVqNdeoba+JMQc="
   "mode/troff/troff.js" "sha256-dqDB4GZaPPxPLtmqibm4uj6v5/UH2HCZPnNEaKSCeCw="
   "mode/troff/troff.min.js" "sha256-dy4Dr+W9GfFRnJDqrkSCIdcgNDwQtXnsxWxLS16o1pI="
   "mode/ttcn-cfg/ttcn-cfg.js" "sha256-6EDBz1GlMLPO5ldHxth6GbfzE6A5vrrpJ8x//xIWpZs="
   "mode/ttcn-cfg/ttcn-cfg.min.js" "sha256-dcE2FrrX/XJrmWNCdy/GKnRJQGEJt09n56P8QqbDZiU="
   "mode/ttcn/ttcn.js" "sha256-hm5gGrSoDdnLAXCEJG3ECRb8LUTn8tg35ylfLeE5fNw="
   "mode/ttcn/ttcn.min.js" "sha256-8OEudvsVENRLMPyEOyAZABR3JJC3QFAkUMjPgZFQCeM="
   "mode/turtle/turtle.js" "sha256-noHQAetbxqMP64UdNGgIXTFW3ggx6tKikVyh0zKbZQo="
   "mode/turtle/turtle.min.js" "sha256-4w92uG0hqbpcauPq/8EP0ftu06FqL1jX6QCPADZZbt8="
   "mode/twig/twig.js" "sha256-j2w5bpiHmLgdwX0685Sfzjt9juba6l/AOS9ek0o6eJE="
   "mode/twig/twig.min.js" "sha256-eZnmFkywyy2AD0ZXYzP2E5ovBmETSwxQug9P28O3lxU="
   "mode/vb/vb.js" "sha256-6pb+RIKHdHhagZH88CPEOTcjnTBFbv3dfz23VmTwLmI="
   "mode/vb/vb.min.js" "sha256-rKD0Gt83EO3XJwDkJ/XuPqndzppGZPvdQST4fzzFVmQ="
   "mode/vbscript/vbscript.js" "sha256-ooBb6cHpf6t+ME0YZCl8WQh/c5R3lYQrv4FmmI1DihI="
   "mode/vbscript/vbscript.min.js" "sha256-gKm4PRmDEKKOSMVEG9Co43Aax9Xo+UrXcHoYzZiKgj8="
   "mode/velocity/velocity.js" "sha256-o7Y0tDfwqDh+rBWSjNYoNkll2sjk3oecfzGpjP0X0gA="
   "mode/velocity/velocity.min.js" "sha256-C7brASd2bEc+FuTS2WAAJ4vFhUihqT09l3oJL9dlBr4="
   "mode/verilog/verilog.js" "sha256-YX9M43Lu6uWesbFNjcrj2EaZ12P5vndryXyEsEWcC1M="
   "mode/verilog/verilog.min.js" "sha256-RvCUpQNC2cE+Dg9eX/6oFFXorNw9RANCUPgiy2eQ6eM="
   "mode/vhdl/vhdl.js" "sha256-rbHKfM4kUhvxpRr/LNt0fR4FnNrDBXFyE948lkUAF+U="
   "mode/vhdl/vhdl.min.js" "sha256-Sfaaoez0E/2QcxVpDGxcsirNi5LQJO+DK3p2HAlI6jg="
   "mode/vue/vue.js" "sha256-DDj4huqEUN4UWqSxBoJdW8MRyn0g0P9sU3EqruaYm2I="
   "mode/vue/vue.min.js" "sha256-9xlNnSdtHuWcbua1eJhfHifxGYCVrkuDhJunhW+lFNQ="
   "mode/webidl/webidl.js" "sha256-K+Rk3CxIGtGjR7QAtQiH3NwfHFwwJCsqbqng8QSPvWg="
   "mode/webidl/webidl.min.js" "sha256-UBmcWQisRkEO9QGbM+sROZ2+ieXFHYZ0LyMzYVJ9ozw="
   "mode/xml/xml.js" "sha256-YaSCLbgcWefc1ZsXbZnIrK0BQ6lIGEPjBKiMyOL0Y7w="
   "mode/xml/xml.min.js" "sha256-ZhQvi0y/HBV02Ej24EigSb5UyIhHe5COCWNRv/0Bd5E="
   "mode/xquery/xquery.js" "sha256-2RPq4w1NCwpzWWGWlW4LzbWMc/J7/YUee8kcNYHpxp8="
   "mode/xquery/xquery.min.js" "sha256-16P7vFCyfgoz81719n0ASBPvo60DtrABrWqnl365X80="
   "mode/yacas/yacas.js" "sha256-BB4190PuYRh5cLgmk8lb3xSIeGJi2Z42Yd+0hFoNUEU="
   "mode/yacas/yacas.min.js" "sha256-ZjnMt0dAnCXRBYFVYm4DmGJ01x6sKCgJDNRB/lUj8ZQ="
   "mode/yaml-frontmatter/yaml-frontmatter.js" "sha256-2MClwAsObP/p9rZvXUf5CNVBmkh1vgnr4l27xGWAmjo="
   "mode/yaml-frontmatter/yaml-frontmatter.min.js" "sha256-sSmzmaSBkSoyxxTsBHdUljj2hXEnFKXe5l1y4ThcwFo="
   "mode/yaml/yaml.js" "sha256-Q0ephOgt1DzzxnKN9uCDEAhzfzsplcYyy3AI+F06dbI="
   "mode/yaml/yaml.min.js" "sha256-G1Y7WHjmBIbgQctjcuj64V2zVyIS6EVUj4PaIneJcDc="
   "mode/z80/z80.js" "sha256-4JPDs3t2DGIGHnEC9cc1telvJSCDmGOT/Nx4YrrBXQY="
   "mode/z80/z80.min.js" "sha256-0voPskHNiFTu/p3la2Otun89NXA85ZJgRUKnkhrh+F8="
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
   "theme/base16-light.css" "sha256-gjHWhNpKA1HhkhsyWvBgQbeeLk6fCqHQU/FdIUjj7/Y="
   "theme/base16-light.min.css" "sha256-kgFqskFNU4E6ZDC8dRT7oBEUv1nEy7frU5DCrpwyxC8="
   "theme/bespin.css" "sha256-1D2lWnhZ6r2Up3Ne/4IFwTaBLivbA3+FqwJjIP2be0M="
   "theme/bespin.min.css" "sha256-1ERF9HPWazRfJdHaxIjJCI9zZoTcmt1Du33oaxEplUw="
   "theme/blackboard.css" "sha256-VPCBHwDRMLPDKDz2eef73Lj5Om2oiuQBFzkiCoFs5FE="
   "theme/blackboard.min.css" "sha256-leZaLFrsfIwu5sPvJhg10z3vXVAPKRVX3A5sneuWm6o="
   "theme/cobalt.css" "sha256-Os0qLNmu+uyjtBpFHiZAhADg2Vi46EWtS81e31/5AeA="
   "theme/cobalt.min.css" "sha256-w+8VeOcoKzd5XqQTdITPf1v7o1OGGNWu0CUVEFVSMOc="
   "theme/colorforth.css" "sha256-R/laTMhLZM7bA9yvUzdCfO6F3USVgirLMPfhurg71oc="
   "theme/colorforth.min.css" "sha256-pgzYXfYyPJYWjahb4JPg7LLiMWWVWsMY0NrG3xhNhgU="
   "theme/darcula.css" "sha256-3nyhbpUDvEij9scDD3mjV54p/GkaolW3s7RnnSk3B5k="
   "theme/darcula.min.css" "sha256-ZzdkdGSdYSUv4R77x7A5G7EeFDLMhAiCybNBNxNoxos="
   "theme/dracula.css" "sha256-Av32BJYI3djQ189kA7blslEzB0Dfw5N3T2i7ZHJ7PG4="
   "theme/dracula.min.css" "sha256-Dq8vPj3GNwTg9rflLnPQ8bDSLndsRxjmtWRXOwcWR4g="
   "theme/duotone-dark.css" "sha256-Q4Y20vpbJa3+O5FESBoCnm9Lwn9ab4wuLDlN1c/h/7Q="
   "theme/duotone-dark.min.css" "sha256-ogC2zMsvsul0YSNVfC6awS4n3oJletA9pl6Y7AwNANo="
   "theme/duotone-light.css" "sha256-UQ0Q46O9H2lcnrA0qBhoNPmyCYb9jEG8FISljJ8648k="
   "theme/duotone-light.min.css" "sha256-20mLYcyo60y1P1Zib5qlTqzuiCTnr2vyiQetUqiFzVI="
   "theme/eclipse.css" "sha256-WhzJFLQmMeYNz3p+/UEqGJczEHrgZQiYawzX76gjnuo="
   "theme/eclipse.min.css" "sha256-C2k2AGTJsrRfmmxnfxARIMz6/LK8tCUuFcWsG4kMhu8="
   "theme/elegant.css" "sha256-At+BZ4vozwp4DkMDlbD2Trd4/qwiFqOx7KtShIq8j+Y="
   "theme/erlang-dark.css" "sha256-8yhsjdoYypKt7Sqjmtdf9RZ8TAfK3dGR8bqKMlH/fUg="
   "theme/erlang-dark.min.css" "sha256-mjWfCJMlwgB2fOWxytEfdMkMSaEmqOoFskl82HaHbLE="
   "theme/gruvbox-dark.css" "sha256-4/R11Vhgn2EpGNfA5hTdfiC0+LwbFcUy/MU3YZGEzW0="
   "theme/gruvbox-dark.min.css" "sha256-GlhYE+y1lG3+3di3VXzN/DSz5Y7Tqem27YAdCaotpro="
   "theme/hopscotch.css" "sha256-IHTaOxAkWcZLuddgx5DV37xKGAWgJkGf6UlJGK3KXVQ="
   "theme/hopscotch.min.css" "sha256-y/0TXQIqIZ0hoG++DA/wPsPomFGvbULtLBhE4wCyBgU="
   "theme/icecoder.css" "sha256-roJwt17LREB7HKtgxO8zX5YUYkd6qQy+C0qaTPBt/44="
   "theme/icecoder.min.css" "sha256-YhgVplprhSQ5Mp4oMO+JDhKKCNF2viCUwW7Ha5V25Ck="
   "theme/idea.css" "sha256-xSwdYnse1q10KTworjVVP53pby//km9+QBhhgkfLPJg="
   "theme/idea.min.css" "sha256-LTYao9tg/W6fIzk5enrHysqnKPbLUWjRRAqDQBR1d5U="
   "theme/isotope.css" "sha256-BaKfblbb2690x1xYWtZrpvQxPa/Abo8EGzQ/uFFCGrE="
   "theme/isotope.min.css" "sha256-/J+T3wMgXHP7DQmIgvha7lO1sxCUtaOnk2kEXk9TQi4="
   "theme/lesser-dark.css" "sha256-t3LsHEtjqPMa+bye5DYaArW2YM8jisCgVpSu2NqDMKA="
   "theme/lesser-dark.min.css" "sha256-kRDm75ZpAk6HEGxwszCLc7vt79azRsrfybbL4llVXBw="
   "theme/liquibyte.css" "sha256-t5mivDT9dQtsixCj4MQP6i7gNQR5nRO7dUXRHtH1s1U="
   "theme/liquibyte.min.css" "sha256-qYSJO3XeQ/3ODXGFm7KGR531Wd2PJqdKT9f8UZPW4vk="
   "theme/lucario.css" "sha256-o9y+v/e6JWMhuS6ksDT8FcuP4IEBD7xnYL1ZoN/40G8="
   "theme/lucario.min.css" "sha256-aO31JIjxSoIgKxlLjI8i0ms7T89nj4Lr9jTsZIf3+xU="
   "theme/material.css" "sha256-B65EQRwM+BIBHPj7TwOj1nATEaDSqZLBORUAcW7TDl0="
   "theme/material.min.css" "sha256-UyTiM5wwtuGiISIGyvkdYa9kgCRJmBQ+OYU72oexofc="
   "theme/mbo.css" "sha256-I/IhbdLU/62KbOwg3edJ+Q2vh7RbmPNeGYFuVFUoMcM="
   "theme/mbo.min.css" "sha256-OYT4UbCbF6F6lwyhjE5DLw7uUG30WFLsqgXzjVhKKq8="
   "theme/mdn-like.css" "sha256-D+Tv2jQlIWS79Q8rRM9R6YWvJ5KBUDENzRAi3zvuZP0="
   "theme/mdn-like.min.css" "sha256-NOY3wYv+L8FgjrwUjZgi4StO4CvnI8F+NxQwFhhstH8="
   "theme/midnight.css" "sha256-xuoxwDY59784w0BV0dI6ryAfEGHm77omK9Qk+oZ75XQ="
   "theme/midnight.min.css" "sha256-kRJfLCotf0zxZ1yktcjFbuFPF5lZ728kqWqNJnVzSk0="
   "theme/monokai.css" "sha256-JGPcb9kgGaDHyiqqAdAxFrKA+nxq4BvyHffBB9m2g+g="
   "theme/monokai.min.css" "sha256-jAukb65gp2gG6t4FtxpHgB3eFwM/rDUwrE2OaAp6+o8="
   "theme/neat.css" "sha256-WMLC5bxpwvLiouYZo3maC9cKh1TBNxBNqrSjnlP0JQM="
   "theme/neo.css" "sha256-qRnm2qfT/Q52+7DC2rgmj2+ZjTgb9s12gC6tuLb0tv0="
   "theme/neo.min.css" "sha256-UxYezVsL3n+/0xULVJyFnX9xaeX8Zs3N8oJy9WhDsY4="
   "theme/night.css" "sha256-DF3eQXEFl768TkITJEuIi+d+5FHH45TYo6LqmohwPuU="
   "theme/night.min.css" "sha256-gwhrVzvfjY9YVgWp99y/jUyvJoZLHLx07uQTjXT7v7Y="
   "theme/nord.css" "sha256-ojj6Xxcai2EhcOtnz90PHExzGsXhDRDqb98WQt0e4bE="
   "theme/nord.min.css" "sha256-BbBFKIUomOA86tvzlghVTZ5KsRHqCz/PVgYJx1JqKSY="
   "theme/oceanic-next.css" "sha256-pDV/AgrbMP94EOq++oTaMvkQBSsFpjOVgmmEvfI4u90="
   "theme/oceanic-next.min.css" "sha256-nfwSA7wAavuKJcJIcnXOt2g0jtMAoEF8oEiDUjDe3tE="
   "theme/panda-syntax.css" "sha256-xtWCchZ3fiwwFMBxEKcIJEQ3mxFSYMbU4CRM+n7d+CA="
   "theme/panda-syntax.min.css" "sha256-6GojLBMWHTWMSHSVZDZQqsF3LesVKpEKTalDRlFm3oY="
   "theme/paraiso-dark.css" "sha256-/FUfGjLKEdQCOvbch+43hfu1/lBEzawSBX8K+cFzHZc="
   "theme/paraiso-dark.min.css" "sha256-qZ3eSOsXWmFBQ87+cSEtitcNQz5SrnaP7Ck2+vtfNBA="
   "theme/paraiso-light.css" "sha256-Jf+TAfSDF9E1hvdMleuOZl+w6OE2S0G2IZ0hipbX9ag="
   "theme/paraiso-light.min.css" "sha256-rDjysMGhr6fQYvuXhzJy2/7Q9VKi064CklDCA11bmrQ="
   "theme/pastel-on-dark.css" "sha256-0T00SJmFoqID0yHxWvuPa2gbdOFrgejIDIuK2d9WFZ4="
   "theme/pastel-on-dark.min.css" "sha256-VUnPtGtiUwEqD7klqFHX56HCmWuinrmYcqh3juZMnvg="
   "theme/railscasts.css" "sha256-6bouQoqwVHlpwCwudvvcx+opYWViDGUrIg+nQnmJukg="
   "theme/railscasts.min.css" "sha256-F4plg5r0MTIXbNJ+KErz9aTJojKh8drwZSWRUBQsKOU="
   "theme/rubyblue.css" "sha256-3Y2eMkt+BNEUyDzrgwH4kumdzi6eF+1qGbtMFiK+OEw="
   "theme/rubyblue.min.css" "sha256-XhtaNxZnQ6BpComTr5QWsTfIIIopFceclT1C3ZG706Y="
   "theme/seti.css" "sha256-evT5j0L2vPdKdPYG7Nava9xsVUm+JY13Jumok2e10S0="
   "theme/seti.min.css" "sha256-xjAcF1n/vILepIr7sG99LhGpLw/QlTsiDX4dOUy+5k0="
   "theme/shadowfox.css" "sha256-WNWIRi/EC78JBJGQf/5mlLCUBsdviLe7COesxn9JeQQ="
   "theme/shadowfox.min.css" "sha256-D9BRwKhRo9AR/+diFbEnxBJi8Y1fg0aJO5F+oD3RuUg="
   "theme/solarized.css" "sha256-kW0aMQhnt5nTaAiBhDAtIlAvtr1+kt5JPxmFMLaw+Gg="
   "theme/solarized.min.css" "sha256-v5CcBJnFb3uNFDq7uhR4sIS7yihsXlBxN+cwxjtzp7c="
   "theme/ssms.css" "sha256-+W5UiuVT0DMS/q+/TEqr7zsCbPh/A0audU5eofl9fhM="
   "theme/ssms.min.css" "sha256-Ipk5DdtCmUffrrYbiYmiwzodHaEhNFaKwnmWqNhNWXg="
   "theme/the-matrix.css" "sha256-/xI8AR3KXFxLGudlD5ivvw5O60n1oCxE6EioAqxf0Hs="
   "theme/the-matrix.min.css" "sha256-X2MvIbX+KIUGVMNfKJUJzppWUWQevbZoxSfRTD5x/2k="
   "theme/tomorrow-night-bright.css" "sha256-Revd/CSbFMIdTmf0ZxgSrIdMWfEftOwn8gw85Q57cQM="
   "theme/tomorrow-night-bright.min.css" "sha256-CX/nGkDL1Q6SZMhcceb+uz9YjCo/KvvK8JLc1KUea0I="
   "theme/tomorrow-night-eighties.css" "sha256-awFdMeqqoIvTXYVYLOq9Ebwrb3CMGgUsD3qUUM4jZrE="
   "theme/tomorrow-night-eighties.min.css" "sha256-7SBhcVeJtwxWqpMtbheE5oEZZvoYIARSGMnv3ZhgCkE="
   "theme/ttcn.css" "sha256-ntAjlfKm3p+WzoFLZ65zxPrKrWwMSPfP5lm809/8UxA="
   "theme/ttcn.min.css" "sha256-dMHvUsjtr4ftKlh6ykib+hqrbcudHexR+1W2AZoMCys="
   "theme/twilight.css" "sha256-ltApMINjtnG8JfMP1DnRcEzkUFuMNil+PKQRoARd8js="
   "theme/twilight.min.css" "sha256-8TpPA1Pd/d+8+15cstGThx0apy0d6lzqqyAOTUK0wdg="
   "theme/vibrant-ink.css" "sha256-YeqS2tHXEEKY2+dJ2KAvqX6ovcFhbmblz3ALjsCGLJ0="
   "theme/vibrant-ink.min.css" "sha256-Z+kRZfEzKYbF0a/HZAD4dtXvcHR3WWgaLXMHiX4fmfo="
   "theme/xq-dark.css" "sha256-ysjoXXYVnEW2Y/IpzAcFfiibzZ7aQvfH6citOV1+jHQ="
   "theme/xq-dark.min.css" "sha256-WJk3D55i+W2oyk/nZrBBsVC2q800hEw7ctjR1Gll2kc="
   "theme/xq-light.css" "sha256-KWl1QR5Q0aRIVE3Y0JUD4B6h48uRxo9QcYLCA4swLTk="
   "theme/xq-light.min.css" "sha256-FZv+ila3KRQWhDfAJrz8dopJnGG8257q0madQBjEtgM="
   "theme/yeti.css" "sha256-8Fi/poAY9nuNFlq0IER8nJnvWJvgjdD+QppK23qtHwc="
   "theme/yeti.min.css" "sha256-I+gO/GVX+OHqJG9Kd6ij9GlwjyNkN9wYoRQ8oW0lcU0="
   "theme/yonce.css" "sha256-WVERKyxmm3pMWnKc69/HKaM9YCy59C0QocGgCxSE2T0="
   "theme/yonce.min.css" "sha256-HFM4Phvlk2psw39d10NKt29TK9Arcr5jPITp/QZs+iU="
   "theme/zenburn.css" "sha256-R3gLzG1CnUNDkq7Vtjwm7+1rzj003xFYVzcA7hieqy8="
   "theme/zenburn.min.css" "sha256-FvSQYX5Mr9r6S3pHJSJgiu1nE/ScW7a/XPOLtjxRF9k="}


(def cdn-base "https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.46.0/")
(defn url [asset] (str cdn-base asset-url))


(defn css
  ([asset]
   (let [url (url asset)
         sri (get raw-assets asset)]
     (if (and url sri)
       (css url sri)
       (fn [] (link :rel "stylesheet" :href url)))))
  ([url sri]
   (fn [] (link :rel "stylesheet" :href url :integrity sri :crossorigin "anonymous"))))


(defn js
  ([asset]
   (let [url (url asset)
         sri (get raw-assets asset)]
     (if (and url sri)
       (js url sri)
       (fn [] (script :src url)))))
  ([url sri]
   (fn [] (script :src url :integrity sri :crossorigin "anonymous"))))


(defn asset->dom-fn
  "For a given asset: build a fn that will create a DOM node to load that asset"
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
  (let [updated-asset-code (conj {:name next-asset} dom-fn)
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
