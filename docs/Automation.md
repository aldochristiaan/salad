# 🧪 Salad Automation Framework

Salad is a modular mobile automation framework designed to be flexible, scalable, and easy to integrate with any test strategy. It supports multiple drivers, dynamic element locators, and reusable page object patterns.

---

## 🚀 Getting Started

### 🏗️ Create Your Application Factory

Create a factory class to initialize your automation engine and register your app modules.

> Replace `YourApp` with your actual app name (e.g., `FacebookFactory`).

```java
public class YourAppFactory {

    private static Salad salad;
    protected static YourApp yourApp;

    @BeforeAll
    public static void setUp() {
        String elementPropertiesDirectory = "src/test/resources/elements/";
        String capabilitiesFileName = "capabilities.properties";
        Properties capabilitiesProperties = PropertiesLoader.loadCapabilities(capabilitiesFileName);

        salad = new Salad(
            capabilitiesProperties,
            elementPropertiesDirectory,
            Driver.ESPRESSO,
            LogLevel.ERROR
        );

        salad.start();
        AndroidDriver<AndroidElement> androidDriver = salad.getAndroidDriver();
        yourApp = new YourApp(androidDriver);
    }

    @AfterAll
    public static void tearDown() {
        salad.stop(Driver.ESPRESSO);
    }
}
```

---

## 📦 Supported Modules

Salad supports multiple mobile drivers:

- [Espresso](src/main/java/id/aldochristiaan/salad/module/android/espresso)
- [UiAutomator2](src/main/java/id/aldochristiaan/salad/module/android/uiautomator2)
- [XCUITest](src/main/java/id/aldochristiaan/salad/module/ios)

Feel free to contribute new modules or methods via pull requests!

---

## 📄 Page Object with Element Properties

Instead of hardcoding locators, Salad uses `.properties` files for cleaner and reusable element definitions.

### Example: `MainPage.java`

```java
public class MainPage extends BasePage {

    public MainPage(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void isOnMainPage() {
        validateDisplayed("ANDROID_TOOLBAR", 5);
    }

    public void validateDrawer() {
        validateDisplayed(constructLocator("GENERAL_TEXT", "android.studio@android.com"), 2);
        validateDisplayed(constructLocator("GENERAL_CONTAINS_TEXT", "Home"), 2);
        validateDisplayed(constructLocator("GENERAL_TRANSLATION_TEXT", "Gallery"), 2);
        validateDisplayed(constructLocator("GENERAL_XPATH", "//*[@text='Slideshow']"), 2);
    }

    public void openDrawer() {
        drawer().open("ANDROID_DRAWER");
    }

    public void closeDrawer() {
        drawer().close("ANDROID_DRAWER");
    }

    public void tapOnFABUsingUiAutomator() {
        uiAutomator().sauce(Strategy.clazz, "com.google.android.material.floatingactionbutton.FloatingActionButton", Action.click);
        tap().element("ANDROID_FLOATING_ACTION_BUTTON");
    }

    public void tapOnFAB() {
        tap().element("ANDROID_FLOATING_ACTION_BUTTON");
    }

    public void tapMoreOptions() {
        tap().element("ANDROID_MORE_OPTIONS");
        pressBackButton();
    }

    public void goToPages() {
        openDrawer();
        tap().element(constructLocator("GENERAL_TEXT", "Home"));
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is home Fragment"), 2);
        openDrawer();
        tap().element(constructLocator("GENERAL_TEXT", "Gallery"));
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is gallery Fragment"), 2);
        openDrawer();
        tap().element(constructLocator("GENERAL_TEXT", "Share"));
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is share Fragment"), 2);
    }

    public void debugElementUsingFlash() {
        flash().element("ANDROID_FLOATING_ACTION_BUTTON", 500, 4);
    }

    public void failedMethod() {
        tap().element(constructLocator("GENERAL_TEXT", "Failed"));
    }
}
```

---

## 🧩 Element Properties

### `General.properties`

```
GENERAL_TEXT=text_%s
GENERAL_CONTAINS_TEXT=containsText_%s
GENERAL_TRANSLATION_TEXT=translationText_%s
GENERAL_ID=id_%s
GENERAL_CONTENT_DESCRIPTION=contentDescription_%s
GENERAL_XPATH=xpath_%s
```

### `MainPage.properties`

```
ANDROID_DRAWER=id_drawer_layout
ANDROID_TOOLBAR=id_toolbar
ANDROID_FLOATING_ACTION_BUTTON=xpath_//com.google.android.material.floatingactionbutton.FloatingActionButton
ANDROID_MORE_OPTIONS=contentDescription_More options
```

---

## 🔍 Locator Strategies

Supported locator types:

- id
- accessibilityId
- contentDescription
- text
- containsText
- translationText
- name
- label
- value
- labelcontains
- class
- xpath
- viewTag

Use `constructLocator()` to dynamically build locators:

```
tap().element(constructLocator("GENERAL_TEXT", "Waiting..."));
tap().element(constructLocator("DYMAMIC_BUTTON_XPATH", "Search"));
```

---

## 🧭 Register Page Objects

```java
public class YourApp {

    private AndroidDriver<AndroidElement> androidDriver;

    public YourApp(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public MainPage homePage() {
        return new MainPage(androidDriver);
    }
}
```

---

## 🧪 Write Your Tests

Extend your factory and use page objects to write expressive tests.

```java
@ExtendWith(TestListener.class)
public class AndroidTest extends YourAppFactory {

    @Test
    public void testA() {
        yourApp.homePage().isOnMainPage();
        yourApp.homePage().tapOnFAB();
        yourApp.homePage().tapMoreOptions();
        yourApp.homePage().openDrawer();
        yourApp.homePage().validateDrawer();
        yourApp.homePage().closeDrawer();
        yourApp.homePage().goToPages();
        yourApp.homePage().tapOnFABUsingUiAutomator();
        yourApp.homePage().failedMethod(); // Expected to fail
    }

    @Test
    public void testB() {
        yourApp.homePage().isOnMainPage();
        yourApp.homePage().tapOnFAB();
        yourApp.homePage().tapMoreOptions();
        yourApp.homePage().openDrawer();
        yourApp.homePage().validateDrawer();
        yourApp.homePage().closeDrawer();
        yourApp.homePage().goToPages();
        yourApp.homePage().tapOnFABUsingUiAutomator();
        yourApp.homePage().debugElementUsingFlash();
    }
}
```

---

## 🔗 Framework Integration

Salad works seamlessly with:

- ✅ **JUnit 5**
- ✅ **TestNG**
- ✅ **Cucumber**
- ✅ **Custom runners and listeners**

---

## 🤝 Contribute

Found something reusable or want to extend a module? Submit a pull request and help improve the framework!
