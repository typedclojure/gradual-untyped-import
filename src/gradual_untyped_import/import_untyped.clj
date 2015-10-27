(ns gradual-untyped-import.import-untyped
  {:lang :core.typed}  ; comment to disable type checking
  (:require [clojure.core.typed :as t]
            [gradual-untyped-import.untyped :as u]))

(t/untyped-var u/is-actually-int t/Int)
(t/untyped-var u/should-be-int   t/Int) ; is actually nil

(defn good []
  u/is-actually-int)

(defn bad []
  u/should-be-int) ; cast is inserted to Int, but actually is nil ...

(comment
  (good)
  ;=> 1
  (bad) ; better error message NYI, but u/should-be-int is nil
        ; and should actually be an Int, so a runtime error is
        ; thrown
  ; Failure
  )
