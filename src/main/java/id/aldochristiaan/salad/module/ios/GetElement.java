package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.XCUITest;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

/**
 * iOS element retrieval utilities.
 * Provides methods for finding elements with optional timeout and swipe direction.
 */
public class GetElement extends XCUITest {

    public GetElement(IOSDriver iosDriver) {
        super(iosDriver);
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
