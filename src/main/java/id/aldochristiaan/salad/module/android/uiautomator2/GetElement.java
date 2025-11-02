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
        return find(elementLocator, null, null);
    }

    public WebElement withLocator(String elementLocator, int timeout) {
        return find(elementLocator, timeout, null);
    }

    public WebElement withLocator(String elementLocator, Direction direction) {
        return find(elementLocator, null, direction);
    }

    private WebElement find(String elementLocator, Integer timeout, Direction direction) {
        if (timeout != null) {
            return findElementBy(getLocator(elementLocator), timeout);
        } else if (direction != null) {
            return findElementBy(getLocator(elementLocator), direction);
        } else {
            return findElementBy(getLocator(elementLocator));
        }
    }
}
