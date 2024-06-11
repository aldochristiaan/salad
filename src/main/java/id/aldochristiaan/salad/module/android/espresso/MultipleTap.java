package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class MultipleTap extends Espresso {

    public MultipleTap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, int count) {
        try {
            WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
            for (int i = 0; i < count; i++) {
                webElement.click();
                delay(150);
            }
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void element(String elementLocator, int count, int index) {
        try {
            WebElement webElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
            for (int i = 0; i < count; i++) {
                webElement.click();
            }
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }
}
