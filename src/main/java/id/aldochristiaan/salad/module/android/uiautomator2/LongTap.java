package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;

public class LongTap extends UiAutomator2 {

    public LongTap(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator));
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(androidElement)).release().perform();
    }

    public void element(String elementLocator, int index) {
        AndroidElement androidElement = findElementsBy(getLocator(elementLocator)).get(index);
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new ElementOption().withElement(androidElement)).release().perform();
    }
}
