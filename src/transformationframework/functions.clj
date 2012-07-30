(ns transformationframework.functions
  (:use [clojure.set]))

(defn sort-by-list 
  "Sort collection (2nd argument) in order provided by first argument"
  {:added "TransformationFramework 1.0"}
  [list seq]
  (sort-by (zipmap list (iterate inc 1)) seq))

(defn safe-conj
  "Standard conj function enhanced to work also with one argument"
  {:added "TransfromationFramework 1.0"}
  ([coll] coll)
  ([coll x] (if x (conj coll x) coll)))

(defn map-intersection
  "Intersections of two maps return map which contains only keys from both maps and values from first map"
  {:added "TransformationFramework 1.0"}
  ([map1 map2]
    (select-keys map1 (intersection (set (keys map1)) (set (keys map2))))))

(defn map-difference
  "Difference of two maps return map which contains only keys and values from first map minus keys from second map"
  {:added "TransformationFramework 1.0"}
  ([map1 map2]
    (select-keys map1 (difference (set (keys map1)) (set (keys map2))))))