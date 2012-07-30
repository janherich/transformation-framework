(ns transformationframework.test.functions
  (:use [transformationframework.functions])
  (:use [clojure.test]))

(deftest test-sort-by-list
  (is (= '("xs" "m" "xl") (sort-by-list '("xs" "s" "m" "l" "xl") '("m" "xs" "xl")))))

(deftest test-map-intersection
  (is (= {:a 1 :b 2} (map-intersection {:a 1 :b 2 :c 2} {:a 1 :b 2 :d 4}))))

(deftest test-map-difference
  (is (= {:a 1 :b 2} (map-difference {:a 1 :b 2 :c 3 :d 4} {:c 3 :d 4}))))