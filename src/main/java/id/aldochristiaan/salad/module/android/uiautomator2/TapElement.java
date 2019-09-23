package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.Tapper;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.HashMap;

public class TapElement extends UiAutomator2 {

    public TapElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void withLocator(String elementLocator) {
        findElementBy(getLocator(elementLocator)).click();
    }

    public void withLocator(String elementLocator, int timeout) {
        findElementBy(getLocator(elementLocator), timeout).click();
    }

    public void withLocatorMultipleTimes(String elementLocator, int count) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator));
        if (androidElement != null) {
            for (int i = 0; i < count; i++) {
                androidElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withLocatorMultipleTimes(String elementLocator, int timeout, int count) {
        AndroidElement androidElement = findElementBy(getLocator(elementLocator), timeout);
        if (androidElement != null) {
            for (int i = 0; i < count; i++) {
                androidElement.click();
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
        AndroidElement androidElement = findElementsBy(getLocator(elementLocator)).get(index);
        if (androidElement != null) {
            for (int i = 0; i < count; i++) {
                androidElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withLocatorAtIndexMultipleTimes(String elementLocator, int timeout, int index, int count) {
        AndroidElement androidElement = (AndroidElement) findElementsBy(getLocator(elementLocator), timeout).get(index);
        if (androidElement != null) {
            for (int i = 0; i < count; i++) {
                androidElement.click();
            }
        } else {
            LogUtil.error("Couldn't find element with locator : " + elementLocator);
        }
    }

    public void withEspresso(String elementLocator, Tapper tapper, Coordinates coordinates, PrecisionDescriber precisionDescriber) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", getElement().withLocator(elementLocator, 2));
        args.put("tapper", tapper.toString());
        args.put("coordinatesProvider", coordinates.toString());
        args.put("precisionDescriber", precisionDescriber.toString());
        androidDriver.executeScript("mobile:clickAction", args);
    }

    public void withLocation(int x, int y, Duration time) {
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(new PointOption().withCoordinates(x, y)).waitAction(new WaitOptions().withDuration(time)).release().perform();
    }
}
