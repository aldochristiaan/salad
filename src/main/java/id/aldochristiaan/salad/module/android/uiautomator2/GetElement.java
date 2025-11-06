package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

/**
 * UiAutomator2 element retrieval utilities.
 * Provides methods for finding elements with optional timeout and swipe direction.
 */
public class GetElement extends UiAutomator2 {

    public GetElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Get element with swipe-to-find functionality (swipes up by default)
     * @param elementLocator Locator for the element
     * @return WebElement found
     */
    public WebElement withLocator(String elementLocator) {
        return findElementBy(getLocator(elementLocator));
    }

    /**
     * Get element with explicit wait
     * @param elementLocator Locator for the element
     * @param timeout Maximum time to wait in seconds
     * @return WebElement found
     */
    public WebElement withLocator(String elementLocator, int timeout) {
        return findElementBy(getLocator(elementLocator), timeout);
    }

    /**
     * Get element with swipe-to-find in specified direction
     * @param elementLocator Locator for the element
     * @param direction Direction to swipe while searching
     * @return WebElement found
     */
    public WebElement withLocator(String elementLocator, Direction direction) {
        return findElementBy(getLocator(elementLocator), direction);
    }

    /**
     * Get element with default timeout
     * @param elementLocator Locator for the element
     * @return WebElement found
     */
    public WebElement withDefaultTimeout(String elementLocator) {
        return withLocator(elementLocator, SaladConfig.DEFAULT_TIMEOUT);
    }
}
