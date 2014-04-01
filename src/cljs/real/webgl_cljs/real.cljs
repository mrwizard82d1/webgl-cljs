(ns webgl-cljs.real
  (:require [dommy.core :as dc]
            [cljsthree.renderers.webgl :as c3js.renderers]
            [cljsthree.scene.scene :as c3js.scenes]
            [cljsthree.cameras.perspective :as c3js.cameras]
            [cljsthree.objects.mesh :as c3js.meshes]
            )
  (:require-macros [dommy.macros :as dm]))

(defn on-mouse-up [animating event]
  (swap! animating not))

(defn add-mouse-handler [renderer animating]
  (let [dom (.-domElement renderer)]
    (dc/listen! dom :click (partial on-mouse-up animating))))

(defn run [render-scene cube animating]
  (render-scene)
  
  (if @animating
    (set! (.-y (.-rotation cube)) (- (.-y (.-rotation cube)) 0.01)))

  (.requestAnimationFrame js/window (partial run render-scene cube animating)))

(defn on-load []
  (let [container (dm/sel1 :#container)
        width (.-offsetWidth container)
        height (.-offsetHeight container)
        renderer (c3js.renderers/webgl {:antialias true})]
    (c3js.renderers/set-size renderer width height)
    (dc/append! container (.-domElement renderer))
    (let [scene (c3js.scenes/scene)
          camera (c3js.cameras/perspective 45 (/ width height) 1 4000)]
      (c3js.cameras/set-position camera [0 0 3])
      (let [light (THREE.DirectionalLight. 0xffffff 1.5)]
        (.set (.-position light) 0 0 1)
        (let [map-url "images/mr_wizard.002.jpg"
              texture-map (. THREE.ImageUtils loadTexture map-url)
              material (THREE.MeshPhongMaterial. (clj->js {:map texture-map}))
              geometry (THREE.CubeGeometry. 1 1 1)
              cube (c3js.meshes/mesh geometry material)
              animating (atom false)]
          (set! (.-x (.-rotation cube)) (/ Math/PI 5))
          (set! (.-y (.-rotation cube)) (/ Math/PI 5))
          (c3js.scenes/add scene light cube)
          (let [render-scene (fn [] (c3js.renderers/render renderer scene camera))]
            (render-scene)
            (add-mouse-handler renderer animating)
            (run render-scene cube animating)))))))
  
(set! (.-onload js/window) on-load)
