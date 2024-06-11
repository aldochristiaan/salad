package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class Type extends Espresso {

    public Type(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String text) {
        try {
            WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
            Assert.assertTrue(webElement.isDisplayed());
            webElement.clear();
            webElement.sendKeys(text);
            hideKeyboard();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        } catch (AssertionError e) {
            throw new AssertionError("Failed to validate element : " + elementLocator, e);
        }
    }

    public void element(String elementLocator, String text, int index) {
        try {
            WebElement webElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
            Assert.assertTrue(webElement.isDisplayed());
            webElement.clear();
            webElement.sendKeys(text);
            hideKeyboard();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        } catch (AssertionError e) {
            throw new AssertionError("Failed to validate element : " + elementLocator, e);
        }
    }

    public void element(String elementLocator, String swipeLocator, String text) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
                Assert.assertTrue(webElement.isDisplayed());
                webElement.clear();
                webElement.sendKeys(text);
                hideKeyboard();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                hideKeyboard();
                swipe().element(swipeLocator, SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
            }
        }
    }

    public void element(String elementLocator, String swipeLocator, String text, int index) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                WebElement webElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
                Assert.assertTrue(webElement.isDisplayed());
                webElement.clear();
                webElement.sendKeys(text);
                hideKeyboard();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                hideKeyboard();
                swipe().element(swipeLocator, SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
            }
        }
    }
}
