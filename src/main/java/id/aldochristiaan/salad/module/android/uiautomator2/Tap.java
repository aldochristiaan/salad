package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class Tap extends UiAutomator2 {

    public Tap(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        findElementBy(getLocator(elementLocator)).click();
    }

    public void element(String elementLocator, int index) {
        findElementsBy(getLocator(elementLocator)).get(index).click();
    }

    public void pendingElement(String elementLocator, int timeout) {
        findElementBy(getLocator(elementLocator), timeout).click();
    }

    public void pendingElement(String elementLocator, int timeout, int index) {
        findElementsBy(getLocator(elementLocator), timeout).get(index).click();
    }

    public void location(int x, int y, Duration time) {
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(new PointOption().withCoordinates(x, y)).waitAction(new WaitOptions().withDuration(time)).release().perform();
    }
}
