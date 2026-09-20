# Appium ATF Project

Java 21 + Appium 2 + Cucumber/JUnit 5 tests for the Android application
`md.moldcell.selfservice`.

## What to install

Install the following tools:

- JDK 21;
- Maven;
- Node.js;
- Android Studio with Android SDK and an Android Emulator;
- Appium 2 and the UiAutomator2 driver.

Install Appium globally:

```powershell
npm install -g appium
appium driver install uiautomator2
```

Create an Android Virtual Device with these default settings:

```text
AVD: Pixel_7
Device: Pixel 7
Android: 13
UDID: emulator-5554
```

Install the `My Moldcell` application on the emulator. The APK is not stored
in this repository.

## Environment variables

Do not store credentials in feature files, Java source code, or Git. Configure
the values as IntelliJ environment variables or in the current PowerShell
session.

### Required credentials

These variables are required by the authentication scenarios:

```text
MY_MOLDCELL_USERNAME_MOBILE
MY_MOLDCELL_USERNAME_EMAIL
MY_MOLDCELL_PASSWORD
```

In IntelliJ IDEA:

```text
Run → Edit Configurations → RunCucumberTest
→ Environment variables → Edit
```

Add:

```text
MY_MOLDCELL_USERNAME_MOBILE=mobile_number
MY_MOLDCELL_USERNAME_EMAIL=email
MY_MOLDCELL_PASSWORD=password
```

For the current PowerShell session:

```powershell
$env:MY_MOLDCELL_USERNAME_MOBILE = "mobile_number"
$env:MY_MOLDCELL_USERNAME_EMAIL = "email"
$env:MY_MOLDCELL_PASSWORD = "password"
```

### Optional environment variables

The following variables have defaults and are only needed when the default
configuration does not match the local setup:

```text
APPIUM_SERVER_URL=http://127.0.0.1:4723
ANDROID_DEVICE_NAME=Pixel 7
ANDROID_UDID=emulator-5554
ANDROID_PLATFORM_VERSION=13
MY_MOLDCELL_PACKAGE=md.moldcell.selfservice
MY_MOLDCELL_ACTIVITY=.screens.splash.SplashActivity
```

`.env.example` contains the variable names. The project does not load `.env`
automatically; use IntelliJ environment variables, PowerShell variables, or
JVM system properties.

## Run from IntelliJ IDEA

1. Start the Android Emulator from IntelliJ or Android Studio.
2. Start Appium in a terminal:

   ```powershell
   appium --address 127.0.0.1 --port 4723
   ```

3. Set the environment variables in `RunCucumberTest`.
4. Run the `RunCucumberTest` JUnit configuration.

IntelliJ automatically builds changed test sources and feature files before
running the configuration. `mvn clean test` is not required after every
scenario change.

## Run with Maven

With the emulator and Appium already running:

```powershell
mvn test
```

Use a clean build only when necessary:

```powershell
mvn clean test
```

## Test files and reports

Feature files:

```text
src/test/resources/features/language.feature
src/test/resources/features/user_authentication.feature
```

After execution, reports are generated in:

```text
target/cucumber-reports/cucumber.html
target/cucumber-reports/cucumber.json
```

Failed scenarios include a screenshot and Appium page source in the Cucumber
report.
