package android;

import id.aldochristiaan.salad.Salad;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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
    private static AndroidDriver<AndroidElement> androidDriver;
    protected static Android android;

    @BeforeAll
    public static void setUp() {
        String elementPropertiesDirectory = "src/test/resources/elements/";
        String capabilitiesFileName = "capabilities.properties";
        Properties capabilitiesProperties = PropertiesLoader.loadCapabilities(capabilitiesFileName);
        salad = new Salad(
                capabilitiesProperties,
                elementPropertiesDirectory,
                Driver.UIAUTOMATOR2,
                LogLevel.DEBUG
        );
        salad.start();
        androidDriver = salad.getAndroidDriver();
        android = new Android(androidDriver);
    }

    @AfterAll
    public static void tearDown() {
        salad.stop(Driver.ESPRESSO);
    }

    /**
     * If test failed, it will automatically take screen shot and reset app state
     * You can modify it too to match your usage
     *
     * @see TestListener
     */
    public static void resetApp() {
        androidDriver.resetApp();
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
