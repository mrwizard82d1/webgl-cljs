(ns webgl-cljs.core
  (:require [cljsthree.renderers.webgl :as renderer]
            [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [sel1]]))

(defn render
  "Render the scene displayed on the page."
  []
  (let [container (sel1 :#container)
        renderer (renderer/webgl)]
    (renderer/set-size renderer
                       (.-offsetWidth container)
                       (.-offsetWidth container))
    (dommy/append! container (.domElement renderer))
    ))

(set! (.-onload js/window) render)

