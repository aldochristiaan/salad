package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class Type extends XCUITest {

    public Type(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void element(String elementLocator, String text) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator));
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }

    public void element(String elementLocator, int index, String text) {
        IOSElement iosElement = findElementsBy(getLocator(elementLocator)).get(index);
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }

    public void pendingElement(String elementLocator, int timeout, String text) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator), timeout);
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }

    public void pendingElement(String elementLocator, int timeout, int index, String text) {
        IOSElement iosElement = (IOSElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }
}
