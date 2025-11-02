package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;
import java.util.Map;

public class AppManagement extends UiAutomator2 {

    public AppManagement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void installApp(String appPath) {
        executeMobileCommand("installApp", mapOf("appPath", appPath));
    }

    public void uninstallApp(String packageName) {
        executeMobileCommand("removeApp", mapOf("appId", packageName));
    }

    public void clearApp(String packageName) {
        executeMobileCommand("clearApp", mapOf("appId", packageName));
    }

    public void terminateApp(String packageName) {
        executeMobileCommand("terminateApp", mapOf("appId", packageName));
    }

    public void activateApp(String packageName) {
        executeMobileCommand("activateApp", mapOf("appId", packageName));
    }

    public void startActivity(String fullActivityIntent) {
        executeMobileCommand("startActivity", mapOf("intent", fullActivityIntent));
    }

    public void startActivityEspresso(String appActivity) {
        executeMobileCommand("startActivity", mapOf("appActivity", appActivity));
    }

    private void executeMobileCommand(String command, Map<String, Object> args) {
        androidDriver.executeScript("mobile:" + command, args);
    }

    private Map<String, Object> mapOf(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
