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
import io.appium.java_client.remote.options.BaseOptions;
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
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


public class Salad {

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
    private String appiumLogFilePath = System.getProperty("user.dir") + "/target/appium.log";
    public static Properties CAPABILITIES_PROPERTIES;
    public static Properties ELEMENT_PROPERTIES;
    public static int DEFAULT_TIMEOUT = 60;
    public static int MAX_SWIPE_COUNT = 15;

    public Salad() {
    }

    public Salad(Capabilities capabilities, Driver driver, LogLevel logLevel) {
        this.capabilities = capabilities;
        this.driver = driver;
        this.logLevel = logLevel;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, LogLevel logLevel) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        this.logLevel = logLevel;
        CAPABILITIES_PROPERTIES = capabilitiesProperties;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, String customServerURL) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        try {
            this.appiumServerURL = new URL(customServerURL + "/wd/hub");
        } catch (MalformedURLException e) {
            LogUtil.error("There is a problem with server url : " + customServerURL);
            e.printStackTrace();
        }
        CAPABILITIES_PROPERTIES = capabilitiesProperties;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Driver driver, Integer appiumPort, LogLevel logLevel) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        this.appiumPort = appiumPort;
        this.logLevel = logLevel;
        CAPABILITIES_PROPERTIES = capabilitiesProperties;
    }

    public void start() {
        try {
            File appiumLogFile = new File(appiumLogFilePath);
            if (appiumServerURL == null) {
                builder = new AppiumServiceBuilder();
                if (appiumPort == null) {
                    builder.usingAnyFreePort();
                } else {
                    builder.usingPort(appiumPort);
                }
                builder.withArgument(GeneralServerFlag.LOG_LEVEL, logLevel.toString().toLowerCase());
                builder.withLogFile(appiumLogFile);
                service = AppiumDriverLocalService.buildService(builder);
                LogUtil.info("Starting Appium Server!");
                service.start();
                appiumServerURL = service.getUrl();
            } else {
                LogUtil.info("Using custom appium server : " + appiumServerURL);
            }

            loadElementProperties(elementPropertiesDirectory);
            switch (driver) {
                case UIAUTOMATOR2:
                    if (CAPABILITIES_PROPERTIES != null) setAndroidCapabilities(CAPABILITIES_PROPERTIES);
                    androidDriver = new AndroidDriver(appiumServerURL, capabilities);
                    break;
                case ESPRESSO:
                    if (CAPABILITIES_PROPERTIES != null) setEspressoCapabilities(CAPABILITIES_PROPERTIES);
                    androidDriver = new AndroidDriver(appiumServerURL, capabilities);
                    break;
                case XCUITEST:
                    if (CAPABILITIES_PROPERTIES != null) setIosCapabilities(CAPABILITIES_PROPERTIES);
                    iosDriver = new IOSDriver(appiumServerURL, capabilities);
                    break;
                default:
                    LogUtil.error("Platform not found! Choose between ANDROID or IOS");
                    throw new NotFoundException();
            }
        } catch (Exception e) {
            forceStop();
        }
    }

    public void stop(Driver driver) {
        appiumServerURL = null;
        switch (driver) {
            case UIAUTOMATOR2:
            case ESPRESSO:
                try {
                    LogUtil.info("Stopping appium server!");
                    androidDriver.quit();
                    service.stop();
                } catch (Exception e) {
                    LogUtil.info("Android driver closed!");
                }
                break;
            case XCUITEST:
                try {
                    LogUtil.info("Stopping appium server!");
                    iosDriver.quit();
                    service.stop();
                } catch (Exception e) {
                    LogUtil.info("Android driver closed!");
                }
                break;
            default:
                LogUtil.error("Platform not found! Choose between UIAUTOMATOR2, ESPRESSO or XCUITEST");
                throw new NotFoundException();
        }
    }

    private void forceStop() {
        try {
            androidDriver.quit();
        } catch (Exception e) {
            System.out.println("Couldn't force quit android driver!");
        }

        try {
            iosDriver.quit();
        } catch (Exception e) {
            System.out.println("Couldn't force quit ios driver!");
        }

        try {
            service.stop();
        } catch (Exception e) {
            System.out.println("There is something wrong with appium server!");
        }
    }

    public AndroidDriver getAndroidDriver() {
        return androidDriver;
    }

    public IOSDriver getIosDriver() {
        return iosDriver;
    }

    public boolean isRunning() {
        return service.isRunning();
    }

    private void setAndroidCapabilities(Properties capabilitiesProperties) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability(UiAutomator2Options.AUTOMATION_NAME_OPTION, AutomationName.ANDROID_UIAUTOMATOR2);
        options.setCapability(UiAutomator2Options.AUTO_GRANT_PERMISSIONS_OPTION, true);
        options.setCapability(BaseOptions.NEW_COMMAND_TIMEOUT_OPTION, 30);
        options.setCapability("uiautomator2ServerInstallTimeout", 120000);
        options.setCapability("uiautomator2ServerLaunchTimeout", 120000);
        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            options.setCapability(capability.getKey().toString(), capability.getValue());
        }
        capabilities = options;
    }

    private void setEspressoCapabilities(Properties capabilitiesProperties) {
        EspressoOptions options = new EspressoOptions();
        options.setCapability(EspressoOptions.AUTOMATION_NAME_OPTION, AutomationName.ESPRESSO);
        options.setCapability(EspressoOptions.AUTO_GRANT_PERMISSIONS_OPTION, true);
        options.setCapability(BaseOptions.NEW_COMMAND_TIMEOUT_OPTION, 30);

        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            options.setCapability(capability.getKey().toString(), capability.getValue());
        }
        capabilities = options;
    }

    private void setIosCapabilities(Properties capabilitiesProperties) {
        XCUITestOptions options = new XCUITestOptions();
        options.setCapability(XCUITestOptions.AUTOMATION_NAME_OPTION, AutomationName.IOS_XCUI_TEST);
        options.setCapability(XCUITestOptions.USE_NEW_WDA_OPTION, true);
        options.setCapability("sendKeyStrategy", "setValue");

        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            options.setCapability(capability.getKey().toString(), capability.getValue());
        }
        capabilities = options;
    }

    private void loadElementProperties(String directory) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        ELEMENT_PROPERTIES = new Properties();

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].toString().contains(".properties")) {
                try {
                    ELEMENT_PROPERTIES.load(new FileInputStream(directory + listOfFiles[i].getName()));
                } catch (IOException e) {
                    LogUtil.error("Failed to load properties : " + listOfFiles[i].getName(), e);
                }
            }
        }
    }

}
