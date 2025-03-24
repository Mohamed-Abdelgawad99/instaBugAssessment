# You can find the answers for the conceptual questions in a PDF file in the repo root directory with name **Conceptual Q.pdf**
---
#  Instabug Assessment Automation Framework

This is a dynamic test automation framework built using:
- **Java**
- **Cucumber (BDD)**
- **Appium** for mobile automation
- **Selenium** for web testing
- **RestAssured** for API testing
- **Allure** for advanced reporting

---

##  Project Structure

```
instabug_model_1_assesment/
├───.idea
├───allure-results/
├───allure-report/
├───apk-files/  #For the test app apk
├───drivers/  # Contains the chromedriver
├───src/
│   ├───main/
│   │   ├───java/
│   │   │   ├───pages/
│   │   │   │   ├───mobileAppPages/
│   │   │   │   └───webAppPages/  
│   │   │   └───utils/  # Mobile and Web drivers handlers for istantiantion
│   │   └───resources/  # runner.properties file testcase controler
│   └───test/
│       ├───java/
│       │   ├───hooks/   # contains hooks for webapp testcases 
│       │   ├───runners/  # Entry point to run testcases
│       │   ├───stepDefinitions/
│       │   │   ├───mobileAppSteps/   #Step definitions files for mobile testcases
│       │   │   ├───restAPISteps/     #Step definitions files for resapi testcases
│       │   │   └───webAppSteps/      #Step definitions files for webapp testcases
│       └───resources/
│           └───features/
│               ├───mobileApp/  # Feature files for mobile testcases
│               ├───restAPI/    # Feature files for restapi testcases
│               └───webApp/     # Feature files for webapp testcases
└───temp-allure-history/

```

---

##  Prerequisites

Before running the tests, make sure you have the following installed:

### ✅ Java & Tools
- **Java JDK 22**
- **Maven**
- **Node.js + Appium 2.17.1**
- **Android SDK (with platform-tools, emulator, system-images)**
- **Allure CLI (for report generation)**

---
## Table of Contents
- [1. General Overview](#1-general-guide-through-the-framework)
   - [1.1 Cloning the Repository](#-11-cloning-the-repository)
   - [1.2 Using `runner.properties`](#-12-using-runnerproperties)
   - [1.3 Configuring IntelliJ to Run the Framework](#-13-configuring-intellij-to-run-the-framework)

- [2. Allure Reporting](#-4-allure-reporting)

- [3. Web Application Testing](#-5-web-application-testing)

- [4. REST API Testing](#-6-rest-api-testing)

- [5. Mobile App Testing](#-7-mobile-app-testing-android)
---
## 1. General Guide through the Framework
## 📥 1.1 Cloning the Repository

To get started, clone the project locally:

```bash
git clone https://github.com/Mohamed-Abdelgawad99/instaBugAssessment.git
cd instabug_model_1_assesment
```

Then install dependencies:

```bash
mvn clean install
```

---

### ⚙️ 1.2 Using `runner.properties`

The framework uses a central config file at:

```
src/main/resources/runner.properties
```

This file controls **what test to run, how to run it, and where**.

#### ✅ Example

```properties
AutomationTestType=mobile         # Options: mobileapp, webapp, restapi
featureFile=Login                 # Feature file name (without .feature)
testCaseID=ID001                  # Cucumber scenario tag
```

🔹 You can change values based on the test type you want to execute.  
🔹 The framework dynamically picks the correct driver, test path, and step definitions based on this file.

---

### 🛠️ 1.3 Configuring IntelliJ to Run the Framework

To run tests directly from IntelliJ using `TestRunner.java`:

1. Go to **Run → Edit Configurations**
2. Click the **➕ (Add)** icon → Choose **Application**
3. Set:
    - **Name**: `Run TestRunner`
    - **Main class**: `runners.TestRunner`
    - **Working directory**: set to your project root
    - **Use classpath of module**: select your test module

4. Click **Apply** and **OK**
5. Now go to the top right and hit the **green Run button** ▶️

💡 Make sure `runner.properties` is properly configured before running.

## 📊 4. Allure Reporting

This framework is pre-configured with **Allure reporting** to provide a clean and interactive way to visualize test results.

---

### ⚡ One-Click Allure Integration

To make it easier for all users, a dedicated **Allure CLI (`allure.bat`)** has been included at:

```
bin/allure-2.33.0/bin/allure.bat
```

✅ You **do not need to install Allure globally** or configure additional paths — the CLI is ready to use locally.

---

### ⚙️ How It Works

After each test run, the following steps happen automatically:

1. **Preserve Allure report history** from the previous run using `AllureHistoryHelper.preserveHistory()`
2. **Generate a new Allure report** from the latest test execution:
   - Reads from `allure-results/`
   - Outputs to `allure-report/`
3. **Restore execution history** into the new report (trend tracking, flaky test detection, etc.)
4. **Start an Allure server** and **automatically open the report in your default browser**
5. Waits for you to press `ENTER` in the IDE's terminal to stop the server

---

### 📜 Code That Powers It

```java
try {
    String allurePath = "bin/allure-2.33.0/bin/allure.bat";
    AllureHistoryHelper.preserveHistory();
    
    Process generateReport = new ProcessBuilder(allurePath, "generate", "allure-results", "--clean", "-o", "allure-report")
        .inheritIO()
        .start();
    generateReport.waitFor();

    AllureHistoryHelper.restoreHistory();

    Process openReport = new ProcessBuilder(allurePath, "serve", "allure-results")
        .inheritIO()
        .start();

    System.out.println("\n✅ Allure report is running.");
    System.out.println("📂 Opened in your browser from local server.");
    System.out.println("🛑 Press ENTER to stop the Allure server and exit...");

    System.in.read(); // Wait for user input
    new ProcessBuilder("taskkill", "/F", "/IM", "java.exe", "/T").start();
    System.out.println("🚪 Allure server stopped. Goodbye!");
} catch (IOException | InterruptedException e) {
    throw e;
}
```

---

### 📂 Report Folders

| Folder | Description |
|--------|-------------|
| `allure-results/` | Stores raw JSON files from test execution |
| `allure-report/`  | Stores the HTML version of the Allure report |
| `.history/`       | Preserved report history for trends and graphs |

---

### ✅ Viewing the Report

Once your tests complete, the report will **automatically open in your default browser**.  
To stop the Allure server, **go to your IDE terminal and press `ENTER`**.

---

## 🌐 5. Web Application Testing

This framework includes support for **end-to-end web application testing** using **Selenium WebDriver** and **Cucumber** with a modular, readable, and maintainable structure.

---

### 🧪 Sample Target Website

Web application test cases in this framework are executed against the following **sample e-commerce website**:

```
https://magento.softwaretestingboard.com/
```

📝 You can change the target site by modifying the **`baseURL`** value inside the `WebDriverFactory.java` utility file located here:

```
src/main/java/utils/WebDriverFactory.java
```

---

### 🗂️ Project Structure for Web Testing

Web test cases are organized into three main parts:

| Component | Location |
|----------|----------|
| ✅ Feature Files | `src/test/resources/features/webApp/` |
| ✅ Step Definitions | `src/test/java/stepDefinitions/webAppSteps/` |
| ✅ Page Objects | `src/main/java/pages/webAppPages/` |
| ✅ Hooks (optional setup/teardown) | `src/test/java/hooks/` |
| ✅ Driver management | `src/main/java/utils/WebDriverFactory.java` |


---

###  Sample Execution Flow

1. The test runner loads the correct `featureFile` and `testCaseID` from `runner.properties`
2. It identifies `AutomationTestType=web` and loads:
   - Web-specific feature file from `features/webApp/`
   - Step definitions from `stepDefinitions/webAppSteps/`
   - Page objects from `pages/webAppPages/`
3. WebDriver is initialized via `WebDriverFactory`
4. The browser launches, executes the test, and reports are generated

---

### 🌐 Supported Browsers

The framework currently supports:
- ✅ Google Chrome (default via ChromeDriver)

You can add more browsers by enhancing `WebDriverFactory.java` to support Firefox, Edge, etc., and selecting the browser via:

---

With this structure, you can easily write new web tests, switch environments, and generate rich test reports with Allure.

---
## 🔌 6. REST API Testing

This framework includes support for **REST API testing** using **RestAssured** and **Cucumber**, 

---

### 🌐 Sample API Base URL

The API test cases are built to execute against a **sample REST API testing website**, where you can create custom endpoints for mock scenarios.  
The default base URL used for testing is:

```
https://asses.free.beeceptor.com
```

> You can change the API base URL inside the `UserApiSteps.java` file`.

---


### 🧩 Test Structure for API

| Component | Location |
|----------|----------|
| ✅ Feature Files | `src/test/resources/features/restAPI/` |
| ✅ Step Definitions | `src/test/java/stepDefinitions/restAPISteps/` |
| ✅ Test Runner | `TestRunner.java` with `AutomationTestType=restAPI` |

---

## 📱 7. Mobile App Testing (Android)

This framework supports **Android mobile app testing** using **Appium** and **Cucumber**

To run mobile tests, follow these steps to set up your local environment.

---

### 🧰 Prerequisites for Android Testing

Before running mobile tests, make sure to set up the Android SDK and emulator.

---

### ✅ Step 1: Download the Android SDK Command Line Tools

1. Go to the official Android [command line tools download page](https://developer.android.com/studio#cmdline-tools)
2. Download the ZIP for Windows (e.g., `commandlinetools-win-xxxx_latest.zip`)

---

### ✅ Step 2: Setup the SDK Folder Structure

1. Unzip the downloaded file into:

   ```
   C:/Android/sdk
   ```

2. Inside `C:/Android/sdk/cmdline-tools`, create a folder named:

   ```
   latest
   ```

3. Move the extracted `bin` and `lib` folders into:

   ```
   C:/Android/sdk/cmdline-tools/latest
   ```

✅ Your structure should now look like:

```
C:/Android/sdk/cmdline-tools/latest/bin
```

---

### ✅ Step 3: Add Environment Variables

Add the following paths to your system `PATH` environment variable:

```
C:/Android/sdk/cmdline-tools/latest/bin
```

Then install required tools by running:

```bash
sdkmanager "platform-tools" "emulator"
```

Add these to `PATH` too:

```
C:/Android/sdk/platform-tools
C:/Android/sdk/emulator
```

Restart your terminal after updating your environment variables.

---

### 🚀 Step 4: Initialize the Appium Server

Make sure Appium 2.x is installed and the `uiautomator2` driver is installed:

```bash
appium driver install uiautomator2
appium
```

This will start the Appium server on `http://127.0.0.1:4723`.

---

### 📱 Step 5: Start Your Android Emulator

Create an emulator using `avdmanager` or Android Studio.  
Start it using:

```bash
emulator -avd Your_Emulator_Name
```

Check it's running:

```bash
adb devices
```

You should see:

```
emulator-5554	device
```

---

### 🛠️ Step 6: Update Driver Config

Open this file:

```
src/main/java/utils/MobileAppDriverManager.java
```

Update the following values:

```java
caps.setCapability("deviceName", "emulator-5554");       // Match 'adb devices' output
caps.setCapability("platformVersion", "11.0");           // Match emulator version
new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps); // Appium server URL
```

Also make sure your `.apk` path is valid:

```java
caps.setCapability("app", new File("apk-files/your-app.apk").getAbsolutePath());
```

---

Once everything is ready:
- Emulator is running
- Appium server is running
- `runner.properties` is set to `AutomationTestType=mobileapp`

Run your test and watch the app in action! 🚀



