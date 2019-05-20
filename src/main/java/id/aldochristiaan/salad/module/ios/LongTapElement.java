package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.ElementOption;

public class LongTapElement extends Ios {

    public LongTapElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void withLocator(String elementLocator) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator));
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(iosElement)).release().perform();
    }

    public void withLocator(String elementLocator, int timeout) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator), timeout);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(iosElement)).release().perform();
    }

    public void withLocatorAtIndex(String elementLocator, int index) {
        IOSElement iosElement = findElementsBy(getLocator(elementLocator)).get(index);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(iosElement)).release().perform();
    }

    public void withLocatorAtIndex(String elementLocator, int timeout, int index) {
        IOSElement iosElement = (IOSElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(iosElement)).release().perform();
    }
}
