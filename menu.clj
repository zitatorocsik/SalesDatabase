(ns menu)
; this file simply prints the menu for the user
(defn printMenu []
  (print "\n*** Sales Menu ***\n")
  (print "------------------\n\n")
  (print "1. Display Customer Table\n")
  (print "2. Display Product Table\n")
  (print "3. Display Sales Table\n")
  (print "4. Total Sales for Customer\n")
  (print "5. Total Count for Product\n")
  (print "6. Exit\n\n")
  (print "Enter an option: ")
  (flush)
) 