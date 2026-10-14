package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Espresso flash element utilities.
 * Provides method for flashing (highlighting) elements visually.
 */
public class Flash extends Espresso {

    private static final int DEFAULT_DURATION_MS = 500;
    private static final int DEFAULT_REPEAT_COUNT = 3;

    public Flash(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Flash an element with default settings (500ms, 3 times)
     * @param elementLocator Locator for the element to flash
     */
    public void element(String elementLocator) {
        element(elementLocator, DEFAULT_DURATION_MS, DEFAULT_REPEAT_COUNT);
    }

    /**
     * Flash an element with custom duration and repeat count
     * @param elementLocator Locator for the element to flash
     * @param durationMillis Duration of each flash in milliseconds
     * @param repeatCount Number of times to flash the element
     */
    public void element(String elementLocator, int durationMillis, int repeatCount) {
        if (durationMillis <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (repeatCount <= 0) {
            throw new IllegalArgumentException("Repeat count must be positive");
        }

        LogUtil.info(String.format("Flashing element '%s' (%dms, %d times)",
                elementLocator, durationMillis, repeatCount));

        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "durationMillis", durationMillis,
                "repeatCount", repeatCount
        );
        androidDriver.executeScript("mobile:flashElement", args);
    }
}
