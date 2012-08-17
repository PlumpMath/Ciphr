(ns Ciphr.core
	(:require 
		[Ciphr.basics]
		[Ciphr.schemas]))

(defn -main [& args]
	(assert (not (nil? (first args))) "Must supply a command line argument")
	(println (Ciphr.basics/substitute (first args) Ciphr.schemas/schema-1)))

