package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetElement extends Espresso {

    public GetElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public AndroidElement withLocator(String elementLocator) {
        return androidDriver.findElement(getLocator(elementLocator));
    }

    public AndroidElement withLocator(String elementLocator, int timeout) {
        return (AndroidElement) (new WebDriverWait(androidDriver, timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(getLocator(elementLocator)));
    }
}
