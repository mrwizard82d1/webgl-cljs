(ns webgl-cljs.simple
  (:require [dommy.core :as dommy]
            [cljsthree.renderers.webgl :as renderer]
            [cljsthree.scene.scene :as scene]
            [cljsthree.cameras.perspective :as camera]
            [cljsthree.extras.geometries.plane-geometry :as plane-geometry]
            [cljsthree.materials.meshbasic :as meshbasic]
            [cljsthree.objects.mesh :as mesh])
  (:use-macros [dommy.macros :only [sel1]]))

(defn render
  "Render the scene displayed on the page."
  []
  (let [container (sel1 :#container)
        width (.-offsetWidth container)
        height (.-offsetHeight container)
        renderer (renderer/webgl)
        scene (scene/scene)
        camera (camera/perspective 45       ; field of view
                                   (/ width height) ; screen aspect ratio
                                   1        ; near clipping plane
                                   4000)    ; far clippling plane
        geometry (plane-geometry/plane-geometry 1 1) ; rectagle with width height
        material (meshbasic/meshbasic {:color 0x00f0f0})
        mesh (mesh/mesh geometry material)]
    ;; Set up the canvas on the page
    (renderer/set-size renderer width height)
    (dommy/append! container (.-domElement renderer))
    
    ;; Set up the camera
    (camera/set-position camera [0 0 3.333])

    ;; add everything to the scene
    (scene/add scene camera mesh)

    ;; and render it.
    (renderer/render renderer scene camera)))

(set! (.-onload js/window) render)

