## QA intern task of signup flow (Vrit Technologies) 
## Project Overview
This project automation was completed for signup flow of : https://authorized-partner.vercel.app/. This project covers all required steps of the signup 
Process properly without manual intervention .
It uses Selenium WebDriver with TestNG , mailosaur and follows the Page Object Model (POM) design pattern.

## Tech Stack
1.Java
2.Selenium WebDriver
3.TestNG
4.Maven
5.Mailosaur (for OTP verification)
6.Extent Reports / HTML Reporting

## Prerequisites
1.Make sure the following are installed:
2.Java JDK 20
3.Apache-maven-3.9.16
4.Chrome Browser
5.ChromeDriver (4.43.0)
## Dependencies
The project uses the following dependencies:
1.Selenium WebDriver
2.TestNG
3.WebDriverManager
4.Mailosaur Java 
5.Maven Surefire Plugin
6.Extentreports

## How to Run the Project
1. Clone the repository
 Run in cmd or bash :
  git clone https://github.com/riyatuitui/Task-QA-Intern-Vrit-Technologies-.git 
2. Import into IDE
Open in IntelliJ IDEA or Eclipse.
3. Install dependencies
mvn clean install
 4. Run tests using TestNG
Option 1:
mvn test
Option 2:
Run testNg.xml directly from IDE.


## Reporting
After execution, reports are generated automatically in:
/reports/Report*.html
This includes:
1.Test execution status
2.Step-by-step logs
3.Pass/Fail results
4.Dashboard

## Test Data
1.First Name: test
2.Last Name: user
3.Email:Generated using Mailosaur
4.OTP: Retrieved automatically from Mailosaur inbox
5.agencyName = "Test Agency"
6.role = "QA"
7.agencyEmail = "testagency"+ System.currentTimeMillis() + "@" + "gmail.com"
8.website = "test.com" 
9.address = "Bhaktapur"

## Project Structure 
src
|---- main
|---- test
      |---- base
      |---- pages
      |---- tests
      |---- utils
      |---- resources
pom.xml
Testng.xml

1.In base there is setup of lunching browser and website. 
2.In pages there are pages classes with locator and action
3.In tests the main signup flow is executed
4.In Utils there are mailosaur utilities for OTP extraction  and extend report utilities  for report generation
5.In resources there is pfd folder 
6.In pom.xml there are dependencies and project build
7.In Testing.xml there is test suite configuration and specifies the test classes to be executed 

## Automation Flow 
1.Lunch Browser and Navigate to home page
2.Navigate to register page 
3.Complete all required signup forms  
4.Retrieve OTP automatically from mailosaur inbox
5.Enter OTP and verify account
6.Fill all agency details
7.Fill all experience details
8.Fill all verification details
9.Submit form
10.Close browser
11.Generate report

## Key Features
1.Fully automated signup flow
2.OTP email verification
3.Page Object Model structure
4.Automated reporting via listener
5.No manual intervention required

## Prepared By:
Riya Tuitui
