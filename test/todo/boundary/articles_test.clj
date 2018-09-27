(ns todo.boundary.articles-test
  (:require [clojure.test :as t]
            [clojure.java.jdbc :as jdbc]
            [todo.test-utils :as u]
            [todo.boundary.articles :as articles]))

(t/use-fixtures :each u/db-cleanup)

(t/deftest boundary-artilces-test
  (t/testing "create article"
    (let [article-id (articles/create-article u/db {:content "foo"})]
      (t/is (int? article-id))))

  (t/testing "index artilces"
    (let [results (articles/index-articles u/db)]
      (t/is (= 1 (count results)))
      (t/is (= "foo" (:content (first results)))))))
