# T-Mobile Tech Assessment - Java/Rest Assured Project

This project is a tech assessment for the recruitment process at T-Mobile. It automates a specific scenario using API of Polish Bank (http://api.nbp.pl)

## Scenario
    1. GET request to get exchange rates is made
    2. The response code <expectedStatusCode> should be displayed
    3. Exchange rate for currency with code <currencyCode> should be displayed
    4. Exchange rate for currency with name <currencyName> should be displayed
    5. Currencies with exchange rate over: <minValue> should be displayed
    6. Currencies with exchange rate under: <maxValue> should be displayed

## Requirements

- Java JDK - Ensure Java is installed on your machine.
- Maven

## Installation
Download and install latest JDK. Set Path and JAVA_HOME environment variables.

## Running Test Locally
To run the test use the 
```bash
mvn test
``` 
command. Ensure that you have the necessary dependencies installed before running the tests.

## BDD
This project uses BDD principles using Cucumber. Feature files can be found in test/resources folder. Step definitions can be found in test/java/stepdef folder

### Parametrization
To parametrize the input/output Scenario Outline has been used. In the Scenario Outline actual values in steps are changed to parameters, such as _currencyCode_ or _currencyName_. Then, in the Examples table, user can enter desired values configurations as next rows - each row will be executed as separate scenario.

