package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class GetElement extends UiAutomator2 {

    public GetElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public WebElement withLocator(String elementLocator) {
        return findElementBy(getLocator(elementLocator));
    }

    public WebElement withLocator(String elementLocator, int timeout) {
        return findElementBy(getLocator(elementLocator), timeout);
    }

    public WebElement withLocator(String elementLocator, Direction direction) {
        return findElementBy(getLocator(elementLocator), direction);
    }
}
