#Chat-tastic

Example code to support the blog at http://www.casualmiracles.com/blog/....

sbt is bundled with the project so just run ./sbt on the command line at the root of the project.

To run with an embedded jetty, run:

> ./sbt

> container:start

Then point your browser at http://localhost:8080

For Eclipse:
	The Eclipse plugin is present: https://github.com/typesafehub/sbteclipse
	
For IntelliJ:
	The Intellij plugin is present: https://github.com/mpeltonen/sbt-idea
	Until a version for 0.11.2 is available use 'gen-idea no-sbt-classifiers'
