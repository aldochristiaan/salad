# How To Salad

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
autoGrantPermissions=true
```

You can see list of appium capabilities [here](http://appium.io/docs/en/writing-running-appium/caps/)

### Example Project

You can check example project here:

- [Espresso](https://github.com/aldochristiaan/salad/tree/document/examples/espresso)
- [UiAutomator2](https://github.com/aldochristiaan/salad/tree/document/examples/uiautomator2)

We're using sample android application and you can find the APK [here](https://github.com/aldochristiaan/salad/tree/document/examples/apk).

#### Getting deep with salad

Look at this [documentation](https://github.com/aldochristiaan/salad/blob/master/docs/Automation.md) to write your automation code!