package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;


public class Tap extends Espresso {

    private static final Coordinates DEFAULT_START = Coordinates.CENTER;
    private static final Coordinates DEFAULT_END = Coordinates.TOP_CENTER;
    private static final PrecisionDescriber DEFAULT_PRECISION = PrecisionDescriber.FINGER;
    private static final int DEFAULT_DELAY = 500;

    public Tap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        clickSafely(() -> androidDriver.findElement(getLocator(elementLocator)), elementLocator);
    }

    public void element(String elementLocator, int index) {
        clickSafely(() -> {
            List<WebElement> elements = androidDriver.findElements(getLocator(elementLocator));
            return elements.get(index);
        }, elementLocator);
    }

    public void element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed) {
        swipeUntilVisible(() -> androidDriver.findElement(getLocator(elementLocator)), elementLocator, swipeLocator, swipeSpeed);
    }

    public void element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed) {
        swipeUntilVisible(() -> {
            List<WebElement> elements = androidDriver.findElements(getLocator(elementLocator));
            return elements.get(index);
        }, elementLocator, swipeLocator, swipeSpeed);
    }

    public void location(int x, int y, Duration time) {
        new TouchAction(androidDriver)
                .press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(time))
                .release()
                .perform();
    }

    private void clickSafely(ElementSupplier supplier, String locator) {
        try {
            supplier.get().click();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element: " + locator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element: " + locator, e);
        }
    }

    private void swipeUntilVisible(ElementSupplier supplier, String locator, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int i = 0; i < SaladConfig.MAX_SWIPE_COUNT; i++) {
            try {
                WebElement element = supplier.get();
                Assert.assertTrue(element.isDisplayed());
                element.click();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                swipe().element(swipeLocator, swipeSpeed, DEFAULT_START, DEFAULT_END, DEFAULT_PRECISION);
                delay(DEFAULT_DELAY);
            }
        }
    }

    @FunctionalInterface
    private interface ElementSupplier {
        WebElement get();
    }
}
