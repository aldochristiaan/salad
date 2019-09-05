package id.aldochristiaan.salad.module.android;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.Android;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class Espresso extends Android {

    public Espresso(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void tap(String elementLocator) {
        try {
            androidDriver.findElement(getLocator(elementLocator)).click();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void tap(String elementLocator, int index) {
        try {
            androidDriver.findElements(getLocator(elementLocator)).get(index).click();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void tap(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
                Assert.assertTrue(androidElement.isDisplayed());
                androidElement.click();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                espressoSwipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
                delay(500);
            }
        }
    }

    public void tap(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                AndroidElement androidElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
                Assert.assertTrue(androidElement.isDisplayed());
                androidElement.click();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                espressoSwipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
                delay(500);
            }
        }
    }

    public void multipleTap(String elementLocator, int count) {
        try {
            AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
            for (int i = 0; i < count; i++) {
                androidElement.click();
                delay(150);
            }
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void multipleTap(String elementLocator, int count, int index) {
        try {
            AndroidElement androidElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
            for (int i = 0; i < count; i++) {
                androidElement.click();
            }
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public void type(String elementLocator, String text) {
        try {
            AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
            Assert.assertTrue(androidElement.isDisplayed());
            androidElement.clear();
            androidElement.sendKeys(text);
            hideKeyboard();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        } catch (AssertionError e) {
            throw new AssertionError("Failed to validate element : " + elementLocator, e);
        }
    }

    public void type(String elementLocator, String text, int index) {
        try {
            AndroidElement androidElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
            Assert.assertTrue(androidElement.isDisplayed());
            androidElement.clear();
            androidElement.sendKeys(text);
            hideKeyboard();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        } catch (AssertionError e) {
            throw new AssertionError("Failed to validate element : " + elementLocator, e);
        }
    }

    public void type(String elementLocator, String swipeLocator, String text) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
                Assert.assertTrue(androidElement.isDisplayed());
                androidElement.clear();
                androidElement.sendKeys(text);
                hideKeyboard();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                hideKeyboard();
                espressoSwipe().element(swipeLocator, SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
            }
        }
    }

    public void type(String elementLocator, String swipeLocator, String text, int index) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                AndroidElement androidElement = androidDriver.findElements(getLocator(elementLocator)).get(index);
                Assert.assertTrue(androidElement.isDisplayed());
                androidElement.clear();
                androidElement.sendKeys(text);
                hideKeyboard();
                break;
            } catch (InvalidElementStateException | NoSuchElementException | AssertionError e) {
                hideKeyboard();
                espressoSwipe().element(swipeLocator, SwipeSpeed.FAST, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
            }
        }
    }

    public void swipeTo(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            espressoSwipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
        }
    }

    public void swipeTo(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinate, Coordinates endCoordinate) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            espressoSwipe().element(swipeLocator, swipeSpeed, startCoordinate, endCoordinate, PrecisionDescriber.FINGER);
        }
    }

    public void swipeTo(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, index, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            espressoSwipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
        }
    }

    public void swipeTo(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinate, Coordinates endCoordinate) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, index, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            espressoSwipe().element(swipeLocator, swipeSpeed, startCoordinate, endCoordinate, PrecisionDescriber.FINGER);
        }
    }

    public boolean isElementExist(String elementLocator) {
        try {
            androidDriver.findElement(getLocator(elementLocator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementEnabled(String elementLocator) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("enabled"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public boolean isElementSelected(String elementLocator) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("selected"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public boolean isElementDisplayed(String elementLocator, int timeoutInSeconds) {
        int screenHeight = androidDriver.manage().window().getSize().getHeight();
        boolean isElementFound = false;
        int yPosition = 0;
        for (int i = 0; i < timeoutInSeconds * 5; i++) {
            try {
                yPosition = androidDriver.findElement(getLocator(elementLocator)).getLocation().getY();
                isElementFound = true;
                break;
            } catch (Exception e) {
                delay(200);
            }
        }
        if (isElementFound) {
            return screenHeight >= yPosition;
        } else {
            return false;
        }
    }

    public boolean isElementDisplayed(String elementLocator, int index, int timeoutInSeconds) {
        int screenHeight = androidDriver.manage().window().getSize().getHeight();
        boolean isElementFound = false;
        int yPosition = 0;
        for (int i = 0; i < timeoutInSeconds * 5; i++) {
            try {
                yPosition = androidDriver.findElements(getLocator(elementLocator)).get(i).getLocation().getY();
                isElementFound = true;
                break;
            } catch (Exception e) {
                delay(200);
            }
        }
        if (isElementFound) {
            return screenHeight >= yPosition;
        } else {
            return false;
        }
    }

    public void validateEnabled(String elementLocator) {
        validateValue().equalsTrue(isElementEnabled(elementLocator), "Element with locator : " + elementLocator + " is not enabled!");
    }

    public void validateEnabled(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementEnabled(elementLocator), errorMessage);
    }

    public void validateDisabled(String elementLocator) {
        validateValue().equalsFalse(isElementEnabled(elementLocator), "Element with locator : " + elementLocator + " is enabled!");
    }

    public void validateDisabled(String elementLocator, String errorMessage) {
        validateValue().equalsFalse(isElementEnabled(elementLocator), errorMessage);
    }

    public void validateSelected(String elementLocator) {
        validateValue().equalsTrue(isElementSelected(elementLocator), "Element with locator : " + elementLocator + " is not selected!");
    }

    public void validateSelected(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementSelected(elementLocator), errorMessage);
    }

    public void validateNotSelected(String elementLocator) {
        validateValue().equalsFalse(isElementSelected(elementLocator), "Element with locator : " + elementLocator + " is selected!");
    }

    public void validateNotSelected(String elementLocator, String errorMessage) {
        validateValue().equalsFalse(isElementSelected(elementLocator), errorMessage);
    }

    public void validateDisplayed(String elementLocator, int timeoutInSeconds) {
        validateValue().equalsTrue(isElementDisplayed(elementLocator, timeoutInSeconds), "Element with locator : " + elementLocator + " is not displayed on screen!");
    }

    public void validateDisplayed(String elementLocator, int timeoutInSeconds, String errorMessage) {
        validateValue().equalsTrue(isElementDisplayed(elementLocator, timeoutInSeconds), errorMessage);
    }

    public void validateNotDisplayed(String elementLocator, int timeoutInSeconds) {
        validateValue().equalsFalse(isElementDisplayed(elementLocator, timeoutInSeconds), "Element with locator : " + elementLocator + " is displayed on screen!");
    }

    public void validateNotDisplayed(String elementLocator, int timeoutInSeconds, String errorMessage) {
        validateValue().equalsFalse(isElementDisplayed(elementLocator, timeoutInSeconds), errorMessage);
    }

    public void validateExist(String elementLocator) {
        validateValue().equalsTrue(isElementExist(elementLocator), "Element with locator : " + elementLocator + " doesn't exist!");
    }

    public void validateExist(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementExist(elementLocator), errorMessage);
    }

    public void validateNotExist(String elementLocator) {
        validateValue().equalsFalse(isElementExist(elementLocator), "Element with locator : " + elementLocator + " do exist!");
    }

    public void validateNotExist(String elementLocator, String errorMessage) {
        validateValue().equalsFalse(isElementExist(elementLocator), errorMessage);
    }

    public void validateStaleness(AndroidElement androidElement, int timeoutInSeconds) {
        validateValue().equalsTrue((new WebDriverWait(androidDriver, timeoutInSeconds)).until(ExpectedConditions.stalenessOf(androidElement)));
    }

    public String getText(String elementLocator) {
        try {
            return androidDriver.findElement(getLocator(elementLocator)).getText();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    public String getText(String elementLocator, int index) {
        try {
            return androidDriver.findElements(getLocator(elementLocator)).get(index).getText();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    /*
      For args, please refer to http://appium.io/docs/en/commands/mobile-command/#android-espresso-only
     */
    public Object backdoor(ImmutableMap<String, Object> args) {
        return androidDriver.executeScript("mobile: backdoor", args);
    }
}
