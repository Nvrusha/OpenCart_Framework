# Jenkins Setup for OpenCart Automation Framework

This document explains how to configure and run the **OpenCart Selenium Automation Framework** using Jenkins locally.

---

# 1. Prerequisites

Ensure the following tools are installed before running the framework.

| Tool           | Purpose                                          |
| -------------- | ------------------------------------------------ |
| Java 17+       | Required to run Jenkins and automation framework |
| Maven          | Build tool used to execute automation tests      |
| Git            | Used to clone the automation repository          |
| Jenkins        | CI server used to run automation pipelines       |
| Chrome Browser | Browser used for test execution                  |

---

# 2. Verify Installation

Run the following commands to confirm the tools are installed correctly.

```
java -version
mvn -version
git --version
```

Expected output should display the installed versions.

---

# 3. Clone the Automation Repository

Clone the OpenCart automation framework from GitHub.

```
git clone https://github.com/Nvrusha/OpenCart_Framework.git
```

Navigate to the project directory.

```
cd OpenCart_Framework
```

---

# 4. Running Jenkins

Jenkins can be executed in two ways depending on the environment.

---

# 4.1 Local Development Mode

For local testing and debugging, Jenkins can be started manually using the WAR file.

Sometimes Jenkins installed as a Windows service runs under the **SYSTEM user**, which does not have desktop access.
This can cause Selenium to fail with the following error:

```
The application does not have desktop access
```

To avoid this issue, Jenkins should be started manually.

### Step 1 – Stop Jenkins Service

Open Windows Services:

```
services.msc
```

Locate:

```
Jenkins
```

Right-click and select:

```
Stop
```

---

### Step 2 – Run Jenkins using WAR file

Navigate to the Jenkins installation directory.

```
cd "C:\Program Files\Jenkins"
```

Start Jenkins manually.

```
java -jar Jenkins.war
```

Open Jenkins in your browser:

```
http://localhost:8080
```

Note:

The command prompt running Jenkins **must remain open** while Jenkins is running.

---

# 4.2 CI Environment Mode (Recommended for Production)

In real CI environments Jenkins runs as a **background service** on a CI server.

Automation tests should use **headless browser execution** so that tests run without requiring a graphical desktop.

Example configuration for headless Chrome execution:

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new");
options.addArguments("--disable-gpu");
options.addArguments("--window-size=1920,1080");

WebDriver driver = new ChromeDriver(options);
```

This allows Jenkins to execute Selenium tests reliably even when running as a service.

---

# 5. Creating Jenkins Job

Open Jenkins dashboard:

```
http://localhost:8080
```

Create a new job:

```
New Item
→ Freestyle Project
→ Name: OpenCartProject
```

Click **OK**.

---

# 6. Configure Source Code Management

Under **Source Code Management**, select:

```
Git
```

Repository URL:

```
https://github.com/Nvrusha/OpenCart_Framework.git
```

Branch:

```
master
```

---

# 7. Configure Build Step

Scroll to **Build Steps**.

Add:

```
Invoke top-level Maven targets
```

Goals:

```
clean test
```

This command runs the automation tests using Maven.

---

# 8. Execute the Job

Click:

```
Build Now
```

Jenkins will perform the following steps:

```
Clone repository from GitHub
      ↓
Compile project using Maven
      ↓
Execute TestNG test suite
      ↓
Run Selenium automation tests
```

---

# 9. Expected CI Pipeline

The automation pipeline follows this flow:

```
GitHub Repository
       ↓
Jenkins Job
       ↓
Maven Build
       ↓
TestNG Execution
       ↓
Selenium WebDriver
       ↓
Test Execution Results
```

---

# 10. Test Reports

Test execution results are generated in the following directory:

```
target/surefire-reports
```

Jenkins console output will display:

```
Tests run: X
Failures: 0
BUILD SUCCESS
```

---

# 11. Troubleshooting

### Issue: Application does not have desktop access

Start Jenkins manually instead of running it as a Windows service.

```
java -jar Jenkins.war
```

---

### Issue: Chrome Browser Not Launching

Verify the following:

* Chrome browser is installed
* ChromeDriver version matches the Chrome browser version
* Chrome is accessible on the Jenkins machine

---

### Issue: Maven Command Not Found

Ensure Maven is installed and available in the system PATH.

Verify using:

```
mvn -version
```

---

# 12. Useful Maven Commands

| Command        | Description                      |
| -------------- | -------------------------------- |
| mvn clean      | Deletes previous build artifacts |
| mvn test       | Executes TestNG test cases       |
| mvn clean test | Cleans project and runs tests    |

---


