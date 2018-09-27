(ns todo.example-test
  (:require [clojure.test :as t]))

(t/deftest addition
  (t/testing "Arithmetic"
    (t/testing "with positive integers"
      (t/is (= 4 (+ 2 2)))
      (t/is (= 7 (+ 3 4))))
    (t/testing "with negative integers"
      (t/is (= -4 (+ -2 -2)))
      (t/is (= -1 (+ 3 -4))))))
