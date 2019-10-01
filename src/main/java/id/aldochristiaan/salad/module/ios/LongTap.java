package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.ElementOption;

public class LongTap extends XCUITest {

    public LongTap(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void element(String elementLocator) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator));
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(iosElement)).release().perform();
    }

    public void element(String elementLocator, int index) {
        IOSElement iosElement = findElementsBy(getLocator(elementLocator)).get(index);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(iosElement)).release().perform();
    }
}
