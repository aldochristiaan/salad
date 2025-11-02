package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GetElement extends Espresso {

    private static final int DEFAULT_TIMEOUT_SECONDS = 0;

    public GetElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public WebElement withLocator(String elementLocator) {
        return withLocator(elementLocator, DEFAULT_TIMEOUT_SECONDS);
    }

    public WebElement withLocator(String elementLocator, int timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            return androidDriver.findElement(getLocator(elementLocator));
        }
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(getLocator(elementLocator)));
    }
}
