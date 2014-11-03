(ns clojuredep.core
  (:use compojure.core)
  (:require
   [ring.adapter.jetty :as jetty]
   [clostache.parser :as clostache]
   [compojure.route :as route]))

(defn read-template [name]
  (slurp
   (clojure.java.io/resource
    (str "templates/" name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render
   (read-template template-file) params))

(defn index []
  (render-template "index" {:name "Oracle"}))


(defn -main []
  (let [port
        (Integer/parseInt
         (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty main-routes {:port port})))

(defroutes main-routes
  (GET "/api" [] "{\"objects\": [{\"a\":1, \"b\":2}, {\"a\":4,\"b\":9}]}")
  (GET "/home" [] (index)))
