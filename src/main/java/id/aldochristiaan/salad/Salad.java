package id.aldochristiaan.salad;

import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Salad {

    private AndroidDriver<AndroidElement> androidDriver;
    private IOSDriver<IOSElement> iosDriver;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private Driver driver;
    private LogLevel logLevel;
    private URL appiumServerURL;
    private Integer appiumPort;
    private String elementPropertiesDirectory;
    public static Properties CAPABILITIES_PROPERTIES;
    public static Properties ELEMENT_PROPERTIES;
    public static int DEFAULT_TIMEOUT = 60;
    public static int MAX_SWIPE_COUNT = 15;

    public Salad() {
    }

    public Salad(DesiredCapabilities desiredCapabilities, Driver driver, LogLevel logLevel) {
        this.desiredCapabilities = desiredCapabilities;
        this.driver = driver;
        this.logLevel = logLevel;
    }

    public Salad(Properties capabilitiesProperies, String elementPropertiesDirectory, Driver driver, LogLevel logLevel) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        this.logLevel = logLevel;
        CAPABILITIES_PROPERTIES = capabilitiesProperies;
    }

    public Salad(Properties capabilitiesProperies, String elementPropertiesDirectory, Driver driver, String customServerURL) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        try {
            this.appiumServerURL = new URL(customServerURL + "/wd/hub");
        } catch (MalformedURLException e) {
            LogUtil.error("There is a problem with server url : " + customServerURL);
            e.printStackTrace();
        }
        CAPABILITIES_PROPERTIES = capabilitiesProperies;
    }

    public Salad(Properties capabilitiesProperies, String elementPropertiesDirectory, Driver driver, Integer appiumPort, LogLevel logLevel) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.driver = driver;
        this.appiumPort = appiumPort;
        this.logLevel = logLevel;
        CAPABILITIES_PROPERTIES = capabilitiesProperies;
    }

    public void start() {
        if (appiumServerURL == null) {
            builder = new AppiumServiceBuilder();
            if (appiumPort == null) {
                builder.usingAnyFreePort();
            } else {
                builder.usingPort(appiumPort);
            }
            builder.withStartUpTimeOut(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, logLevel.toString().toLowerCase());
            service = AppiumDriverLocalService.buildService(builder);
            LogUtil.info("Starting Appium Server!");
            service.start();
            appiumServerURL = service.getUrl();
        } else {
            LogUtil.info("Using custom appium server : " + appiumServerURL.toString());
        }

        loadElementProperties(elementPropertiesDirectory);
        switch (driver) {
            case UIAUTOMATOR2:
                if (CAPABILITIES_PROPERTIES != null) setAndroidCapabilities(CAPABILITIES_PROPERTIES);
                androidDriver = new AndroidDriver<>(appiumServerURL, desiredCapabilities);
                break;
            case ESPRESSO:
                if (CAPABILITIES_PROPERTIES != null) setEspressoCapabilities(CAPABILITIES_PROPERTIES);
                androidDriver = new AndroidDriver<>(appiumServerURL, desiredCapabilities);
                break;
            case XCUITEST:
                if (CAPABILITIES_PROPERTIES != null) setIosCapabilities(CAPABILITIES_PROPERTIES);
                iosDriver = new IOSDriver<>(appiumServerURL, desiredCapabilities);
                break;
            default:
                LogUtil.error("Platform not found! Choose between ANDROID or IOS");
                throw new NotFoundException();
        }
    }

    public void stop(Driver driver) {
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

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return androidDriver;
    }

    public IOSDriver<IOSElement> getIosDriver() {
        return iosDriver;
    }

    public boolean isRunning() {
        return service.isRunning();
    }

    private void setAndroidCapabilities(Properties capabilitiesProperties) {
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 240000);
        desiredCapabilities.setCapability("uiautomator2ServerInstallTimeout", 240000);
        desiredCapabilities.setCapability("uiautomator2ServerLaunchTimeout", 240000);

        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            desiredCapabilities.setCapability(capability.getKey().toString(), capability.getValue());
        }
    }

    private void setEspressoCapabilities(Properties capabilitiesProperties) {
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 240000);

        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            desiredCapabilities.setCapability(capability.getKey().toString(), capability.getValue());
        }
    }

    private void setIosCapabilities(Properties capabilitiesProperties) {
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        desiredCapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
        desiredCapabilities.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, 60);
        desiredCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
        desiredCapabilities.setCapability("useJSONSource", true);
        desiredCapabilities.setCapability("sendKeyStrategy", "setValue");

        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            desiredCapabilities.setCapability(capability.getKey().toString(), capability.getValue());
        }
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

    public void setCapability(String capabilityName, String value) {
        desiredCapabilities.setCapability(capabilityName, value);
    }
}
