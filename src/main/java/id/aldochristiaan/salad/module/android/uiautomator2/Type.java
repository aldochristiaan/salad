package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Type extends UiAutomator2 {

    public Type(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String text) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator));
        androidElement.click();
        androidElement.clear();
        androidElement.sendKeys(text);
        hideKeyboard();
    }

    public void element(String elementLocator, int index, String text) {
        AndroidElement androidElement = findElementsBy(getLocator(elementLocator)).get(index);
        androidElement.click();
        androidElement.clear();
        androidElement.sendKeys(text);
        hideKeyboard();
    }
}
