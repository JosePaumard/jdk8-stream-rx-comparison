This project contains de source code of the bench I have presented at JavaOne 2015 and Devoxx 2015 (minus the GC Collections part). It compares the performances of the implementations of the "Shakespeare plays Scrabble" kata using the GC Collections, RxJava and Java 8 Stream API frameworks.

This bench has no other purpose than to show different performances on the chosen problem, please, do not take it as a general bench, and do not draw conclusions that could be wrong on other use cases.

It is built on JMH, the standard tool used to measure performances in Java.

You need Maven to build and run this bench.

    $ mvn clean install

This creates a `benchmark.jar` file in your `target` directory. This jar contains what needs to be run to run the benchmark.

    $ java -jar target/benchmark.jar

This runs the benchmark and prints out the result on the console. Of course, the execution of this benchmark has very little chance to produce the same result as the one I showed in my talks.

This bench uses two data files: `ospd.txt` and `words.shakespeare.txt`. They can be freely downloaded from Robert Sedgwick page here: http://introcs.cs.princeton.edu/java/data/. By the way, there are other very interesting data sets on this page.

Those two data sets files are under the copyright of their authors, and provided for convenience only.