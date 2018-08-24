# webfusion - The next step after Atom

WebFusion Text is the intersection of a number of good ideas I've encountered over the years
that happens to implement an awesome programmer's editor and programming environment:

* Start with a small kernel that does nothing but bootstrap itself
* Modular, plugin-based design
* The program is written in (and using) itself
* Coding and program execution happen at the same time
* Use Lisp, because Lisp is the first and best language that can be evolved in itself

## TODO

* Finish integrating replumb / implementing the main loader in repl.cljs

* Job API
  * Job name
  * Job description
  * Step number / of? Or vector of step functions where each function accepts a continuation?
  * Root cells: Current step name/description; current step number; total steps in current job; jobs remaining

* Mechanism to push jobs from back-end to front-end (as noted under Refactoring below)

* Edit itself using CodeMirror
  * Select a file
  * auto-repl displays result in gray beside line; hover shows more; guard against infinite loops

* UI ideas
  * Splitters.  Ctrl-1, Ctrl-2, Ctrl-3, Ctrl-0 to add and remove.
  * Quick sidebar for things like quick outline, open-file tree, git status, etc.
  * File tree - type to filter
  * Need a way to compress the number of tabs to make manageable?
  * Synchronized editors of same file.  Across multiple tabs/connections.  Displaying remote highlight.

* Refactoring
  * Move everything but bootstrap logic to separate repos as spiked in api.clj
  * Config.edn in each plugin repo's root naming the plugin, its activator, its dependencies
    * Dependencies allow us to compute load order
  * Link via the config mechanism
  * Write default config file if one doesn't already exist
  * Download webfusion-text via git during bootstrap
  * Get the UI up, then download and activate plugins in the background
    * Push activation jobs from the back-end to the UI via the refresh-state mechanism or similar
    * `git pull` already-downloaded plugins before activating
    * Load everything we can, then display what we couldn't load (and dependencies) if needed
      * Just create an editor buffer in Markdown with the report?
    * Support Javascript-based plugins?


## License

Eclipse Public License, Version 2.0

Copyright © 2017, David Orme
