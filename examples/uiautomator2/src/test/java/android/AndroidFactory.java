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

    private static Salad salad;
    private static AndroidDriver androidDriver;
    private static AppiumDriverLocalService service;
    protected static Android android;

    // === Lifecycle Hooks ===
    @BeforeAll
    public static void setUp() {
        Properties capabilities = PropertiesLoader.loadCapabilities("capabilities.properties");
        salad = new Salad(
                capabilities,
                "src/test/resources/elements/",
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

    // === App Recovery ===
    public static void resetApp() {
        AppManagement app = new AppManagement(androidDriver);
        app.clearApp("com.example.myapplication");
        app.activateApp("com.example.myapplication");
    }

    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }
        // === Screenshot Utility ===
    public static void takeScreenshot(String name) {
        File src = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(src), dest);
            LogUtil.info("Screenshot taken: " + dest.getAbsolutePath());
        } catch (Exception e) {
            LogUtil.error("Failed to take screenshot", e);
        }
    }
}
