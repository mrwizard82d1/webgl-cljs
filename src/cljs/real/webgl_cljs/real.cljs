(ns webgl-cljs.real
  (:require [dommy.core :as dommy]
            [cljsthree.renderers.webgl :as c3js.renderers]
            [cljsthree.scene.scene :as c3js.scenes]
            [cljsthree.cameras.perspective :as c3sj.cameras]
            [cljsthree.extras.geometries.plane-geometry :as c3js.geometries]
            [cljsthree.materials.meshbasic :as c3js.materials]
            [cljsthree.objects.mesh :as c3js.meshes])
  (:use-macros [dommy.macros :only [sel1]]))

(defn render
  "Render the scene displayed on the page."
  []
  (let [container (sel1 :#container)
        width (.-offsetWidth container)
        height (.-offsetHeight container)
        renderer (c3js.renderers/webgl)
        scene (c3js.scenes/scene)
        camera (c3sj.cameras/perspective 45
                                         (/ width height)
                                         1
                                         4000)
        geometry (THREE.CubeGeometry. 1 1 1)
        material (c3js.materials/meshbasic {:color 0x00f00f})
        light (THREE.DirectionalLight. 0xffffff 1.5)
        mesh (c3js.meshes/mesh geometry material)]
    ;; Set up the canvas on the page
    (c3js.renderers/set-size renderer width height)
    (dommy/append! container (.-domElement renderer))
    
    ;; Set up the camera
    (c3sj.cameras/set-position camera [0 0 3.333])

    ;; Turn the geometry toward the camera
    ;; (set! (.-x (.-rotation mesh)) (/ Math/Pi 5))
    ;; (set! (.-y (.-rotation mesh)) (/ Math/Pi 5))

    ;; add everything to the scene
    (c3js.scenes/add scene camera mesh light)

    ;; and render it.
    (c3js.renderers/render renderer scene camera)))

(set! (.-onload js/window) render)

