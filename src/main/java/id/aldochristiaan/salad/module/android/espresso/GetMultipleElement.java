package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Espresso multiple element retrieval utilities.
 * Provides methods for finding lists of elements with optional waiting.
 */
public class GetMultipleElement extends Espresso {

    public GetMultipleElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Get all matching elements immediately without waiting
     * @param elementLocator Locator for the elements
     * @return List of WebElements found
     */
    public List<WebElement> withLocator(String elementLocator) {
        return androidDriver.findElements(getLocator(elementLocator));
    }

    /**
     * Get all matching elements with explicit wait
     * @param elementLocator Locator for the elements
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return List of WebElements found
     */
    public List<WebElement> withLocator(String elementLocator, int timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            return withLocator(elementLocator);
        }
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(elementLocator)));
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
