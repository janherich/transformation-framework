(ns transformationframework.mergers
  (:require [transformationframework.functions :as fc]))

(defn value
  "Just pass value through the function without any modification"
  {:added "TransformationFramework 1.0"}
  [x] x)

(defn subrecordlist
  "Higher order function which returns function for creating sub-record lists, takes keys to construct sub-record list from"
  {:added "TransformationFramework 1.0"}
  [x & more]
  (fn [record & records]
    (let [keylist (apply list x more) recordlist (apply list record records)]
      (map #(select-keys % keylist) recordlist))))

(defn create-record-key
  "Create key from record using key template map"
  {:added "TransformationFramework 1.0"}
  [record key-template]
  (into {} (for [[k v] key-template] [k (v (k record))])))

(defn divide-groups
  "Divide sequence of records into groups according to key template map"
  {:added "TransformationFramework 1.0"}
  [records-seq key-template]
  (loop [records records-seq merge-groups {}]
    (if records
      (let [current-record (first records) key (create-record-key current-record key-template)]
        (recur (next records) (update-in merge-groups [key] #(flatten (fc/safe-conj (list current-record) %)))))
      merge-groups)))

(defn merge-group
  "Merge group together into one record"
  {:added "TransformationFramework 1.0"}
  [key merge-group merge-template]
  (let [reference-record (first merge-group)]
    (merge key 
           (into {} (for [[k v] (fc/map-intersection merge-template reference-record)] [k (apply v (map k merge-group))]))
           (into {} (for [[k v] (fc/map-difference merge-template reference-record)] [k (apply v merge-group)])))))

(defn merge-records
  "Merge list of records according to key template and merge template maps"
  {:added "TransformationFramework 1.0"}
  [records key-template merge-template]
  (map #(merge-group (key %) (val %) merge-template) (divide-groups records key-template)))

