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

import java.util.List;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class Type extends Espresso {

    private static final Coordinates DEFAULT_START = Coordinates.CENTER;
    private static final Coordinates DEFAULT_END = Coordinates.TOP_CENTER;
    private static final PrecisionDescriber DEFAULT_PRECISION = PrecisionDescriber.FINGER;

    public Type(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String text) {
        typeSafely(() -> androidDriver.findElement(getLocator(elementLocator)), elementLocator, text);
    }

    public void element(String elementLocator, String text, int index) {
        typeSafely(() -> {
            List<WebElement> elements = androidDriver.findElements(getLocator(elementLocator));
            return elements.get(index);
        }, elementLocator, text);
    }

    public void element(String elementLocator, String swipeLocator, String text) {
        swipeAndType(() -> androidDriver.findElement(getLocator(elementLocator)), elementLocator, swipeLocator, text);
    }

    public void element(String elementLocator, String swipeLocator, String text, int index) {
        swipeAndType(() -> {
            List<WebElement> elements = androidDriver.findElements(getLocator(elementLocator));
            return elements.get(index);
        }, elementLocator, swipeLocator, text);
    }

    private void typeSafely(ElementSupplier supplier, String locator, String text) {
        try {
            WebElement element = supplier.get();
            Assert.assertTrue(element.isDisplayed());
            element.clear();
            element.sendKeys(text);
            hideKeyboard();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element: " + locator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element: " + locator, e);
        } catch (AssertionError e) {
            throw new AssertionError("Failed to validate element: " + locator, e);
        }
    }

    private void swipeAndType(ElementSupplier supplier, String locator, String swipeLocator, String text) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                WebElement element = supplier.get();
                Assert.assertTrue(element.isDisplayed());
                element.clear();
                element.sendKeys(text);
                hideKeyboard();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                hideKeyboard();
                swipe().element(swipeLocator, SwipeSpeed.FAST, DEFAULT_START, DEFAULT_END, DEFAULT_PRECISION);
            }
        }
    }

    @FunctionalInterface
    private interface ElementSupplier {
        WebElement get();
    }
}
