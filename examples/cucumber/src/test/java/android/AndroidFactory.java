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

    private static Salad salad;
    private static AndroidDriver androidDriver;
    private static AppManagement appManagement;
    public static Android android;

    @BeforeAll
    public static void setUp() {
        String elementPropertiesDirectory = "src/test/resources/elements/";
        String capabilitiesFileName = "capabilities.properties";
        Properties capabilitiesProperties = PropertiesLoader.loadCapabilities(capabilitiesFileName);
        salad = new Salad(
                capabilitiesProperties,
                elementPropertiesDirectory,
                Driver.UIAUTOMATOR2,
                LogLevel.ERROR
        );
        initSession();
    }

    @AfterAll
    public static void tearDown() {
        salad.stop(Driver.UIAUTOMATOR2);
    }

    @Before
    public static void beforeScenario() {
        appManagement.activateApp("com.example.myapplication");
    }

    @After
    public static void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario.getName());
        }
        appManagement.clearApp("com.example.myapplication");
    }

    public static void initSession() {
        salad.start();
        androidDriver = salad.getAndroidDriver();
        appManagement = new AppManagement(androidDriver);
        android = new Android(androidDriver);
    }

    public static void takeScreenshot(String name) {
        File srcFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        File imageFile = new File("screenshot/" + name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(srcFile), imageFile);
            LogUtil.info("Screenshot taken");
        } catch (Exception e) {
            LogUtil.error("Exception while taking screenshot", e);
        }
    }
}
