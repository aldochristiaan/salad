package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;

public class LongTap extends UiAutomator2 {

    public LongTap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        WebElement webElement = findElementBy(getLocator(elementLocator));
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }

    public void element(String elementLocator, int index) {
        WebElement webElement = findElementsBy(getLocator(elementLocator)).get(index);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }

    public void pendingElement(String elementLocator, int timeout) {
        WebElement webElement = findElementBy(getLocator(elementLocator), timeout);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }

    public void pendingElement(String elementLocator, int timeout, int index) {
        WebElement webElement = findElementsBy(getLocator(elementLocator), timeout).get(index);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }
}
