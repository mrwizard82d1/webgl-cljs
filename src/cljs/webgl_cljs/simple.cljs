(ns webgl-cljs.simple
  (:require [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [sel1]]))

(defn add-to-scene
  "Add a number of objects to the scene."
  [scene & objectss]
  (doseq [object objectss]
    (.add scene object)))

(defn render
  "Render the scene displayed on the page."
  []
  (let [container (sel1 :#container)
        width (.-offsetWidth container)
        height (.-offsetHeight container)
        renderer (THREE.WebGLRenderer.)
        scene (THREE.Scene.)
        camera
        (THREE.PerspectiveCamera. 45    ; field of view
                                  (/ width height) ; screen aspect ratio
                                  1     ; near clipping plane
                                  4000) ; far clippling plane
        geometry (THREE.PlaneGeometry. 1 1) ; rectagle with width height
        mesh (THREE.Mesh. geometry (THREE.MeshBasicMaterial.))]
    ;; Set up the canvas on the page
    (.setSize renderer width height)
    (dommy/append! container (.-domElement renderer))
    
    ;; Set up the camera
    (-> camera .-position (.set 0 0 3.333))

    ;; add everything to the scene
    (add-to-scene scene camera mesh)

    ;; and render it.
    (.render renderer scene camera)))

(set! (.-onload js/window) render)

