package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Action;
import id.aldochristiaan.salad.util.Strategy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class UiAutomator extends Espresso {

    public UiAutomator(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public Object sauce(Strategy strategy, String locator, Action action) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("strategy", strategy.toString());
        args.put("locator", locator);
        args.put("action", action.toString());
        return androidDriver.executeScript("mobile:uiautomator", args);
    }

    public Object sauce(Strategy strategy, String locator, Action action, int index) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("strategy", strategy.toString());
        args.put("locator", locator);
        args.put("action", action.toString());
        args.put("index", index);
        return androidDriver.executeScript("mobile:uiautomator", args);
    }
}
