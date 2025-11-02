package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class Flash extends Espresso {

    public Flash(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, int durationMillis, int repeatCount) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "durationMillis", durationMillis,
                "repeatCount", repeatCount
        );
        androidDriver.executeScript("mobile:flashElement", args);
    }
}
