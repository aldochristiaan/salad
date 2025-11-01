package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GetMultipleElement extends Espresso {

    private static final int DEFAULT_TIMEOUT_SECONDS = 0;

    public GetMultipleElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public List<WebElement> withLocator(String elementLocator) {
        return withLocator(elementLocator, DEFAULT_TIMEOUT_SECONDS);
    }

    public List<WebElement> withLocator(String elementLocator, int timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            return androidDriver.findElements(getLocator(elementLocator));
        }
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(elementLocator)));
    }
}
