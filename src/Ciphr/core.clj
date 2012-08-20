(ns Ciphr.core
	(:require 
		[Ciphr.basics]
		[Ciphr.schemas]
		[clojure.string]))

(defn get-char [schema c]
	(let [item (find schema c)]
		(if (nil? item)
			"?"
			(val item))))

(defn substitute [text schema]
	(clojure.string/join (map (partial get-char schema) text)))

(defn count-letter [text letter]
	(count (filter #(= letter %1) text)))

(defn count-letters [text alphabet]
	(let [occurences (map (partial count-letter text) alphabet)]
		(into {} (zipmap alphabet occurences))))

(defn letters-sorted-by-count [text alphabet]
	(keys (sort-by val > (count-letters text alphabet))))

(defn hypothesis-schema [text alphabet sorted-alphabet]
	(Ciphr.schemas/add-non-letters (into {} (zipmap (letters-sorted-by-count text alphabet) sorted-alphabet))))

(defn solve [text alphabet sorted-alphabet]
	(let [schema (hypothesis-schema text alphabet sorted-alphabet)]
		(substitute text schema)))

(def help-text
"Please supply a valid argument: 
 -e (caesar encrypt) [string] [steps]
 -d (caesar decrypt) [string] [steps]
 -solve [encrypted string]")

(defn -main [& args]
	(let [flag (first args)]
		(cond
			; (= flag "-count")
			; 	(println (count-letters (second args) Ciphr.schemas/normal-alphabet))
			; (= flag "-sorted")
			; 	(println (letters-sorted-by-count (second args) Ciphr.schemas/normal-alphabet))
			; (= flag "-hypothesis")
			; 	(println (hypothesis-schema (second args) Ciphr.schemas/normal-alphabet Ciphr.schemas/sorted-alphabet))
			(= flag "-solve")
				(println (solve (clojure.string/lower-case (second args)) Ciphr.schemas/normal-alphabet Ciphr.schemas/sorted-alphabet))
			(= flag "-fsolve")
				(println (solve (clojure.string/lower-case (slurp (second args))) Ciphr.schemas/normal-alphabet Ciphr.schemas/sorted-alphabet))
			(= flag "-e") 
				(println (substitute (clojure.string/lower-case (second args)) (Ciphr.schemas/caesar-schema (Integer/parseInt (nth args 2)))))
			(= flag "-d")
				(println (substitute (clojure.string/lower-case (second args)) (Ciphr.schemas/caesar-schema (- (Integer/parseInt (nth args 2))))))
			(= flag "-fe")
				(spit (str "encoded-" (second args)) (substitute (clojure.string/lower-case (slurp (second args))) (Ciphr.schemas/caesar-schema (Integer/parseInt (nth args 2)))))
			:else (println help-text))))

