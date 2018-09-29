(ns integrant-sample
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

; [A] integrant のコンポーネント設定
(def config
  {:adapter/jetty {:port 3000, :handler (ig/ref :handler/greet)}
   :handler/greet {:name "Alice"}})

; [B] integrant の各コンポーネントの初期化処理
(defmethod ig/init-key :adapter/jetty [_ {:keys [handler] :as opts}]
  (jetty/run-jetty handler (-> opts (dissoc :handler) (assoc :join? false))))

(defmethod ig/init-key :handler/greet [_ {:keys [name]}]
  (fn [request]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body (str "Hello " name)}))

; [C] 初期化処理の実行
(defn go []
  (ig/init config))
