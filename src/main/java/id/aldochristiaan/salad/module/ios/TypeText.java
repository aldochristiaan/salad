package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TypeText extends Ios {

    public TypeText(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void onElementWithLocator(String elementLocator, String text) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator));
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }

    public void onElementWithLocator(String elementLocator, int timeout, String text) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator), timeout);
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }

    public void onElementAtIndexWithLocator(String elementLocator, int index, String text) {
        IOSElement iosElement = findElementsBy(getLocator(elementLocator)).get(index);
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }

    public void onElementAtIndexWithLocator(String elementLocator, int timeout, int index, String text) {
        IOSElement iosElement = (IOSElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
        hideKeyboard();
    }
}
