package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class TapElement extends Ios {

    public TapElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void withLocator(String elementLocator) {
        findElementBy(getLocator(elementLocator)).click();
    }

    public void withLocator(String elementLocator, int timeout) {
        findElementBy(getLocator(elementLocator), timeout).click();
    }

    public void withLocatorMultipleTimes(String elementLocator, int count) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator));
        if (iosElement != null) {
            for (int i = 0; i < count; i++) {
                iosElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withLocatorMultipleTimes(String elementLocator, int timeout, int count) {
        IOSElement iosElement = findElementBy(getLocator(elementLocator), timeout);
        if (iosElement != null) {
            for (int i = 0; i < count; i++) {
                iosElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withLocatorAtIndex(String elementLocator, int index) {
        findElementsBy(getLocator(elementLocator)).get(index).click();
    }

    public void withLocatorAtIndex(String elementLocator, int timeout, int index) {
        findElementsBy(getLocator(elementLocator), timeout).get(index).click();
    }

    public void withLocatorAtIndexMultipleTimes(String elementLocator, int index, int count) {
        IOSElement iosElement = findElementsBy(getLocator(elementLocator)).get(index);
        if (iosElement != null) {
            for (int i = 0; i < count; i++) {
                iosElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withLocatorAtIndexMultipleTimes(String elementLocator, int timeout, int index, int count) {
        IOSElement iosElement = (IOSElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        if (iosElement != null) {
            for (int i = 0; i < count; i++) {
                iosElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withLocation(int x, int y, Duration time) {
        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y)).waitAction(new WaitOptions().withDuration(time)).release().perform();
    }
}
