package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Action;
import id.aldochristiaan.salad.util.Strategy;
import io.appium.java_client.android.AndroidDriver;

import java.util.Map;

public class UiAutomator extends Espresso {

    public UiAutomator(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public Object sauce(Strategy strategy, String locator, Action action) {
        return executeUiAutomator(strategy, locator, action, null);
    }

    public Object sauce(Strategy strategy, String locator, Action action, Integer index) {
        return executeUiAutomator(strategy, locator, action, index);
    }

    private Object executeUiAutomator(Strategy strategy, String locator, Action action, Integer index) {
        Map<String, Object> args = Map.of(
                "strategy", strategy.toString(),
                "locator", locator,
                "action", action.toString()
        );

        // If index is provided, add it to the map
        if (index != null) {
            args = new java.util.HashMap<>(args); // convert to mutable map
            args.put("index", index);
        }

        return androidDriver.executeScript("mobile:uiautomator", args);
    }
}
