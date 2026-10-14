package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * UiAutomator2 multiple element retrieval utilities.
 * Provides methods for finding lists of elements with optional timeout and swipe direction.
 */
public class GetMultipleElement extends UiAutomator2 {

    public GetMultipleElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Get all matching elements with swipe-to-find functionality (swipes up by default)
     * @param elementLocator Locator for the elements
     * @return List of WebElements found
     */
    public List<WebElement> withLocator(String elementLocator) {
        return findElementsBy(getLocator(elementLocator));
    }

    /**
     * Get all matching elements with explicit wait
     * @param elementLocator Locator for the elements
     * @param timeout Maximum time to wait in seconds
     * @return List of WebElements found
     */
    public List<WebElement> withLocator(String elementLocator, int timeout) {
        return findElementsBy(getLocator(elementLocator), timeout);
    }

    /**
     * Get all matching elements with swipe-to-find in specified direction
     * @param elementLocator Locator for the elements
     * @param direction Direction to swipe while searching
     * @return List of WebElements found
     */
    public List<WebElement> withLocator(String elementLocator, Direction direction) {
        return findElementsBy(getLocator(elementLocator), direction);
    }

    /**
     * Get all matching elements with default timeout
     * @param elementLocator Locator for the elements
     * @return List of WebElements found
     */
    public List<WebElement> withDefaultTimeout(String elementLocator) {
        return withLocator(elementLocator, SaladConfig.DEFAULT_TIMEOUT);
    }

    /**
     * Get count of matching elements
     * @param elementLocator Locator for the elements
     * @return Number of matching elements
     */
    public int count(String elementLocator) {
        return withLocator(elementLocator).size();
    }
}
