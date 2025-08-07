# Selenium Hybrid Framework 🚀

A robust hybrid automation framework built using **Selenium WebDriver**, **TestNG**, **Java**, and **Log4j2**, designed for cross-browser testing with support for data-driven testing, reporting, grouping, and remote execution (Grid/Docker).

---

## 📁 Project Structure
- `testBase` – Contains `BaseClass` for setup/teardown, config loading, browser init.
- `pageObjects` – Page Object Model classes for app pages.
- `testCases` – Test classes like Account Registration and Login.
- `utilities` – Helpers like ExcelUtility, DataProviders, ExtentReportUtility.
- `resources` – Holds `log4j2.xml`, `config.properties`, test data Excel.

---

## ✅ Features Overview

### 1. 🔧 Core Setup
- `BasePage` constructor reused across all POMs.
- `BaseClass` handles browser setup, logging, test data utilities.

### 2. 📝 Logging (Log4j2)
- Centralized logging setup with daily rolling logs.
- Helpful for debugging and audit trails.

### 3. 🌐 Cross-Browser & Parallel Execution
- `testng.xml` parameterizes browser/OS.
- Supports Chrome, Firefox, Edge in parallel.
- Master & cross-browser suite files created.

### 4. ⚙️ Config Management
- `config.properties` stores common values like `appURL`, `browser`, `execution_env`.
- Used to eliminate hard-coded values.

### 5. 🔐 Login & Registration Test Cases
- Account registration and login tests with validations.
- POM pattern with reusable actions.

### 6. 📊 Data-Driven Testing (DDT)
- Login tested with multiple inputs from Excel.
- `ExcelUtility` + `DataProviders` for external test data.

### 7. 🧪 Test Grouping
- Tests grouped into `sanity`, `regression`, `master`.
- Can execute groups via `grouping.xml`.

### 8. 📋 Extent Reporting
- Integrated ExtentReports for HTML test reports.
- Includes screenshots on failure.

### 9. 🔁 Rerun Failed Tests
- Uses `testng-failed.xml` from test-output directory.

### 10. 🧱 Selenium Grid & Docker Support
- Run tests locally or remotely using config flag.
- Grid/Docker setup supported.

### 11. ⚙️ Maven & Jenkins Ready
- Tests executable via `pom.xml`, command line, `.bat`, or Jenkins pipeline.
- GitHub integration enabled.

---

## 📌 How to Run
- Update browser/environment in `testng.xml` or `config.properties`
- Use IntelliJ/Eclipse or:
```bash
mvn clean test
```

---

## 🚀 Planned Enhancements
- Adding more test cases
- API automation support
- CI/CD workflows

---

## 📁 Sample Files
- `testng.xml`, `log4j2.xml`, `config.properties`, and Excel test data provided.

---

## 📬 Contact
For suggestions or contributions, feel free to fork or raise an issue.

---

> Built with ❤️ using Java + Selenium + TestNG
