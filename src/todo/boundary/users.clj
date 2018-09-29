(ns todo.boundary.users
  (:require duct.database.sql
            [clojure.java.jdbc :as jdbc]
            [buddy.hashers :as hashers]))

(defprotocol Users
  (create-user [db params]))

(extend-protocol Users
  duct.database.sql.Boundary

  (create-user [{:keys [spec]} {:keys [email password]}]
    (let [password-digest (hashers/derive password)
          result (jdbc/insert! spec :users {:email email :password_digest password-digest})]
      (-> result first :id))))
