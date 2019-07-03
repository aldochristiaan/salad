package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class Flash extends Android {

    public Flash(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void element(AndroidElement androidElement, int durationMillis, int repeatCount) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("durationMillis", durationMillis);
        args.put("repeatCount", repeatCount);
        androidDriver.executeScript("mobile:flashElement", args);
    }
}
