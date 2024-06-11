package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GetElement extends Espresso {

    public GetElement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public WebElement withLocator(String elementLocator) {
        return androidDriver.findElement(getLocator(elementLocator));
    }

    public WebElement withLocator(String elementLocator, int timeout) {
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(getLocator(elementLocator)));
    }
}
