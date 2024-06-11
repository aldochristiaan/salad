package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

public class Type extends XCUITest {

    public Type(IOSDriver iosDriver) {
        super(iosDriver);
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
