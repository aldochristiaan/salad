# Automate your test

### Create Application Factory

First of all, you have to make `YourAppFactory.java` to make it as your automation engine that control how your automation works.

P.S: You can change `<YourApp>` with your application name. Ex: `FacebookFactory.java`

```
public class YourAppFactory {

   private static Salad salad; // Automation Engine
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
       androidDriver = salad.getAndroidDriver();
       yourApp = new YourApp(androidDriver);
   }
   
   @AfterAll
   public static void tearDown() {
       salad.stop(Driver.ESPRESSO);
   }
}

```

### List of modules

You can use modules provided based on mobile driver :

- [Espresso](https://github.com/aldochristiaan/salad/tree/document/src/main/java/id/aldochristiaan/salad/module/android/espresso)
- [UiAutomator2](https://github.com/aldochristiaan/salad/tree/document/src/main/java/id/aldochristiaan/salad/module/android/uiautomator2)
- [XCUITest](https://github.com/aldochristiaan/salad/tree/document/src/main/java/id/aldochristiaan/salad/module/ios)

Feel free to add more methods if it can be used generally. Just create a PR :)

### Provide Element Properties and Page Object

In general, Page Object Pattern define its element inside the file.

But we will use another approach by using element properties to make it cleaner and reusable.

Example:

```
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

Page properties : 

- `General.properties`

```
GENERAL_TEXT=text_%s
GENERAL_CONTAINS_TEXT=containsText_%s
GENERAL_TRANSLATION_TEXT=translationText_%s
GENERAL_ID=id_%s
GENERAL_CONTENT_DESCRIPTION=contentDescription_%s
GENERAL_XPATH=xpath_%s
```

- `MainPage.properties`

```
ANDROID_DRAWER=id_drawer_layout
ANDROID_TOOLBAR=id_toolbar
ANDROID_FLOATING_ACTION_BUTTON=xpath_//com.google.android.material.floatingactionbutton.FloatingActionButton
ANDROID_MORE_OPTIONS=contentDescription_More options
```

List of locator:
- id
- accessibilityId
- contentDescription
- text
- containsText
- translationText
- accessibilityId
- name
- label
- value
- labelcontains
- class
- xpath
- viewTag

In case you need general text locator, you can use `constructLocator()` that will provide dynamic arguments to find the element.

Example:

```
GENERAL_TEXT=text_%s
GENERAL_LABEL=label_%s
DYMAMIC_BUTTON_XPATH=xpath_//android.widget.Button[@text='%s']
```

And use it like these:

```
String textToSearch = "Waiting..."
tap().element(constructLocator("GENERAL_TEXT", textToSearch));

String buttonText = "Search"
tap().element(constructLocator("DYMAMIC_BUTTON_XPATH", buttonText));
```

### Register all of Page Object in YourApp.java

Register all of page object you've created into `YourApp.java`

```
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

### Finally, create your test!

Create test file and extends `YourAppFactory`

```
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
        // This method will make automation test fail
        yourApp.homePage().failedMethod();
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

### Implement with another Framework ?

Don't worry and Yes, you can!

You can integrate this library with cucumber, testNG, or anything you want!