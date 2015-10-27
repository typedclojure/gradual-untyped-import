(ns gradual-untyped-import.timer-eg
  {:lang :timer})

1

(count (take 1000000 (repeat 0)))

;; (require 'gradual-untyped-import.timer-eg)
;; "Elapsed time: 11.418212 msecs"
;; "Elapsed time: 0.106438 msecs"
;; "Elapsed time: 232.563357 msecs"
;  nil
