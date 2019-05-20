package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TypeText extends Android {

    public TypeText(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void onElementWithLocator(String elementLocator, String text) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator));
        androidElement.click();
        androidElement.clear();
        androidElement.sendKeys(text);
        hideKeyboard();
    }

    public void onElementWithLocator(String elementLocator, int timeout, String text) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator), timeout);
        androidElement.click();
        androidElement.clear();
        androidElement.sendKeys(text);
        hideKeyboard();
    }

    public void onElementAtIndexWithLocator(String elementLocator, int index, String text) {
        AndroidElement androidElement = findElementsBy(getLocator(elementLocator)).get(index);
        androidElement.click();
        androidElement.clear();
        androidElement.sendKeys(text);
        hideKeyboard();
    }

    public void onElementAtIndexWithLocator(String elementLocator, int timeout, int index, String text) {
        AndroidElement androidElement = (AndroidElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        androidElement.click();
        androidElement.clear();
        androidElement.sendKeys(text);
        hideKeyboard();
    }
}
