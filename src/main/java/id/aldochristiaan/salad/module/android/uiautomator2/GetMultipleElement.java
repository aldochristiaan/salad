package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetMultipleElement extends UiAutomator2 {

    public GetMultipleElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public List<WebElement> withLocator(String elementLocator) {
        return find(elementLocator, null);
    }

    public List<WebElement> withLocator(String elementLocator, int timeout) {
        return find(elementLocator, timeout);
    }

    private List<WebElement> find(String elementLocator, Integer timeout) {
        if (timeout != null) {
            return findElementsBy(getLocator(elementLocator), timeout);
        } else {
            return findElementsBy(getLocator(elementLocator));
        }
    }
}
