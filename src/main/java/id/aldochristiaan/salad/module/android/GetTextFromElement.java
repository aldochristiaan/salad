package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GetTextFromElement extends Android {

    public GetTextFromElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public String withLocator(String elementLocator) {
        return findElementBy(getLocator(elementLocator)).getText();
    }

    public String withLocator(String elementLocator, int timeout) {
        return findElementBy(getLocator(elementLocator), timeout).getText();
    }

    public String fromLocatorAtIndex(String elementLocator, int index) {
        return findElementsBy(getLocator(elementLocator)).get(index).getText();
    }

    public String fromLocatorAtIndex(String elementLocator, int timeout, int index) {
        return findElementsBy(getLocator(elementLocator), timeout).get(index).getText();
    }
}
