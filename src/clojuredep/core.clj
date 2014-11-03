(ns clojuredep.core
  (:use compojure.core)
  (:require
   [ring.adapter.jetty :as jetty]
   [clostache.parser :as clostache]
   [compojure.route :as route]))

(defroutes main-routes
  (GET "/" [] "Welcome world")
  (GET "/api" [] "{\"objects\": [{\"a\":1, \"b\":2}, {\"a\":4,\"b\":9}]}"))

(defn -main []
  (let [port
        (Integer/parseInt
         (get (System/getenv) "PORT" "80"))]
    (jetty/run-jetty main-routes {:port port})))
