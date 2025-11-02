package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class Navigate extends Espresso {

    public Navigate(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void toElement(String elementLocator, int menuItemId) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "menuItemId", menuItemId
        );
        androidDriver.executeScript("mobile:navigateTo", args);
    }
}
