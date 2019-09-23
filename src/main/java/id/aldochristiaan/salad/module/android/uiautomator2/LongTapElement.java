package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;

public class LongTapElement extends UiAutomator2 {

    public LongTapElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void withLocator(String elementLocator) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator));
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(androidElement)).release().perform();
    }

    public void withLocator(String elementLocator, int timeout) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator), timeout);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(androidElement)).release().perform();
    }

    public void withLocatorAtIndex(String elementLocator, int index) {
        AndroidElement androidElement = findElementsBy(getLocator(elementLocator)).get(index);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(androidElement)).release().perform();
    }

    public void withLocatorAtIndex(String elementLocator, int timeout, int index) {
        AndroidElement androidElement = (AndroidElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(androidElement)).release().perform();
    }
}
