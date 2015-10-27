(defproject gradual-untyped-import "0.1.0-SNAPSHOT"
  :description "Example untyped import in core.typed"
  :url "http://typedclojure.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  :injections [(require 'clojure.core.typed.load)
               (require 'clojure.core.typed.lang)
               (clojure.core.typed.load/install-typed-load)
               (clojure.core.typed.lang/monkey-patch-extensible-load)

               ;; load example :lang
               (require 'gradual-untyped-import.timer-lang)
               ]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/core.typed "0.3.14"]])
