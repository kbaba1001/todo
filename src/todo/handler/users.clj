(ns todo.handler.users
  (:require [ataraxy.response :as response]
            [integrant.core :as ig]
            [todo.boundary.users :as users]))

(defmethod ig/init-key ::create [_ {:keys [db]}]
  (fn [request]
    [::response/ok "OK!!"]))

(defmethod ig/init-key ::signin [_ {:keys [db]}]
  (fn [request]
    [::response/ok "OK!!"]))
