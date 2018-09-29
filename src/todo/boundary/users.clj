(ns todo.boundary.users
  (:require duct.database.sql
            [clojure.java.jdbc :as jdbc]))

(defprotocol Users)

(extend-protocol Users
  duct.database.sql.Boundary)
