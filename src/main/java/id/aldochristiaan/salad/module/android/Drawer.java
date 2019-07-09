package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class Drawer extends Android {

    public Drawer(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void open(String elementLocator){
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        androidDriver.executeScript("mobile:openDrawer", args);
    }

    public void close(String elementLocator){
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        androidDriver.executeScript("mobile:closeDrawer", args);
    }
}
