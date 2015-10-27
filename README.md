# gradual-untyped-import

This project shows three new core.typed features:
- automatic type-checking on `load`/`require`
- the first gradual typing feature: contract generation for untyped imports
- how to define new `:lang`s, a la Racket's #lang

## Implicit type checking

Adding these lines to our `project.clj` monkey-patches core.typed's
typed loading over `clojure.core/load`.

```clojure
  :injections [(require 'clojure.core.typed.load)
               (require 'clojure.core.typed.lang)
               (clojure.core.typed.load/install-typed-load)
               (clojure.core.typed.lang/monkey-patch-extensible-load)
```

The namespace `gradual-untyped-import.auto-type` shows this in action.

Normal calls to `require` are automatically type checked when both `load` is
called and the `ns` form contains `{:lang :core.typed}` metadata.

## Generating contracts

The first "gradual typing" feature to appear in core.typed is automatic contract
generation for untyped imports.

An example is shown in `gradual-untyped-import.import-untyped`.

Types for untyped code are declared with `untyped-var`.

```
(t/untyped-var u/is-actually-int t/Int)
```

Now occurrences of `u/is-actually-int` in the current namespace are compiled
to

```
(let [v u/is-actually-int]
  (assert ((pred t/Int) v))
  v)
```

## Defining new languages

We can hook into the same extensible `load` mechanism as core.typed
by interacting with `clojure.core.typed.lang`.

The `gradual-untyped-import.timer-lang` namespace defines the `:timer`
language, that prints timing information for each evaluation in a file.

The `gradual-untyped-import.timer-eg` namespace has `{:lang :timer}`
namespace metadata, so calls to `load` have the timing information.

## License

Copyright © 2015 Ambrose Bonnaire-Sergeant

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
