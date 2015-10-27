(ns gradual-untyped-import.timer-lang
  (:require [clojure.core.typed.lang :as lang]
            [clojure.java.io :as io]))

(defn timer [base-resource-path]
  (let [[file-url filename]
        (let [f (str base-resource-path ".clj")]
          (when-let [r (io/resource f)]
            [r f]))]
    (assert (and file-url filename) (str "Cannot find file " filename))
    (binding [*ns*   *ns*
              *file* filename]
      (with-open [rdr (io/reader file-url)]
        (let [pbr (java.io.PushbackReader. rdr)
              eof (Object.)]
          (loop []
            (let [form (read pbr false eof)]
              (when-not (identical? form eof)
                (time (eval form))
                (recur)))))))))

(alter-var-root #'lang/lang-dispatch
                assoc :timer #'timer)
