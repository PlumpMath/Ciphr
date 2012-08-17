(ns Ciphr.core
	(:require [Ciphr.basics]))

(defn -main [& args]
	(assert (not (nil? (first args))))
	(println (Ciphr.basics/substitution (first args))))