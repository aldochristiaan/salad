# 🥗 How To Salad

## 📦 Salad Library Setup

Add the Salad library to your project's root folder.

1. Create a `libs/` directory.
2. Add the following to your `build.gradle` file:

```
sourceCompatibility = JavaVersion.VERSION_21

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

### Building the Salad JAR yourself

If you're hacking on Salad itself (from a clone of this repository):

```bash
./gradlew clean build            # full build with tests
./gradlew clean build -x test    # build without tests
./gradlew test                   # run unit tests only
./gradlew shadowJar              # create the fat JAR
./gradlew clean test shadowJar   # complete release build
./gradlew compileJava            # compile check only
```

The output JAR lands in `build/libs/salad-<version>.jar` (e.g. `build/libs/salad-1.3.0-rc1.jar`).

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

Load it in your factory:

```java
Properties capabilities = PropertiesLoader.loadCapabilities("capabilities.properties");
```

🔗 [View full list of Appium capabilities](https://appium.io/docs/en/latest/guides/caps/)

---

## 🧩 Element Properties

Locators live in `.properties` files (see [Automation.md](Automation.md) for the format).
Load a whole directory of them once at startup:

```java
PropertiesLoader loader = PropertiesLoader.getInstance();
loader.loadFromDirectory("src/test/resources/elements");

String locator = loader.getProperty("ANDROID_TOOLBAR");
String fallback = loader.getProperty("MISSING_KEY", "xpath_//*"); // optional default
```

You can call `loadFromDirectory(...)` multiple times — the files merge into one store.

---

## 🔄 Migration Guide (v1.2.x → v1.3.0)

**No action is required — existing tests keep working.** Deprecated APIs
(`Salad.ELEMENT_PROPERTIES`, `Salad.CAPABILITIES_PROPERTIES`, iOS `Tap.pendingElement`)
remain functional until **v2.0.0**. Recommended updates, at your own pace:

**Configuration constants**
```java
// Old (still works)
import static id.aldochristiaan.salad.Salad.DEFAULT_TIMEOUT;
int timeout = DEFAULT_TIMEOUT;

// New
import id.aldochristiaan.salad.config.SaladConfig;
int timeout = SaladConfig.DEFAULT_TIMEOUT;
Duration d = SaladConfig.toDuration(30);
```

**Properties access**
```java
// Old (still works)
String v = Salad.ELEMENT_PROPERTIES.getProperty("loginButton");

// New
String v = PropertiesLoader.getInstance().getProperty("loginButton", "default");
```

**Waiting**
```java
// Before
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

// After
WebDriverWait wait = new WebDriverWait(driver, SaladConfig.toDuration(SaladConfig.DEFAULT_TIMEOUT));
```

**New helper methods**
```java
getElement().withDefaultTimeout("loginButton");   // instead of withLocator("loginButton", 30)
tap().elementWithWait("button", 10);              // instead of pendingElement(...)
toast().isVisible();                              // instead of manual polling
```

---

## ❓ FAQ & Troubleshooting

**Q: Do I need to update my existing tests?**
A: No. All existing tests work without changes.

**Q: Will the old API be removed?**
A: Not in 1.x. Deprecated APIs are removed in v2.0.0.

**Q: When should I start using the new APIs?**
A: Whenever you write or touch a test — there's no rush.

**Issue: "Cannot resolve symbol SaladConfig"**
→ Add `import id.aldochristiaan.salad.config.SaladConfig;`

**Issue: "Cannot resolve symbol PropertiesLoader"**
→ Add `import id.aldochristiaan.salad.util.PropertiesLoader;`

**Issue: Deprecation warnings in my build**
→ Expected. They point at the old static fields; migrate when convenient.

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

- [Salad Automation Guide](Automation.md) — framework concepts, page objects, locators
- [Espresso Modules](Espresso.md) · [UiAutomator2 Modules](UiAutomator2.md) · [XCUITest Modules](XCUITest.md)
- [CHANGELOG.md](../CHANGELOG.md) — what changed in each version
