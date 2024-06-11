# Test API Automation Project

This is the test automation project which supports:
* REST Assured

## Table of Contents
1. Project Overview<br/>
2. Setup local machine<br/>
3. API Test Execution<br/>
4. Project Structure<br/>
5. Generate Allure REST Assured Report<br/>

## 1. Project Overview
This project is designed to automate API testing using REST Assured framework. It provides a set of predefined test cases to ensure the API behaves as expected.

## 2. Setup local machine
#### This guide assumes the following:
* Have Maven and Java 17 installed

## 3. API Test Execution
#### Maven Command Terminal
Use Maven as a tool for building and managing the project.<br/>
To execute all tests from **testng.xml**, use the following command:

        > mvn test

To execute test itself the next command line should be used:

        > mvn test -Dtest={String}

_Parameters:_ <br/>
`-Dtest={String}` - name of Test class<br/>

## 4. Project Structure
The Maven project has a **pom.xml** file and a directory structure based on defined conventions:
```
|——rest-assured-frmk
    |—-pom.xml
    |—-testng.xml
    |—-src
        |—-main
            |—-java
        |—-test
            |—-java
```

The **src** directory is the root directory of source code and test code.<br/>
The **main** directory is the root directory for source code related to the application itself (api services, helpers, tools, etc.), not test code.<br/>
The **test** directory contains the test source code related to implemented test-cases.<br/>
The **java** directories under main and test contains the Java code for the application itself which is under main and the Java code for the tests which is under test.<br/>
The **pom.xml** file contains information of project and configuration information for the Maven to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals etc.<br/>
The **testng.xml** file is a configuration file that helps organize the test classes.<br/>

## 4. Generate Allure REST Assured Report
To generate a report by Allure after tests have finished, use the following command: 

        > allure serve 
This command immediately opens the generated report in the default browser.

To generate the report from existing Allure results you can use the following command:
        
        > allure generate <directory-with-results>
The report will be generated to allure-report folder.