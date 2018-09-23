(ns todo.handler.articles
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            [clojure.java.jdbc :as jdbc]))

(defmethod ig/init-key ::index [_ {:keys [db]}]
  (fn [request]
    (let [articles (jdbc/query (:spec db) ["SELECT * FROM articles"])]
      [::response/ok articles])))
