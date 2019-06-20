package id.aldochristiaan.salad;

import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.Platform;
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
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Salad {

    private AndroidDriver<AndroidElement> androidDriver;
    private IOSDriver<IOSElement> iosDriver;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities desiredCapabilities;
    private Platform platform;
    private LogLevel logLevel;
    private Integer appiumPort;
    private String elementPropertiesDirectory;
    private Properties capabilitiesProperties;
    public static Properties ELEMENT_PROPERTIES;
    public static int DEFAULT_TIMEOUT = 60;
    public static int MAX_SWIPE_COUNT = 20;

    public Salad() {
    }

    public Salad(DesiredCapabilities desiredCapabilities, Platform platform, LogLevel logLevel) {
        this.desiredCapabilities = desiredCapabilities;
        this.platform = platform;
        this.logLevel = logLevel;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Platform platform, LogLevel logLevel) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.capabilitiesProperties = capabilitiesProperties;
        this.platform = platform;
        this.logLevel = logLevel;
    }

    public Salad(Properties capabilitiesProperties, String elementPropertiesDirectory, Platform platform, Integer appiumPort, LogLevel logLevel) {
        this.elementPropertiesDirectory = elementPropertiesDirectory;
        this.capabilitiesProperties = capabilitiesProperties;
        this.platform = platform;
        this.appiumPort = appiumPort;
        this.logLevel = logLevel;
    }

    public void start() {
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

        loadElementProperties(elementPropertiesDirectory);
        switch (platform) {
            case ANDROID:
                if (capabilitiesProperties != null) setAndroidCapabilities(capabilitiesProperties);
                androidDriver = new AndroidDriver<>(service.getUrl(), desiredCapabilities);
                break;
            case ESPRESSO:
                if (capabilitiesProperties != null) setEspressoCapabilities(capabilitiesProperties);
                androidDriver = new AndroidDriver<>(service.getUrl(), desiredCapabilities);
                break;
            case IOS:
                if (capabilitiesProperties != null) setIosCapabilities(capabilitiesProperties);
                iosDriver = new IOSDriver<>(service.getUrl(), desiredCapabilities);
                break;
            default:
                LogUtil.error("Platform not found! Choose between ANDROID or IOS");
                throw new NotFoundException();
        }
    }

    public void stop(Platform platform) {
        switch (platform) {
            case ANDROID:
                try {
                    LogUtil.info("Stopping appium server!");
                    androidDriver.quit();
                    service.stop();
                } catch (Exception e) {
                    LogUtil.info("Android driver closed!");
                    e.printStackTrace();
                }
                break;
            case IOS:
                try {
                    LogUtil.info("Stopping appium server!");
                    iosDriver.quit();
                    service.stop();
                } catch (Exception e) {
                    LogUtil.info("Android driver closed!");
                    e.printStackTrace();
                }
                break;
            default:
                LogUtil.error("Platform not found! Choose between ANDROID or IOS");
                throw new NotFoundException();
        }
    }

    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return androidDriver;
    }

    public IOSDriver<IOSElement> getIosDriver() {
        return iosDriver;
    }

    public Boolean isRunning(){
        return service.isRunning();
    }

    private void setAndroidCapabilities(Properties capabilitiesProperties) {
        desiredCapabilities = new DesiredCapabilities();
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
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ESPRESSO);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 240000);

        for (Map.Entry<Object, Object> capability : capabilitiesProperties.entrySet()) {
            desiredCapabilities.setCapability(capability.getKey().toString(), capability.getValue());
        }
    }

    private void setIosCapabilities(Properties capabilitiesProperties) {
        desiredCapabilities = new DesiredCapabilities();
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
}
