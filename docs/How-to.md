#How To Salad

### Salad library

Add salad library to your project's root folder.

Create `libs` directory and add this to your `build.gradle` file:

```
dependencies {
    ...
    testImplementation fileTree(dir: 'libs', include: ['*.jar'])
    testImplementation 'org.junit.jupiter:junit-jupiter:5.4.2'
    ...
}

test {
    useJUnitPlatform()
    testLogging {
        events "passed", "skipped", "failed"
    }
}
```

We are using Junit5 instead of Junit4. To help appium server lifecycle.

### Capabilities Properties

In order to make automation works you have to provide `DesiredCapabilities`.

You must provide a file contains list of capabilities or you can pass it as a parameter in your code. 

Place `capabilities.properties` in project root folder:

```
app=/Users/aldochristian/Dropbox/APK/app_wholesale-release.apk
deviceName=XXXXXXXXX
udid=XXXXXXXXX
platformVersion=8.0.0
fullReset=false
netspeed=full
autoGrantPermissions=true
skipLogcatCapture=true
unlockType=pin
unlockKey=2500
```

You can see list of appium capabilities [here](http://appium.io/docs/en/writing-running-appium/caps/)

### Project Structure

You can create project structure as you want, but I suggest you to follow this following structure to make it easy to work on.

```
...
|-libs
  |- salad-1.0.0.jar
|-src
  |- test
    |- java
      |- packageName
        |- pageobject // Contains app's page objects
          |- HomePage.java
          |- LoginPage.java
        |- testcase // Contains test cases
          |- LoginTest.java
          |- CreateTransaction.java
        |- YourApp.java // Contains list of pageObject
      |- YourAppFactory.java // Automation Factory
|- capabilities.properties // Automation capabilities
...
```

#### Start writing your code

Look at this [documentation](https://github.com/aldochristiaan/salad/blob/master/docs/Automation.md) to write your automation code!

