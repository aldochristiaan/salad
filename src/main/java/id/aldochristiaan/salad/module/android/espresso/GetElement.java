package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Espresso element retrieval utilities.
 * Provides methods for finding elements with optional waiting.
 */
public class GetElement extends Espresso {

    public GetElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Get element immediately without waiting
     * @param elementLocator Locator for the element
     * @return WebElement found
     */
    public WebElement withLocator(String elementLocator) {
        return androidDriver.findElement(getLocator(elementLocator));
    }

    /**
     * Get element with explicit wait
     * @param elementLocator Locator for the element
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return WebElement found
     */
    public WebElement withLocator(String elementLocator, int timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            return withLocator(elementLocator);
        }
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(getLocator(elementLocator)));
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
