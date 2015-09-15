# Conway Card Sort
### About
This program is a solution to John H. Conway's 3 stack card sorting problem.
The algorithm used to solve this problem is preformed in three basic steps:

1. Distribute the elements from the left stack amongst the middle and right stacks
until the left stack is empty.
2. Compare the top cards of the middle and right stacks and add them to the left stack.
At the end of this process we will have a single stack (on the left position) that contains several sorted substacks.
3. Pop off the first sorted substack onto the middle stack, then the second sorted substack onto the right stack.
Merge the two substacks by adding the larger of the top two cards to the left stack. Repeat this process until
you have a single sorted stack.

### Instructions
This program uses the [Gradle](http://gradle.org) build tool.
the following are instructions on how to run the tests written for the program using gradle in the command line. 
* run `gradle test` to run the test cases in CardSortTest.java
* navigate to `build/reports/tests/` and open `index.html` in your browser
  * this will show information about the test results: test result, time to execute, output statements, etc.
* run `gradle clean` if you wish to run the tests again 
