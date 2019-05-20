# Automate your test

### Create Application Factory

First of all, you have to make `YourAppFactory.java` to make it as your automation engine that control how your automation works.

P.S: You can change `<YourApp>` with your application name. Ex: `FacebookFactory.java`

```
public class AndroidFactory {

   private static Salad salad; // Automation Engine
   protected static YourApp yourApp;

   @BeforeAll // Junit5 annotation
   public static void setUp() { // To start appium server and inject elements 
       String elementPropertiesDirectory = "src/test/resources/element/your_app/"; // Element properties file
       String capabilitiesFileName = "your_app.properties";
       // You can choose other constructor to run automation
       salad = new Salad(PropertiesLoader.loadCapabilities(capabilitiesFileName), elementPropertiesDirectory, Platform.ANDROID); // or Platform.IOS
       salad.start();
       yourApp = new YourApp(salad.getAndroidDriver()); // or salad.getIosDriver();
   }

   @AfterAll // J
   public static void tearDown() { 
       salad.stop(Platform.ANDROID); // or Platform.IOS
   }
}

```

### List of modules

You can use below modules provided by salad:
- CheckElement
- GetElement
- GetMultipleElement
- GetTextFromElement
- LongTapElement
- Swipe
- TapElement
- TypeText
- ValidateElement
- ValidateValue
- Randomize
- Toast (Android)
- FakerUtil (Generate fake data)
- LogUtil

Feel free to add more methods if it can be used generally. Just create a PR :)

### Provide Element Properties and Page Object

In general, Page Object Pattern define its element inside the file.

But we will use another approach by using element properties to make it cleaner and reusable.

Example:

Page object : `HomePage.java`

```
public class HomePage extends Ios {

    public HomePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }
    
    public void validateOnHomePage(){
        // Wait for 10 seconds and assert element present
        validateElement.presentByLocator("MARKETPLACE_HOME_HEADER", 10);
    }

    public void checkOnboarding(){
        // Tap element
        tapElement().withLocator("MARKETPLACE_MULAI_TUR");
        tapElement().withLocator("MARKETPLACE_LANJUT");
        tapElement().withLocator("MARKETPLACE_LANJUT");
        tapElement().withLocator("MARKETPLACE_SELESAI");
    }

    public void tapAkunBar(){
        tapElement().withLocator("MARKETPLACE_AKUN_BAR");
    }
}
```

Page properties : `HomePage.properties`

```
MARKETPLACE_MULAI_TUR=label_Mulai Tur
MARKETPLACE_LANJUT=label_Lanjut
MARKETPLACE_SELESAI=label_Selesai
MARKETPLACE_AKUN_BAR=label_Akun
MARKETPLACE_LOGIN_BUTTON=label_Login
MARKETPLACE_CREDENTIAL_FIELD=xpath_//XCUIElementTypeTextField[@name="lineTextField"]
MARKETPLACE_PASSWORD_FIELD=xpath_//XCUIElementTypeSecureTextField[@name="lineTextField"]
MARKETPLACE_LOGIN_LOGIN_BUTTON=xpath_//XCUIElementTypeButton[contains(@label,'Login')]
```

List of locator:
- id
- text
- containsText
- translationText
- accessibilityId
- name
- label
- labelcontains
- class
- xpath

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
tapElement().withLocator(constructLocator("GENERAL_TEXT", textToSearch));

String buttonText = "Search"
tapElement().withLocator(constructLocator("DYMAMIC_BUTTON_XPATH", buttonText));
```

### Register all of Page Object in YourApp.java

Register all of page object you've created into `YourApp.java`

```
public class YourApp {

    private IOSDriver<IOSElement> iosDriver;

    public YourApp(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
    }

    public HomePage homePage() {
        return new HomePage(iosDriver);
    }

    public LoginPage loginPage() {
        return new LoginPage(iosDriver);
    }
}

```

### Finally, create your test!

Create test file and extends `YourAppFactory`

```
public class YourAppTest extends YourAppFactory {

    @Test
    public void loginTest(){
        yourApp.homePage().checkOnboarding();
        yourApp.homePage().tapAkunBar();
        yourApp.loginPage().tapOnLoginButton();
        yourApp.loginPage().inputCredential("credential");
        yourApp.loginPage().inputPassword("password");
    }
}

```

### Implement with another Framework ?

Don't worry and Yes, you can!

You can integrate this library with cucumber, testNG, or anything you want!