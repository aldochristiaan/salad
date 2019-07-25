package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class Navigate extends Android {

    public Navigate(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void toElement(String elementLocator, int menuItemId) {
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("menuItemId", menuItemId);
        androidDriver.executeScript("mobile:navigateTo", args);
    }
}
