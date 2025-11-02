package id.aldochristiaan.salad;

import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class Salad {

    // Static configuration
    public static Properties CAPABILITIES_PROPERTIES;
    public static Properties ELEMENT_PROPERTIES;
    public static final int DEFAULT_TIMEOUT = 30;
    public static final int DURATION_TIMEOUT = 60;
    public static final int MAX_SWIPE_COUNT = 15;

    // Instance variables
    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private Capabilities capabilities;
    private Driver driver;
    private LogLevel logLevel;
    private URL appiumServerURL;
    private Integer appiumPort;
    private String elementPropertiesDirectory;
    private final String appiumLogFilePath = System.getProperty("user.dir") + "/target/appium.log";

    // Constructors
    public Salad() {
    }

    public Salad(Capabilities capabilities, Driver driver, LogLevel logLevel) {
        this.capabilities = capabilities;
        this.driver = driver;
        this.logLevel = logLevel;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, LogLevel logLevel) {
        this(capabilitiesProperties, elementPropertiesDirectory, driver);
        this.logLevel = logLevel;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, String customServerURL) {
        this(capabilitiesProperties, elementPropertiesDirectory, driver);
        try {
            this.appiumServerURL = new URL(customServerURL + "/wd/hub");
        } catch (MalformedURLException e) {
            LogUtil.error("Invalid server URL: " + customServerURL, e);
        }
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, Integer appiumPort, LogLevel logLevel) {
        this(capabilitiesProperties, elementPropertiesDirectory, driver);
        this.appiumPort = appiumPort;
        this.logLevel = logLevel;
    }

    private Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        CAPABILITIES_PROPERTIES = capabilitiesProperties;
    }

    // Start Appium server and driver
    public void start() {
        try {
            setupAppiumService();
            loadElementProperties(elementPropertiesDirectory);

            if (CAPABILITIES_PROPERTIES != null) {
                switch (driver) {
                    case UIAUTOMATOR2 -> {
                        setAndroidCapabilities(CAPABILITIES_PROPERTIES);
                        androidDriver = new AndroidDriver(appiumServerURL, capabilities);
                    }
                    case ESPRESSO -> {
                        setEspressoCapabilities(CAPABILITIES_PROPERTIES);
                        androidDriver = new AndroidDriver(appiumServerURL, capabilities);
                    }
                    case XCUITEST -> {
                        setIosCapabilities(CAPABILITIES_PROPERTIES);
                        iosDriver = new IOSDriver(appiumServerURL, capabilities);
                    }
                    default -> throw new NotFoundException("Platform not found! Choose between ANDROID or IOS");
                }
            }
        } catch (Exception e) {
            forceStop();
        }
    }

    // Stop Appium server and driver
    public void stop(Driver driver) {
        appiumServerURL = null;
        try {
            switch (driver) {
                case UIAUTOMATOR2, ESPRESSO -> {
                    LogUtil.info("Stopping Appium server...");
                    androidDriver.quit();
                    service.stop();
                }
                case XCUITEST -> {
                    LogUtil.info("Stopping Appium server...");
                    iosDriver.quit();
                    service.stop();
                }
                default ->
                        throw new NotFoundException("Platform not found! Choose between UIAUTOMATOR2, ESPRESSO or XCUITEST");
            }
        } catch (Exception e) {
            LogUtil.info("Driver closed with exception: " + e.getMessage());
        }
    }

    // Force stop drivers and service
    private void forceStop() {
        try {
            if (androidDriver != null) androidDriver.quit();
        } catch (Exception e) {
            System.out.println("Couldn't force quit Android driver!");
        }

        try {
            if (iosDriver != null) iosDriver.quit();
        } catch (Exception e) {
            System.out.println("Couldn't force quit iOS driver!");
        }

        try {
            if (service != null) service.stop();
        } catch (Exception e) {
            System.out.println("Error stopping Appium server!");
        }
    }

    // Appium service setup
    private void setupAppiumService() {
        File appiumLogFile = new File(appiumLogFilePath);
        if (appiumServerURL == null) {
            builder = new AppiumServiceBuilder()
                    .withArgument(GeneralServerFlag.LOG_LEVEL, logLevel.toString().toLowerCase())
                    .withLogFile(appiumLogFile);

            if (appiumPort == null) {
                builder.usingAnyFreePort();
            } else {
                builder.usingPort(appiumPort);
            }

            service = AppiumDriverLocalService.buildService(builder);
            LogUtil.info("Starting Appium Server...");
            service.start();
            appiumServerURL = service.getUrl();
        } else {
            LogUtil.info("Using custom Appium server: " + appiumServerURL);
        }
    }

    // Capability setup methods
    private void setAndroidCapabilities(Properties props) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(DURATION_TIMEOUT))
                .setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(DURATION_TIMEOUT));

        props.forEach((key, value) -> options.setCapability(key.toString(), value));
        capabilities = options;
    }

    private void setEspressoCapabilities(Properties props) {
        EspressoOptions options = new EspressoOptions()
                .setAutomationName(AutomationName.ESPRESSO)
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT));

        props.forEach((key, value) -> options.setCapability(key.toString(), value));
        capabilities = options;
    }

    private void setIosCapabilities(Properties props) {
        XCUITestOptions options = new XCUITestOptions()
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setUseNewWDA(true);

        props.forEach((key, value) -> options.setCapability(key.toString(), value));
        capabilities = options;
    }

    // Load element properties from directory
    private void loadElementProperties(String directory) {
        File[] files = new File(directory).listFiles();
        ELEMENT_PROPERTIES = new Properties();

        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile() && file.getName().endsWith(".properties")) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ELEMENT_PROPERTIES.load(fis);
                } catch (IOException e) {
                    LogUtil.error("Failed to load properties: " + file.getName(), e);
                }
            }
        }
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
