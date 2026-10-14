package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.XCUITest;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

/**
 * iOS tap interaction utilities.
 * Provides methods for tapping elements and specific coordinates.
 */
public class Tap extends XCUITest {

    public Tap(IOSDriver iosDriver) {
        super(iosDriver);
    }

    /**
     * Tap an element immediately
     * @param elementLocator Locator for the element
     */
    public void element(String elementLocator) {
        LogUtil.info("Tapping element: " + elementLocator);
        findElementBy(getLocator(elementLocator)).click();
    }

    /**
     * Tap a specific element from a list
     * @param elementLocator Locator for the elements
     * @param index Index of the element in the list
     */
    public void element(String elementLocator, int index) {
        LogUtil.info(String.format("Tapping element '%s' at index %d", elementLocator, index));
        findElementsBy(getLocator(elementLocator)).get(index).click();
    }

    /**
     * Tap an element after waiting for it to appear
     * @param elementLocator Locator for the element
     * @param timeout Maximum time to wait in seconds
     */
    public void elementWithWait(String elementLocator, int timeout) {
        LogUtil.info(String.format("Waiting %ds to tap element: %s", timeout, elementLocator));
        findElementBy(getLocator(elementLocator), timeout).click();
    }

    /**
     * Tap a specific element from a list after waiting
     * @param elementLocator Locator for the elements
     * @param timeout Maximum time to wait in seconds
     * @param index Index of the element in the list
     */
    public void elementWithWait(String elementLocator, int timeout, int index) {
        LogUtil.info(String.format("Waiting %ds to tap element '%s' at index %d",
                timeout, elementLocator, index));
        findElementsBy(getLocator(elementLocator), timeout).get(index).click();
    }

    /**
     * Tap at specific screen coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @param duration Duration to hold the tap
     */
    public void location(int x, int y, Duration duration) {
        LogUtil.info(String.format("Tapping at coordinates (%d, %d) for %dms",
                x, y, duration.toMillis()));
        TouchAction<?> touchAction = new TouchAction<>(iosDriver);
        touchAction
                .press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(duration))
                .release()
                .perform();
    }

    /**
     * Tap at specific screen coordinates with default duration
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void location(int x, int y) {
        location(x, y, SaladConfig.toMillisDuration(500));
    }

    // Backward compatibility
    @Deprecated
    public void pendingElement(String elementLocator, int timeout) {
        elementWithWait(elementLocator, timeout);
    }

    @Deprecated
    public void pendingElement(String elementLocator, int timeout, int index) {
        elementWithWait(elementLocator, timeout, index);
    }
}
