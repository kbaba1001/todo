(ns todo.boundary.articles
  (:require duct.database.sql
            [clojure.java.jdbc :as jdbc]))

(defprotocol Articles
  (index-articles [db])
  (create-article [db params]))

(extend-protocol Articles
  duct.database.sql.Boundary

  (index-articles [{:keys [spec]}]
    (jdbc/query spec ["SELECT * FROM articles"]))

  (create-article [{:keys [spec]} {:keys [content]}]
    (let [result (jdbc/insert! spec :articles {:content content})]
      (-> result first :id))))
