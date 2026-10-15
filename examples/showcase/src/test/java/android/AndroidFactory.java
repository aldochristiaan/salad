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
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

/**
 * Boots a single Appium/Salad session for the whole test class.
 * <p>
 * The active driver is chosen by the {@code driver} system property (set by the Gradle
 * {@code uiautomator2Test} / {@code espressoTest} tasks). It defaults to UIAUTOMATOR2.
 * Capabilities and element locators are loaded from driver-specific files so resource-id
 * resolution matches each driver.
 */
public class AndroidFactory {

    private static final String CAPABILITIES_TEMPLATE = "capabilities-%s.properties";
    private static final String LOCAL_CAPABILITIES_TEMPLATE = "capabilities-%s.local.properties";
    private static final String ELEMENTS_DIR_TEMPLATE = "src/test/resources/elements-%s/";
    private static final String APP_PACKAGE = "id.aldochristiaan.salad.showcase";

    private static Driver driverType;
    private static Salad salad;
    private static AndroidDriver androidDriver;
    protected static Android android;

    @BeforeAll
    public static void setUp() {
        driverType = resolveDriver();
        String slug = slug(driverType);

        Properties capabilities = PropertiesLoader.loadCapabilities(String.format(CAPABILITIES_TEMPLATE, slug));
        applyLocalOverrides(capabilities, String.format(LOCAL_CAPABILITIES_TEMPLATE, slug));
        String elementsDir = String.format(ELEMENTS_DIR_TEMPLATE, slug);

        salad = new Salad(capabilities, elementsDir, driverType, LogLevel.INFO);
        startSession();
    }

    @AfterAll
    public static void tearDown() {
        if (salad != null) {
            salad.stop(driverType);
        }
    }

    public static void startSession() {
        salad.start();
        androidDriver = salad.getAndroidDriver();
        android = new Android(androidDriver, driverType);
    }

    public static AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    public static Driver getDriverType() {
        return driverType;
    }

    /** Re-launch the app to a clean state between tests. */
    public static void resetApp() {
        androidDriver.activateApp(APP_PACKAGE);
    }

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

    private static Driver resolveDriver() {
        String prop = System.getProperty("driver", Driver.UIAUTOMATOR2.name());
        try {
            return Driver.valueOf(prop.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            LogUtil.warn("Unknown driver '" + prop + "', falling back to UIAUTOMATOR2");
            return Driver.UIAUTOMATOR2;
        }
    }

    /**
     * Merge device-specific overrides on top of the committed, CI-generic capabilities.
     * <p>
     * The committed {@code capabilities-<driver>.properties} target the CI emulator. A
     * developer pointing a run at a locally attached device (different udid/device name,
     * extra adb timeouts, ...) drops those keys into an optional, git-ignored
     * {@code capabilities-<driver>.local.properties} which is layered over the base file.
     * CI never sees this file, so it keeps using the emulator defaults.
     */
    private static void applyLocalOverrides(Properties base, String fileName) {
        File local = new File("./" + fileName);
        if (!local.isFile()) {
            return;
        }
        // Colon-safe parser: a plain Properties.load would collapse every
        // "appium:someCap=value" override into a single "appium" key (last line wins).
        Properties overrides = PropertiesLoader.loadCapabilities(local);
        overrides.forEach((key, value) -> base.setProperty(key.toString(), value.toString()));
        LogUtil.info("Applied " + overrides.size() + " local capability override(s) from " + fileName);
    }

    private static String slug(Driver driver) {
        return driver == Driver.ESPRESSO ? "espresso" : "uiautomator2";
    }
}
