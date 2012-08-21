(ns Ciphr.core
	(:require 
		[Ciphr.schemas :as s]
		[clojure.string]))

(defn get-letter [schema letter]
	(get schema letter "?"))

(defn substitute [text schema]
	(clojure.string/join (map (partial get-letter schema) text)))

(defn count-letter [text letter]
	(count (filter #(= letter %1) text)))

(defn count-letters [text alphabet]
	(let [occurences (map (partial count-letter text) alphabet)]
		(into {} (zipmap alphabet occurences))))

(defn letters-sorted-by-count [text alphabet]
	(keys (sort-by val > (count-letters text alphabet))))

(defn hypothesis-schema [text alphabet sorted-alphabet]
	(s/add-non-letters (into {} (zipmap (letters-sorted-by-count text alphabet) sorted-alphabet))))

(defn solve [text alphabet sorted-alphabet]
	(let [schema (hypothesis-schema text alphabet sorted-alphabet)]
		(substitute text schema)))

(def help-text
"Please supply a valid argument: 
 -en(crypt) [string] [steps]
 -de(crypt) [string] [steps]
 -solve [string]
 -fen(crypt) [filename] [steps]
 -fsolve [filename]")

(defn -main [& args]
	(let [flag (first args)]
		(cond
			(= flag "-solve")
				(println (solve (clojure.string/lower-case (second args)) s/normal-alphabet s/sorted-alphabet))
			(= flag "-fsolve")
				(println (solve (clojure.string/lower-case (slurp (second args))) s/normal-alphabet s/sorted-alphabet))
			(= flag "-en") 
				(println (substitute (clojure.string/lower-case (second args)) (s/caesar-schema (Integer/parseInt (nth args 2)))))
			(= flag "-de")
				(println (substitute (clojure.string/lower-case (second args)) (s/caesar-schema (- (Integer/parseInt (nth args 2))))))
			(= flag "-fen")
				(spit (str "encoded-" (second args)) (substitute (clojure.string/lower-case (slurp (second args))) (s/caesar-schema (Integer/parseInt (nth args 2)))))
			:else (println help-text))))

