# Selenium Hybrid Automation Framework 🚀

A robust **Hybrid Test Automation Framework** built using **Selenium WebDriver, Java, TestNG, Maven, and Log4j2**.
The framework supports **cross-browser testing, data-driven testing, reporting, CI execution with Jenkins, and Selenium Grid support**.

This project demonstrates how a **production-level automation framework** is structured for scalable UI test automation.

---

# 🧰 Tech Stack

| Tool               | Purpose                                    |
| ------------------ | ------------------------------------------ |
| Selenium WebDriver | Browser automation                         |
| Java               | Programming language                       |
| TestNG             | Test execution framework                   |
| Maven              | Build and dependency management            |
| Log4j2             | Logging framework                          |
| Jenkins            | Continuous Integration execution           |
| GitHub             | Source code management                     |
| Excel              | External test data for data-driven testing |

---

# 📁 Project Structure

```
OpenCart_Framework
│
├── src/test/java
│   ├── testBase        → BaseClass for setup, teardown and configuration
│   ├── pageObjects     → Page Object Model classes
│   ├── testCases       → Test scripts (Login, Registration etc.)
│   └── utilities       → ExcelUtility, DataProviders, ExtentReportUtility
│
├── src/test/resources
│   ├── config.properties
│   ├── log4j2.xml
│   └── test data Excel files
│
├── test-output         → TestNG execution results
├── screenshots         → Failure screenshots
├── pom.xml             → Maven dependencies
└── JENKINS_SETUP.md    → Jenkins execution guide
```

---

# ✅ Framework Features

## 1️⃣ Base Framework Setup

* **BaseClass** handles browser initialization, configuration loading, and teardown.
* **BasePage** constructor reused across Page Object classes.

---

## 2️⃣ Logging (Log4j2)

* Centralized logging configuration using **Log4j2**.
* Logs generated during test execution.
* Useful for debugging automation failures.

---

## 3️⃣ Cross-Browser Testing

Supports execution on:

```
Chrome
Firefox
Edge
```

Browser selection can be controlled via:

```
testng.xml
config.properties
```

---

## 4️⃣ Configuration Management

Environment configuration stored in:

```
config.properties
```

Example:

```
appURL=https://tutorialsninja.com/demo/
execution_env=local
```

This avoids hardcoded values in test scripts.

---

## 5️⃣ Page Object Model (POM)

The framework follows **Page Object Model design pattern**.

Benefits:

* Better maintainability
* Reusable page methods
* Cleaner test cases

---

## 6️⃣ Data Driven Testing (DDT)

Login tests support **multiple test data inputs from Excel**.

Components used:

```
ExcelUtility
DataProviders
```

This enables externalized test data.

---

## 7️⃣ Test Grouping

TestNG groups allow selective execution:

```
Sanity
Regression
Master
```

Execution can be controlled through:

```
grouping.xml
```

---

## 8️⃣ Extent Reports

The framework integrates **ExtentReports** for HTML test reports.

Features include:

* Test pass/fail status
* Screenshots for failed tests
* Execution summary

Reports generated after execution.

---

## 9️⃣ Rerun Failed Tests

TestNG automatically generates:

```
testng-failed.xml
```

This allows rerunning only failed test cases.

---

## 🔟 Selenium Grid / Remote Execution

Framework supports both:

```
Local execution
Remote execution via Selenium Grid / Docker
```

Controlled via:

```
execution_env property in config.properties
```

---

## 1️⃣1️⃣ Maven Execution

Tests can be executed using Maven.

```
mvn clean test
```

This performs:

```
Clean previous build
Compile project
Run TestNG tests
```

---

## 1️⃣2️⃣ Jenkins CI Integration

The framework is integrated with **Jenkins for Continuous Integration**.

Pipeline flow:

```
GitHub
   ↓
Jenkins Job
   ↓
Maven Build
   ↓
TestNG Execution
   ↓
Selenium Tests
   ↓
Test Reports
```

Detailed setup instructions are available in:

```
JENKINS_SETUP.md
```

---

# ▶️ How to Run the Framework

Clone the repository:

```
git clone https://github.com/Nvrusha/OpenCart_Framework.git
```

Navigate to project directory:

```
cd OpenCart_Framework
```

Run tests:

```
mvn clean test
```

---

# 📊 Expected Test Execution Flow

```
TestNG Suite
     ↓
Browser Initialization
     ↓
Page Object Methods
     ↓
Test Execution
     ↓
Logging + Reporting
     ↓
Screenshots on Failure
```

---

# 🚀 Future Enhancements

Planned improvements:

* API automation integration
* CI pipeline improvements
* Parallel execution optimization

---

⭐ If you find this framework helpful, feel free to **star the repository**.

Built with ❤️ using **Java + Selenium + TestNG**
