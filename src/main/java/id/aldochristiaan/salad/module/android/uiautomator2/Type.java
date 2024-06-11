package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class Type extends UiAutomator2 {

    public Type(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String text) {
        WebElement webElement = findElementBy(getLocator(elementLocator));
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        hideKeyboard();
    }

    public void element(String elementLocator, int index, String text) {
        WebElement webElement = findElementsBy(getLocator(elementLocator)).get(index);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        hideKeyboard();
    }

    public void pendingElement(String elementLocator, int timeout, String text) {
        WebElement webElement = findElementBy(getLocator(elementLocator), timeout);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        hideKeyboard();
    }

    public void pendingElement(String elementLocator, int timeout, int index, String text) {
        WebElement webElement = (WebElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        hideKeyboard();
    }
}
