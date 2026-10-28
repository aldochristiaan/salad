package ios;

import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import java.util.Locale;

import id.aldochristiaan.salad.Salad;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

/**
 * Boots a single XCUITest session for the whole test class.
 *
 * <p>Requires a macOS runner with an iOS simulator and the {@code xcuitest} Appium driver
 * installed. The app path and simulator device come from {@code capabilities-ios.properties}.
 */
public class IosFactory {

    private static final String CAPABILITIES_FILE = "capabilities-ios.properties";
    private static final String ELEMENTS_DIR = "src/test/resources/elements-ios/";

    private static Salad salad;
    private static IOSDriver iosDriver;
    protected static Ios ios;

    @BeforeAll
    public static void setUp() {
        Properties capabilities = PropertiesLoader.loadCapabilities(CAPABILITIES_FILE);
        salad = new Salad(capabilities, ELEMENTS_DIR, Driver.XCUITEST, appiumLogLevel());
        salad.start();
        iosDriver = salad.getIosDriver();
        ios = new Ios(iosDriver);
    }

    @AfterAll
    public static void tearDown() {
        if (salad != null) {
            salad.stop(Driver.XCUITEST);
        }
    }

    public static IOSDriver getIosDriver() {
        return iosDriver;
    }


    private static LogLevel appiumLogLevel() {
        String env = System.getenv("SALAD_APPIUM_LOG_LEVEL");
        if (env == null || env.isBlank()) return LogLevel.INFO;
        try { return LogLevel.valueOf(env.trim().toUpperCase(Locale.ROOT)); }
        catch (IllegalArgumentException e) { LogUtil.warn("Unknown SALAD_APPIUM_LOG_LEVEL '" + env + "', falling back to INFO"); return LogLevel.INFO; }
    }

    public static void takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshot/" + name + ".png");
            FileUtils.copyFile(Objects.requireNonNull(src), dest);
        } catch (Exception e) {
            // Screenshot is best-effort; never fail the run over it
        }
    }
}
