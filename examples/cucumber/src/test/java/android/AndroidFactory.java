package android;

import id.aldochristiaan.salad.Salad;
import id.aldochristiaan.salad.module.android.AppManagement;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

public class AndroidFactory {

    private static final String APP_PACKAGE = "com.example.myapplication";
    private static final String ELEMENTS_DIR = "src/test/resources/elements/";
    private static final String CAPABILITIES_FILE = "capabilities.properties";

    private static Salad salad;
    private static AndroidDriver androidDriver;
    private static AppManagement appManager;
    public static Android android;

    @BeforeAll
    public static void setUp() {
        Properties capabilities = PropertiesLoader.loadCapabilities(CAPABILITIES_FILE);
        salad = new Salad(
                capabilities,
                ELEMENTS_DIR,
                Driver.UIAUTOMATOR2,
                LogLevel.ERROR
        );
        startSession();
    }

    @AfterAll
    public static void tearDown() {
        salad.stop(Driver.UIAUTOMATOR2);
    }

    @Before
    public static void beforeScenario() {
        appManager.activateApp(APP_PACKAGE);
    }

    @After
    public static void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario.getName());
        }
        appManager.clearApp(APP_PACKAGE);
    }

    private static void startSession() {
        salad.start();
        androidDriver = salad.getAndroidDriver();
        appManager = new AppManagement(androidDriver);
        android = new Android(androidDriver);
    }

    private static void captureScreenshot(String scenarioName) {
        try {
            File src = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshot/" + scenarioName + ".png");
            FileUtils.copyFile(Objects.requireNonNull(src), dest);
            LogUtil.info("Screenshot saved: " + dest.getPath());
        } catch (Exception e) {
            LogUtil.error("Failed to capture screenshot", e);
        }
    }
}
