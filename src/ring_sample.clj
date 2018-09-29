(ns ring-sample
  (:require [ring.adapter.jetty :as jetty]))

(defn handler-greet [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello Alice"})

(def server
  (jetty/run-jetty handler-greet {:port 3000 :join? false}))
