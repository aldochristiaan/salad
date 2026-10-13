# 🥗 How To Salad

## 📦 Salad Library Setup

Add the Salad library to your project's root folder.

1. Create a `libs/` directory.
2. Add the following to your `build.gradle` file:

```
sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    ...
    testImplementation fileTree(dir: 'libs', include: ['*.jar'])
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.2'
    ...
}

test {
    useJUnitPlatform()
    testLogging {
        events "passed", "skipped", "failed"
    }
}
```

> ✅ As of v1.3.0-rc1 the Salad library itself has **no JUnit dependency at all** (it throws plain
> `AssertionError` on validation failures). The example projects use **JUnit 5** to manage the
> Appium server lifecycle, but you are free to use JUnit 4, JUnit 5, or TestNG in your own tests.

---

## ⚙️ Capabilities Properties

To run automation, you must provide `DesiredCapabilities`.

You can either:
- Load them from a `.properties` file, or
- Pass them directly in your code.

Place a `capabilities.properties` file in your project root:

```
app=/Users/APK/app-debug.apk
deviceName=XXXXXXXXX
udid=XXXXXXXXX
platformVersion=8.0.0
fullReset=false
autoGrantPermissions=true
```

🔗 [View full list of Appium capabilities](https://appium.io/docs/en/latest/guides/caps/)

---

## 🧪 Example Projects

Explore working examples here:

- [Espresso Example](https://github.com/aldochristiaan/salad/tree/master/examples/espresso)
- [UiAutomator2 Example](https://github.com/aldochristiaan/salad/tree/master/examples/uiautomator2)
- [Cucumber Example](https://github.com/aldochristiaan/salad/tree/master/examples/cucumber)

📱 Sample APKs are available [here](https://github.com/aldochristiaan/salad/tree/master/examples/apk)

---

## 📚 Getting Deep with Salad

Want to write your own automation code?

Check out the full [Salad Automation Guide](https://github.com/aldochristiaan/salad/blob/master/docs/Automation.md)
