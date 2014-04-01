(ns webgl-cljs.real)

(defn on-load []
  (let [container (.getElementById js/document "container")
        width (.-offsetWidth container)
        height (.-offsetHeight container)
        renderer (THREE.WebGLRenderer. (clj->js {:antialias true}))]
    (.setSize renderer width height)
    (.appendChild container (.-domElement renderer))
    (let [scene (THREE.Scene.)
          camera (THREE.PerspectiveCamera. 45 (/ width height) 1 4000)]
      (.set (.-position camera) 0 0 3)
      (let [light (THREE.DirectionalLight. 0xffffff 1.5)]
        (.set (.-position light) 0 0 1)
        (.add scene light)
        (let [map-url "images/mr_wizard.002.jpg"
              texture-map (. THREE.ImageUtils loadTexture map-url)
              material (THREE.MeshPhongMaterial. (clj->js {:map texture-map}))
              geometry (THREE.CubeGeometry. 1 1 1)
              cube (THREE.Mesh. geometry material)]
          (set! (.-x (.-rotation cube)) (/ Math/PI 5))
          (set! (.-y (.-rotation cube)) (/ Math/PI 5))
          (.add scene cube)
          (.render renderer scene camera))))))
  
(set! (.-onload js/window) on-load)
