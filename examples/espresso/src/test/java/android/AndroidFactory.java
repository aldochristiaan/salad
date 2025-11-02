package android;

import id.aldochristiaan.salad.Salad;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
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
    protected static Android android;

    @BeforeAll
    public static void setUp() {
        Properties capabilities = PropertiesLoader.loadCapabilities(CAPABILITIES_FILE);
        salad = new Salad(
                capabilities,
                ELEMENTS_DIR,
                Driver.ESPRESSO,
                LogLevel.ERROR
        );
        initSession();
    }

    @AfterAll
    public static void tearDown() {
        salad.stop(Driver.ESPRESSO);
    }

    public static void initSession() {
        salad.start();
        androidDriver = salad.getAndroidDriver();
        android = new Android(androidDriver);
    }

    /**
     * Resets the app by uninstalling and restarting the session.
     * Automatically triggered after test failure.
     *
     * @see TestListener
     */
    public static void resetApp() {
        androidDriver.removeApp(APP_PACKAGE);
        salad.stop(Driver.ESPRESSO);
        initSession();
    }

    public static void takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshot/" + name + ".png");
            FileUtils.copyFile(Objects.requireNonNull(src), dest);
            LogUtil.info("Screenshot taken: " + dest.getPath());
        } catch (Exception e) {
            LogUtil.error("Exception while taking screenshot", e);
        }
    }
}
