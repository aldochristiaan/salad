package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Android application management utilities.
 * Provides methods for installing, uninstalling, and managing Android apps.
 */
public class AppManagement extends UiAutomator2 {

    public AppManagement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Install an application from the specified path
     * @param appPath Path to the APK file
     */
    public void installApp(String appPath) {
        LogUtil.info("Installing app from: " + appPath);
        executeMobileCommand("installApp", mapOf("appPath", appPath));
    }

    /**
     * Uninstall an application by package name
     * @param packageName Package name of the app to uninstall
     */
    public void uninstallApp(String packageName) {
        LogUtil.info("Uninstalling app: " + packageName);
        executeMobileCommand("removeApp", mapOf("appId", packageName));
    }

    /**
     * Clear application data and cache
     * @param packageName Package name of the app to clear
     */
    public void clearApp(String packageName) {
        LogUtil.info("Clearing app data: " + packageName);
        executeMobileCommand("clearApp", mapOf("appId", packageName));
    }

    /**
     * Terminate (force stop) an application
     * @param packageName Package name of the app to terminate
     */
    public void terminateApp(String packageName) {
        LogUtil.info("Terminating app: " + packageName);
        executeMobileCommand("terminateApp", mapOf("appId", packageName));
    }

    /**
     * Activate (bring to foreground) an application
     * @param packageName Package name of the app to activate
     */
    public void activateApp(String packageName) {
        LogUtil.info("Activating app: " + packageName);
        executeMobileCommand("activateApp", mapOf("appId", packageName));
    }

    /**
     * Start an Android activity with full intent
     * @param fullActivityIntent Complete activity intent string
     */
    public void startActivity(String fullActivityIntent) {
        LogUtil.info("Starting activity: " + fullActivityIntent);
        executeMobileCommand("startActivity", mapOf("intent", fullActivityIntent));
    }

    /**
     * Start an Android activity (Espresso-compatible)
     * @param appActivity Activity name to start
     */
    public void startActivityEspresso(String appActivity) {
        LogUtil.info("Starting activity (Espresso): " + appActivity);
        executeMobileCommand("startActivity", mapOf("appActivity", appActivity));
    }

    /**
     * Check if an app is installed
     * @param packageName Package name to check
     * @return true if installed, false otherwise
     */
    public boolean isAppInstalled(String packageName) {
        try {
            Object result = executeMobileCommand("isAppInstalled", mapOf("bundleId", packageName));
            return Boolean.TRUE.equals(result);
        } catch (Exception e) {
            LogUtil.error("Error checking if app is installed: " + packageName, e);
            return false;
        }
    }

    /**
     * Execute a mobile command with arguments
     */
    private Object executeMobileCommand(String command, Map<String, Object> args) {
        return androidDriver.executeScript("mobile:" + command, args);
    }

    /**
     * Create a map with a single key-value pair
     */
    private Map<String, Object> mapOf(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
