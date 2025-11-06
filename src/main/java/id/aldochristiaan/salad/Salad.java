package id.aldochristiaan.salad;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.EspressoOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NotFoundException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class Salad {

    // Static configuration - Deprecated, use PropertiesLoader instead
    @Deprecated
    public static Properties CAPABILITIES_PROPERTIES;
    @Deprecated
    public static Properties ELEMENT_PROPERTIES;

    // Instance variables
    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;
    private AppiumDriverLocalService service;
    private Capabilities capabilities;
    private final Driver driver;
    private final LogLevel logLevel;
    private URL appiumServerURL;
    private final Integer appiumPort;
    private final PropertiesLoader propertiesLoader;

    // Constructors
    public Salad(Capabilities capabilities, Driver driver, LogLevel logLevel) {
        this.capabilities = capabilities;
        this.driver = driver;
        this.logLevel = logLevel;
        this.appiumPort = null;
        this.propertiesLoader = PropertiesLoader.getInstance();
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, LogLevel logLevel) {
        this.driver = driver;
        this.logLevel = logLevel;
        this.appiumPort = null;
        this.propertiesLoader = PropertiesLoader.getInstance();
        this.propertiesLoader.loadFromProperties(capabilitiesProperties);
        this.propertiesLoader.loadFromDirectory(elementPropertiesDirectory);

        // Backward compatibility
        CAPABILITIES_PROPERTIES = capabilitiesProperties;
        ELEMENT_PROPERTIES = this.propertiesLoader.getProperties();
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, String customServerURL) {
        this(capabilitiesProperties, elementPropertiesDirectory, driver, LogLevel.INFO);
        try {
            this.appiumServerURL = new URL(customServerURL + SaladConfig.APPIUM_HUB_PATH);
        } catch (MalformedURLException e) {
            LogUtil.error("Invalid server URL: " + customServerURL, e);
            throw new IllegalArgumentException("Invalid server URL", e);
        }
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, Integer appiumPort, LogLevel logLevel) {
        this.driver = driver;
        this.logLevel = logLevel;
        this.appiumPort = appiumPort;
        this.propertiesLoader = PropertiesLoader.getInstance();
        this.propertiesLoader.loadFromProperties(capabilitiesProperties);
        this.propertiesLoader.loadFromDirectory(elementPropertiesDirectory);

        // Backward compatibility
        CAPABILITIES_PROPERTIES = capabilitiesProperties;
        ELEMENT_PROPERTIES = this.propertiesLoader.getProperties();
    }

    // Start Appium server and driver
    public void start() {
        try {
            setupAppiumService();

            Properties caps = propertiesLoader.getProperties();
            if (caps.isEmpty() && CAPABILITIES_PROPERTIES != null) {
                caps = CAPABILITIES_PROPERTIES; // Backward compatibility
            }

            if (!caps.isEmpty()) {
                initializeDriver(caps);
            } else {
                throw new IllegalStateException("No capabilities configured");
            }
        } catch (Exception e) {
            LogUtil.error("Failed to start Salad session", e);
            forceStop();
            throw new RuntimeException("Failed to start Salad session", e);
        }
    }

    private void initializeDriver(Properties caps) {
        switch (driver) {
            case UIAUTOMATOR2 -> {
                setAndroidCapabilities(caps);
                androidDriver = new AndroidDriver(appiumServerURL, capabilities);
                LogUtil.info("UiAutomator2 driver started successfully");
            }
            case ESPRESSO -> {
                setEspressoCapabilities(caps);
                androidDriver = new AndroidDriver(appiumServerURL, capabilities);
                LogUtil.info("Espresso driver started successfully");
            }
            case XCUITEST -> {
                setIosCapabilities(caps);
                iosDriver = new IOSDriver(appiumServerURL, capabilities);
                LogUtil.info("XCUITest driver started successfully");
            }
            default -> throw new NotFoundException("Unsupported driver type: " + driver);
        }
    }

    // Stop Appium server and driver
    public void stop() {
        stop(this.driver);
    }

    public void stop(Driver driverType) {
        LogUtil.info("Stopping Salad session for driver: " + driverType);
        try {
            switch (driverType) {
                case UIAUTOMATOR2, ESPRESSO -> stopAndroidDriver();
                case XCUITEST -> stopIOSDriver();
                default -> throw new NotFoundException("Unsupported driver type: " + driverType);
            }
        } catch (Exception e) {
            LogUtil.error("Error during driver shutdown: " + e.getMessage(), e);
        } finally {
            stopService();
            appiumServerURL = null;
        }
    }

    private void stopAndroidDriver() {
        if (androidDriver != null) {
            try {
                androidDriver.quit();
                LogUtil.info("Android driver stopped successfully");
            } catch (Exception e) {
                LogUtil.error("Error quitting Android driver", e);
            }
        }
    }

    private void stopIOSDriver() {
        if (iosDriver != null) {
            try {
                iosDriver.quit();
                LogUtil.info("iOS driver stopped successfully");
            } catch (Exception e) {
                LogUtil.error("Error quitting iOS driver", e);
            }
        }
    }

    private void stopService() {
        if (service != null && service.isRunning()) {
            try {
                service.stop();
                LogUtil.info("Appium service stopped successfully");
            } catch (Exception e) {
                LogUtil.error("Error stopping Appium service", e);
            }
        }
    }

    // Force stop drivers and service
    private void forceStop() {
        LogUtil.warn("Forcing cleanup of resources");
        stopAndroidDriver();
        stopIOSDriver();
        stopService();
    }

    // Appium service setup
    private void setupAppiumService() {
        if (appiumServerURL != null) {
            LogUtil.info("Using custom Appium server: " + appiumServerURL);
            return;
        }

        File appiumLogFile = new File(SaladConfig.APPIUM_LOG_FILE_PATH);
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.LOG_LEVEL, logLevel.toString().toLowerCase())
                .withLogFile(appiumLogFile);

        if (appiumPort != null) {
            builder.usingPort(appiumPort);
            LogUtil.info("Configuring Appium to use port: " + appiumPort);
        } else {
            builder.usingAnyFreePort();
            LogUtil.info("Configuring Appium to use any free port");
        }

        service = AppiumDriverLocalService.buildService(builder);
        LogUtil.info("Starting Appium Server...");
        service.start();
        appiumServerURL = service.getUrl();
        LogUtil.info("Appium Server started at: " + appiumServerURL);
    }

    // Capability setup methods
    private void setAndroidCapabilities(Properties props) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(SaladConfig.toDuration(SaladConfig.DEFAULT_TIMEOUT))
                .setUiautomator2ServerInstallTimeout(SaladConfig.toDuration(SaladConfig.DURATION_TIMEOUT))
                .setUiautomator2ServerLaunchTimeout(SaladConfig.toDuration(SaladConfig.DURATION_TIMEOUT));

        props.forEach((key, value) -> options.setCapability(key.toString(), value));
        capabilities = options;
        LogUtil.info("Android UiAutomator2 capabilities configured");
    }

    private void setEspressoCapabilities(Properties props) {
        EspressoOptions options = new EspressoOptions()
                .setAutomationName(AutomationName.ESPRESSO)
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(SaladConfig.toDuration(SaladConfig.DEFAULT_TIMEOUT));

        props.forEach((key, value) -> options.setCapability(key.toString(), value));
        capabilities = options;
        LogUtil.info("Android Espresso capabilities configured");
    }

    private void setIosCapabilities(Properties props) {
        XCUITestOptions options = new XCUITestOptions()
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setUseNewWDA(true);

        props.forEach((key, value) -> options.setCapability(key.toString(), value));
        capabilities = options;
        LogUtil.info("iOS XCUITest capabilities configured");
    }

    // Getters
    public AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    public IOSDriver getIosDriver() {
        return iosDriver;
    }

    public boolean isRunning() {
        return service != null && service.isRunning();
    }
}
