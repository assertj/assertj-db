Contributing
============

Thank you for your interest in contributing to AssertJ assertions !  
We appreciate your effort and to make sure that your pull request is easy to review, we ask you to make note of the following guidelines :

* Use **[AssertJ code Eclipse formatting preferences](https://github.com/joel-costigliola/assertj-core/blob/main/src/ide-support/assertj-eclipse-formatter.xml)** (for Idea users, it is possible to import it)
* Write complete Javadocs on each assertion methods including a code example.
* Write one JUnit test class for each assertion method with the following naming convention : `<AssertClass>_<assertion>_Test`. 
* Unit tests method naming convention is underscore based (like python) and not camel case, we find it is much readable for long test names !
* Successful assertion unit test method name must start with : `should_pass_...`.
* Failing assertion unit test method name must start with : `should_fail_...`.
* If possible, add a (fun) code example in [assertj-examples](https://github.com/joel-costigliola/assertj-examples) and use it in the javadoc. 


Documentation
-------------

The user guide is written in AsciiDoc and built with [Antora](https://antora.org).
Its pages are in `src/docs/antora/modules/ROOT/pages`, and the code examples they include are in `src/test/java/example`.

The code examples are compiled with the test sources, so an API change that breaks them fails the build.
They are not executed as tests, because they need a database that does not exist.
Pages include them by tag, e.g. `include::example$java/example/db/TableAssertionExamples.java[tags=user_guide]`.

To preview your changes, generate the site:

```shell
./mvnw antora
```

Then open `target/site/index.html` in a browser (the build prints the full `file://` URL at the end).
The preview only contains the AssertJ DB guide; the complete site is assembled from all AssertJ repositories by [docs.assertj.dev](https://github.com/assertj/docs.assertj.dev).
No Node.js installation is required: the first run downloads Node.js into `target/node` and the Antora packages into `node_modules`.
To use a Node.js installation from your `PATH` instead, add `-Dnode.executable=node`.
