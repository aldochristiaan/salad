package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GetMultipleElement extends Espresso {

    public GetMultipleElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public List<WebElement> withLocator(String elementLocator) {
        return androidDriver.findElements(getLocator(elementLocator));
    }

    public List<WebElement> withLocator(String elementLocator, int timeout) {
        return (new WebDriverWait(androidDriver, Duration.ofSeconds(timeout)))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(elementLocator)));
    }
}
