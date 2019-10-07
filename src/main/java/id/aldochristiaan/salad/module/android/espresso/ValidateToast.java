package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class ValidateToast extends Espresso {

    public ValidateToast(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void exist(String text, boolean isRegexp) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("text", text);
        if (isRegexp) args.put("isRegexp", true);
        androidDriver.executeScript("mobile:isToastVisible", args);
    }
}
