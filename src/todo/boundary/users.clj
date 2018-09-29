(ns todo.boundary.users
  (:require duct.database.sql
            [clojure.java.jdbc :as jdbc]
            [buddy.hashers :as hashers]))

(defprotocol Users
  (create-user [db params])
  (find-user-by-email [db email])
  (authenticate-user [db params]))

(extend-protocol Users
  duct.database.sql.Boundary

  (create-user [{:keys [spec]} {:keys [email password]}]
    (let [password-digest (hashers/derive password)
          result (jdbc/insert! spec :users {:email email :password_digest password-digest})]
      (-> result first :id)))

  (find-user-by-email [{:keys [spec]} email]
    (first (jdbc/query spec ["SELECT * FROM users WHERE email=?" email])))

  (authenticate-user [db {:keys [email password]}]
    (if-let [user (find-user-by-email db email)]
      (if (hashers/check password (:password_digest user))
        (dissoc user :password_digest)))))
