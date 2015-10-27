(ns gradual-untyped-import.timer-lang
  "Defines the :timer language"
  (:require [clojure.core.typed.lang :as lang]
            [clojure.java.io :as io]))

(defn timer-load
  "Loads a file under the :timer lang.
  
  Same as clojure.core/load, but prints out timing information
  for each `eval`."
  [base-resource-path]
  (let [filename (str base-resource-path ".clj")
        file-url (io/resource filename)]
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

;; set up the :timer language.
(alter-var-root #'lang/lang-dispatch
                assoc :timer #'timer-load)
