package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GetMultipleElement extends Espresso {

    public GetMultipleElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public List<AndroidElement> withLocator(String elementLocator) {
        return androidDriver.findElements(getLocator(elementLocator));
    }

    public List<WebElement> withLocator(String elementLocator, int timeout) {
        return (new WebDriverWait(androidDriver, timeout))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(elementLocator)));
    }
}
