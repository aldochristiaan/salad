package id.aldochristiaan.salad.module.android.espresso;

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

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class Tap extends Espresso {

    public Tap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        try {
            androidDriver.findElement(getLocator(elementLocator)).click();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void element(String elementLocator, int index) {
        try {
            androidDriver.findElements(getLocator(elementLocator)).get(index).click();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
                Assert.assertTrue(webElement.isDisplayed());
                webElement.click();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                swipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
                delay(500);
            }
        }
    }

    public void element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                WebElement webElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
                Assert.assertTrue(webElement.isDisplayed());
                webElement.click();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                swipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
                delay(500);
            }
        }
    }

    public void location(int x, int y, Duration time) {
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(new PointOption().withCoordinates(x, y)).waitAction(new WaitOptions().withDuration(time)).release().perform();
    }
}
