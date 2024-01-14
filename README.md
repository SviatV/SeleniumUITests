This guide provides instructions on how to set up and run the "HomeWorkOne" project using the provided pom.xml file.

IMPORTANT:
-
The Opera driver no longer works with the latest functionality of Selenium and is currently officially unsupported.

PREREQUISITES: 
-
 - Java JDK 19 or higher
 - Maven

PROJECT PROPERTIES:
-
- base.url: The base URL for the application. Default is set to https://otus.ru.
- browser: The web browser to be used for testing. Default is set to chrome.
- browser.version: The version of the selected web browser.

RUN TESTS:
-
- To run the tests, use the following Maven command: mvn clean test
- If you want to run tests with a specific profile, use the following command: mvn clean test -Pprod

CODE ANALYSIS:
-
- SportBugs:  mvn spotbugs:check
- Checkstyle: mvn checkstyle:check
