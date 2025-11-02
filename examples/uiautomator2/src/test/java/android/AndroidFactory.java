package android;

import id.aldochristiaan.salad.Salad;
import id.aldochristiaan.salad.module.android.AppManagement;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

public class AndroidFactory {

    private static final String ELEMENTS_DIR = "src/test/resources/elements/";
    private static final String CAPABILITIES_FILE = "capabilities.properties";
    private static final String APP_PACKAGE = "com.example.myapplication";

    private static Salad salad;
    private static AndroidDriver androidDriver;
    private static AppiumDriverLocalService service;
    protected static Android android;

    // === Lifecycle Hooks ===
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

    // === Session Management ===
    public static void startSession() {
        salad.start();
        androidDriver = salad.getAndroidDriver();
        android = new Android(androidDriver);
    }

    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    // === App Recovery ===
    public static void resetApp() {
        AppManagement app = new AppManagement(androidDriver);
        app.clearApp(APP_PACKAGE);
        app.activateApp(APP_PACKAGE);
    }

    // === Screenshot Utility ===
    public static void takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshot/" + name + ".png");
            FileUtils.copyFile(Objects.requireNonNull(src), dest);
            LogUtil.info("Screenshot taken: " + dest.getAbsolutePath());
        } catch (Exception e) {
            LogUtil.error("Failed to take screenshot", e);
        }
    }
}
