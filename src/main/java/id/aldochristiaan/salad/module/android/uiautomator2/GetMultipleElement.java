package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetMultipleElement extends UiAutomator2 {

    public GetMultipleElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public List<AndroidElement> withLocator(String elementLocator) {
        return findElementsBy(getLocator(elementLocator));
    }

    public List<WebElement> withLocator(String elementLocator, int timeout) {
        return findElementsBy(getLocator(elementLocator), timeout);
    }
}
