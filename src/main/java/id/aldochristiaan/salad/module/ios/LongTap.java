package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;

public class LongTap extends XCUITest {

    public LongTap(IOSDriver iosDriver) {
        super(iosDriver);
    }

    public void element(String elementLocator) {
        WebElement webElement = findElementBy(getLocator(elementLocator));
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }

    public void element(String elementLocator, int index) {
        WebElement webElement = findElementsBy(getLocator(elementLocator)).get(index);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }

    public void pendingElement(String elementLocator, int timeout) {
        WebElement webElement = findElementBy(getLocator(elementLocator), timeout);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }

    public void pendingElement(String elementLocator, int timeout, int index) {
        WebElement webElement = findElementsBy(getLocator(elementLocator), timeout).get(index);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(new ElementOption().withElement(webElement)).release().perform();
    }
}
