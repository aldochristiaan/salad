package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;

public class AppManagement extends UiAutomator2 {

    public AppManagement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void installApp(String appPath) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("appPath", appPath);
        androidDriver.executeScript("mobile:installApp", args);
    }

    public void uninstallApp(String packageName) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("appId", packageName);
        androidDriver.executeScript("mobile:removeApp", args);
    }

    public void clearApp(String packageName) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("appId", packageName);
        androidDriver.executeScript("mobile:clearApp", args);
    }

    public void terminateApp(String packageName) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("appId", packageName);
        androidDriver.executeScript("mobile:terminateApp", args);
    }

    public void activateApp(String packageName) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("appId", packageName);
        androidDriver.executeScript("mobile:activateApp", args);
    }

    public void startActivity(String fullActivityIntent) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("intent", fullActivityIntent);
        androidDriver.executeScript("mobile:startActivity", args);
    }

    public void startActivityEspresso(String appActivity) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("appActivity", appActivity);
        androidDriver.executeScript("mobile:startActivity", args);
    }
}
