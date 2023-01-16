(ns db
  (:require [clojure.string :as str]))

;function to load file
(defn loadData [fileName]
  (slurp fileName))

;load each file into program
(def custDB (loadData "cust.txt"))
(def prodDB (loadData "prod.txt"))
(def salesDB (loadData "sales.txt"))

;make each file into a vector of vectors
(def custDict
  (mapv #(str/split % #"\|") (str/split-lines custDB)))
(def prodDict
  (mapv #(str/split % #"\|") (str/split-lines prodDB)))
(def salesDict
  (mapv #(str/split % #"\|") (str/split-lines salesDB)))

;calculate the sum of a product 
(defn calculateSum [prodId amount]
  (loop [n 0]
    (if (< n (count prodDict))
      (if (= prodId (nth (nth prodDict n) 0))
        (* amount (read-string (nth (nth prodDict n) 2)))
        (recur (inc n)))
      (str "product not found"))))

;get a customers id from their name
(defn getCustomerIDfromName [name]
  (loop [n 0]
    (if (< n (count custDict))
      (if (= name (nth (nth custDict n) 1))
        (nth (nth custDict n) 0)
        (recur (inc n)))
      (str "Customer not in Database"))))

;customer name from their id
(defn getCustomerNameFromID [id]
  (loop [n 0]
    (if (< n (count custDict))
      (if (= id (nth (nth custDict n) 0))
        (nth (nth custDict n) 1)
        (recur (inc n)))
      ())))

;product from its id
(defn getProductFromID [prodID]
  (loop [n 0]
    (if (< n (count prodDict))
      (if (= prodID (nth (nth prodDict n) 0))
        (nth (nth prodDict n) 1)
        (recur (inc n)))
      ())))

(defn getIDFromProduct [product]
  (loop [n 0]
    (if (< n (count prodDict))
      (if (= product (nth (nth prodDict n) 1))
        (nth (nth prodDict n) 0)
        (recur (inc n)))
      ())))

;option 1: printing customer table
(defn printCustomerTable []
  (println "")
  (dotimes [n (count custDict)]
    (println (str (nth (nth custDict n) 0) ":[\"" (nth (nth custDict n) 1) "\" \"" (nth (nth custDict n) 2) "\" " "\"" (nth (nth custDict n) 3) "\"]"))))

;option 2: printing product table
(defn printProductTable []
  (println "")
  (dotimes [n (count prodDict)]
    (println (str (nth (nth prodDict n) 0) ":[\"" (nth (nth prodDict n) 1) "\" \"" (nth (nth prodDict n) 2) "\"]"))))

;option 3: printing sales table
(defn printSalesTable []
  (println "")
  (dotimes [n (count salesDict)]
    (println (str (nth (nth salesDict n) 0) ":[\"" (getCustomerNameFromID (nth (nth salesDict n) 1)) "\" \"" (getProductFromID (nth (nth salesDict n) 2)) "\" " "\"" (nth (nth salesDict n) 3) "\"]"))))

;option 4: getting total sales for a customer
(defn totalSaleForCustomer [name]
  (println "")
  (loop [n 0 sum 0]
    (if (< n (count salesDict))
      (if (= (getCustomerIDfromName name) (nth (nth salesDict n) 1))
        (recur (inc n) (+ sum (calculateSum (nth (nth salesDict n) 2) (read-string (nth (nth salesDict n) 3)))))
        (recur (inc n) sum))
      (println (str "The total for customer " name " is: $" (format "%.2f" (float sum)))))))

;option 5: total count for a product 
(defn totalCountProduct [product]
  (println "")
  (loop [n 0 sum 0]
    (if (< n (count salesDict))
      (if (= (getIDFromProduct product) (nth (nth salesDict n) 2))
        (recur (inc n) (+ sum (read-string (nth (nth salesDict n) 3))))
        (recur (inc n) sum))
      (println (str "The total count for product " product " is: " sum)))))