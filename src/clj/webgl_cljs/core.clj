(ns webgl-cljs.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

;; defroutes macro defines a function that chains individual route
;; functions together. The request map is passed to each function in
;; turn, until a non-nil response is returned.
(defroutes app-routes
  ;; Serves the document root address
  (GET "/" [] "<p>Hello from compojure</p>")

  ;; Serves static pages from the resources/public directory
  (route/resources "/")

  ;; If no such resource exists
  (route/not-found "Page not found"))

;; The function handler/site creates a handler suitable for a standard
;; website, adding a bunch of standard ring middleware to app-route:
(def handler
  (handler/site app-routes))
