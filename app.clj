(ns app
  (:require [db])
  (:require [menu]))

;this function will simply check the users input and call the correct function to be performed in db.clj
(defn process [option]
  (if (= "1" option)
    (do
      (db/printCustomerTable)
      (flush))
    (do
      (when (= "2" option)
        (db/printProductTable))
      (when (= "3" option)
        (db/printSalesTable))
      (when (= "4" option)
        (print "\nEnter a customer name: ")
        (flush)
        (db/totalSaleForCustomer (read-line)))
      (when (= "5" option)
        (print)
        (print "\nEnter a product: ")
        (flush)
        (db/totalCountProduct (read-line)))
      (when (= "6" option)
        (println "")
        (print "Good-bye!\n")
        (println "")
        (flush)
        (System/exit 0)))))

; this is how the program continues running. At each loop iteration, the menu is printed, the option is processed, then we start again
(loop []
  (menu/printMenu)
  (process (read-line))
  (recur))