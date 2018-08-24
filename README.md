# webfusion text - The next step after Atom

WebFusion Text is the intersection of a number of good ideas I've encountered over the years
that happens to implement an awesome programmer's editor and programming environment:

* Start with a small kernel that does nothing but bootstrap itself
* Modular, plugin-based design
* The program is written in (and using) itself
* Coding and program execution happen at the same time
* Use Lisp, because Lisp is the first and best language that can be evolved in itself

## TODO

* Finish integrating replumb / implementing the main loader

* UI ideas
  * Quick sidebar for things like quick outline, open-file tree, git status, etc.
  * File tree - type to filter
  * Need a way to compress the number of tabs to make manageable?

* Refactoring
  * Move everything but bootstrap logic to separate repos as spiked in api.clj
  * Config.edn in each plugin repo's root naming the plugin and its activator
  * Link via the config mechanism
  * Write default config file if one doesn't already exist
  * Download webfusion-text via git during bootstrap

* Job API
  * Job name
  * Job description
  * Step number / of? Or vector of step functions where each function accepts a continuation?
  * Root cells: Current step name/description; current step number; total steps in current job; jobs remaining


## License

Eclipse Public License, Version 2.0

Copyright © 2017, David Orme
