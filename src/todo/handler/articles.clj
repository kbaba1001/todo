(ns todo.handler.articles
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]))

; [clojure.java.jdbc :as jdbc]
; TODO ["SELECT * FROM articles"] のクエリの結果を response/ok で返す
(defmethod ig/init-key ::index [_ {:keys [db]}]
  (fn [request]
    [::response/ok "OK!!"]))

; [{"id":1,"content":"hello"}]
