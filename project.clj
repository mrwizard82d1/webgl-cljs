(defproject webgl-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2197"]
                 [compojure "1.1.6"]
                 [ring "1.2.2"]
                 [prismatic/dommy "0.1.2"]
                 [cljsthree "0.1.2-SNAPSHOT"]]
  :repositories [["cljsthree-local"
                  "file:///D:/cygwin/home/l.jones/professional/projects/cljsthree/target/cljsthree-0.1.2-SNAPSHOT.jar"]]
  :source-paths ["src/clj"]             ; Compiling Clojure files
  :plugins [[lein-cljsbuild "1.0.2"]
            [lein-ring "0.8.8"]]
  :ring {:handler webgl-cljs.core/handler}
  :cljsbuild
  {:builds
   {:simple-debug
    {:source-paths ["src/cljs/simple" "src/cljs/connect"]
     :compiler
     {:output-to "resources/public/simple.js"
      :optimizations :whitespace
      :pretty-print true}}}})
