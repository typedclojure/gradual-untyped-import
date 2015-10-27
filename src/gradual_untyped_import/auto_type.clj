;Demonstrates implicit static typing
(ns gradual-untyped-import.auto-type
  {:lang :core.typed})

(defn terror []
  (inc 'a)) ; a type error



(comment
  (require 'gradual-untyped-import.auto-type :reload)
  ;; Type Error (gradual_untyped_import/auto_type.clj:6:3) Static method clojure.lang.Numbers/inc could not be applied to arguments:
  ;; 
  ;; 
  ;; Domains:
  ;; 	Number
  ;; 
  ;; Arguments:
  ;; 	(clojure.core.typed/Val a)
  ;; 
  ;; Ranges:
  ;; 	Number
  ;; 
  ;; with expected type:
  ;; 	Error
  ;; 
  ;; 
  ;; in: (clojure.lang.Numbers/inc (quote a))
)
