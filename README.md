# REST Assured API Demo Automation Project
This is the test automation project which supports:<br/>
* REST Assured

## Table of Contents
1. [Setup local machine](#one)
2. [Project Overview](#two)
3. [Project Structure](#three)
4. [Create API Test Scenarios](#four)
5. [API Test Execution](#five)
6. [Generate Allure REST Assured Report](#six)

<a id="one"></a>
## 1. Setup local machine
#### This guide assumes the following:
* Have **Maven** and **Java 17** installed.<br/>

<a id="two"></a>
## 2. Project Overview
This project is designed to automate API testing using the REST Assured framework. It provides a set of predefined test cases to ensure that the API behaves as expected for the ClickUp platform.<br/>

<a id="three"></a>
## 3. Project Structure
The Maven project has a `pom.xml` file and a directory structure based on defined conventions:<br/>
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
The `src` directory is the root directory of source code and test code.<br/>
The `main` directory is the root directory for source code related to the application itself (API services, helpers, tools, etc.), not test code.<br/>
The `test` directory contains the test source code of implemented test cases.<br/>
The `java` directories under main and test include the Java code for the application itself which is under main and the Java code for the tests which is under test.<br/>
The `pom.xml` file contains information on the project and configuration information for the Maven to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals, etc.<br/>
The `testng.xml` file is a configuration file that helps organize the test classes.<br/>

<a id="four"></a>
## 4. Create API Test Scenarios
Creating API test scenarios involves defining test data and expected API behavior. The `TestData` utility helps in generating and managing test data models, which can be customized as needed.<br/>

### Steps to Build API Test Scenarios
1. **Generate Required Test Data**:<br/>
   Use the `TestData` utility to generate the required test data model with necessary fields.<br/>
2. **Customize Test Data**:<br/>
   Modify the test data by removing fields or setting specific field values to suit different test scenarios.<br/>
3. **Define Response Specifications**:<br/>
   Create expected response specifications using REST Assured to validate API responses.<br/>
4. **Execute API Requests**:<br/>
   Perform API requests using the generated test data and validate responses against the defined specifications.<br/>

<a id="five"></a>
## 5. API Test Execution
#### Maven Command Terminal
Maven is used as a tool to build and manage the project.<br/>
Create a hidden `.env` file containing the parameters TOKEN, BASE_URL, and USER_NAME to configure the testing environment for API tests.
To obtain your personal token, please refer to the [helper](https://help.clickup.com/hc/en-us/articles/6303426241687-Use-the-ClickUp-API).<br/>

To execute all tests from `testng.xml`, use the following command:<br/>
```bash
mvn test
```
To execute test itself the next command line should be used:<br/>
```bash
mvn test -Dtest=SpacesScenarioTest
```

<a id="six"></a>
## 6. Generate Allure REST Assured Report
To generate a report by Allure after tests have finished, use the following command:<br/>
```bash
allure serve 
```
This command immediately opens the generated report in the default browser.<br/>

To generate the report from existing Allure results you can use the following command:<br/>
```bash
allure generate
```
The report will be generated in the `allure-report` folder.<br/>

#### Allure Report Overview:
![RestAssured_Allure](https://github.com/user-attachments/assets/d59e1106-5a89-46d6-b3a0-7a00a39c9704)
https://github.com/user-attachments/assets/09adba30-8582-487d-b5e3-1b0e62b67896


---
#### Copyright (c) 2024 Marta Kravchuk under MIT License.

