package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class Flash extends Espresso {

    public Flash(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, int durationMillis, int repeatCount) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", webElement);
        args.put("durationMillis", durationMillis);
        args.put("repeatCount", repeatCount);
        androidDriver.executeScript("mobile:flashElement", args);
    }
}
