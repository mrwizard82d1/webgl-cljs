(ns webgl-cljs.simple)

(defn render
  "Renders a 3D scene."
  []
  (let [container (.getElementById js/document "container")
        width (.-offsetWidth container)
        height (.-offsetHeight container)
        renderer (THREE.WebGLRenderer.)
        scene (THREE.Scene.)
        camera (THREE.PerspectiveCamera. 45
                                         (/ width height)
                                         1 4000)
        geometry (THREE.PlaneGeometry. 1 1)
        mesh (THREE.Mesh. geometry (THREE.MeshBasicMaterial.))]
    (.setSize renderer width height)
    (.appendChild container (.-domElement renderer))
    
    (.set (.-position camera) 0 0 3.3333)
    (.add scene camera)
    (.add scene mesh)
    (.render renderer scene camera)))

(set! (.-onload js/window) render)

