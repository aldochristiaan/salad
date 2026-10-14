package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Espresso multiple tap utilities.
 * Provides methods for tapping elements multiple times with optional delays.
 */
public class MultipleTap extends Espresso {

    private static final int DEFAULT_DELAY_MILLIS = 150;

    public MultipleTap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Tap an element multiple times with default delay
     * @param elementLocator Locator for the element
     * @param count Number of times to tap
     */
    public void element(String elementLocator, int count) {
        element(elementLocator, count, DEFAULT_DELAY_MILLIS);
    }

    /**
     * Tap an element multiple times with custom delay
     * @param elementLocator Locator for the element
     * @param count Number of times to tap
     * @param delayMillis Delay between taps in milliseconds
     */
    public void element(String elementLocator, int count, int delayMillis) {
        validateTapCount(count);
        LogUtil.info(String.format("Tapping element '%s' %d times with %dms delay",
                elementLocator, count, delayMillis));

        WebElement webElement = findElementSafely(elementLocator);
        tapElementMultipleTimes(webElement, count, delayMillis);
    }

    /**
     * Tap a specific element from a list multiple times
     * @param elementLocator Locator for the elements
     * @param index Index of the element in the list
     * @param count Number of times to tap
     */
    public void elementAtIndex(String elementLocator, int index, int count) {
        validateTapCount(count);
        LogUtil.info(String.format("Tapping element '%s' at index %d, %d times",
                elementLocator, index, count));

        List<WebElement> elements = androidDriver.findElements(getLocator(elementLocator));
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException(
                String.format("Index %d out of bounds for element list of size %d",
                    index, elements.size()));
        }
        WebElement webElement = elements.get(index);
        tapElementMultipleTimes(webElement, count, 0);
    }

    /**
     * Validate tap count
     */
    private void validateTapCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Tap count must be positive");
        }
        if (count > 100) {
            LogUtil.warn("Tap count is unusually high: " + count);
        }
    }

    /**
     * Find element safely with better error messages
     */
    private WebElement findElementSafely(String elementLocator) {
        try {
            return androidDriver.findElement(getLocator(elementLocator));
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element: " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find element: " + elementLocator, e);
        }
    }

    /**
     * Tap element multiple times with optional delay
     */
    private void tapElementMultipleTimes(WebElement element, int count, int delayMillis) {
        for (int i = 0; i < count; i++) {
            element.click();
            if (delayMillis > 0 && i < count - 1) { // No delay after last tap
                delay(delayMillis);
            }
        }
    }
}
