package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class Navigate extends Android {

    public Navigate(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void toElement(AndroidElement navigationElement, int menuItemId){
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", navigationElement);
        args.put("menuItemId", menuItemId);
        androidDriver.executeScript("mobile:navigateTo", args);
    }
}
