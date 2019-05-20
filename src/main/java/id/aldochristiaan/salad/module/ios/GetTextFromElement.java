package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GetTextFromElement extends Ios {

    public GetTextFromElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
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
