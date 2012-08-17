(ns Ciphr.schemas)

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn add-non-letters [schema]
	(into {\space \space} schema))

(defn skew [text steps]
	(subs text steps))

(defn caesar-schema [steps]
	(add-non-letters (into {} (map (fn [a b] [a b]) alphabet (skew alphabet steps)))))

(def schema-1 {
	\a \b
	\b \c
	\c \d
	\d \e  
	\e \f 
	\f \g
	\g \h
	\h \i
	\i \j
	\j \k
	\k \l
	\l \m
	\m \n
	\n \o
	\o \p
	\p \q
	\q \r
	\r \s
	\s \t
	\t \u
	\u \v
	\v \w
	\w \x
	\x \y
	\y \z
	\z \a
	\space \space
	})