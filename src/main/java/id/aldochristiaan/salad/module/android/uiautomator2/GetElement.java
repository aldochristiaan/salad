package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GetElement extends UiAutomator2 {

    public GetElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public AndroidElement withLocator(String elementLocator) {
        return findElementBy(getLocator(elementLocator));
    }

    public AndroidElement withLocator(String elementLocator, int timeout) {
        return findElementBy(getLocator(elementLocator), timeout);
    }

    public AndroidElement withLocator(String elementLocator, Direction direction) {
        return findElementBy(getLocator(elementLocator), direction);
    }
}
