package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MultipleTap extends Espresso {

    private static final int DEFAULT_DELAY_MILLIS = 150;

    public MultipleTap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, int count) {
        WebElement webElement = findElementSafely(elementLocator);
        tapElementMultipleTimes(webElement, count, true);
    }

    public void element(String elementLocator, int count, int index) {
        List<WebElement> elements = androidDriver.findElements(getLocator(elementLocator));
        if (index < 0 || index >= elements.size()) {
            throw new NoSuchElementException("Index out of bounds for element list: " + index);
        }
        WebElement webElement = elements.get(index);
        tapElementMultipleTimes(webElement, count, false);
    }

    private WebElement findElementSafely(String elementLocator) {
        try {
            return androidDriver.findElement(getLocator(elementLocator));
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element: " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element: " + elementLocator, e);
        }
    }

    private void tapElementMultipleTimes(WebElement element, int count, boolean withDelay) {
        for (int i = 0; i < count; i++) {
            element.click();
            if (withDelay) {
                delay(DEFAULT_DELAY_MILLIS);
            }
        }
    }
}
