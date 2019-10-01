package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.module.ios.*;
import id.aldochristiaan.salad.util.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class XCUITest extends Mobile {

    protected IOSDriver<IOSElement> iosDriver;

    public XCUITest(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
    }

    protected GetElement getElement() {
        return new GetElement(iosDriver);
    }

    protected GetMultipleElement getMultipleElement() {
        return new GetMultipleElement(iosDriver);
    }

    protected LongTap longTapElement() {
        return new LongTap(iosDriver);
    }

    protected Swipe swipe() {
        return new Swipe(iosDriver);
    }

    protected Tap tapElement() {
        return new Tap(iosDriver);
    }

    protected ValidateValue validateValue() {
        return new ValidateValue();
    }

    protected Type typeText() {
        return new Type(iosDriver);
    }

    protected Randomize randomize() {
        return new Randomize();
    }

    protected FakerUtil fakerUtil() {
        return new FakerUtil();
    }

    protected Deeplink deeplink() {
        return new Deeplink(iosDriver);
    }

    protected IOSElement findElementBy(By by) {
        IOSElement element = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                element = iosDriver.findElement(by);
                break;
            } catch (NoSuchElementException e) {
                swipe().up();
            }
        }
        return element;
    }

    protected IOSElement findElementBy(By by, Direction direction) {
        IOSElement element = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                element = iosDriver.findElement(by);
                break;
            } catch (NoSuchElementException e) {
                swipe().toDirection(direction);
            }
        }
        return element;
    }

    protected IOSElement findElementBy(By by, int timeout) {
        return (IOSElement) (new WebDriverWait(iosDriver, timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<IOSElement> findElementsBy(By by) {
        List<IOSElement> elements = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                elements = iosDriver.findElements(by);
                break;
            } catch (NoSuchElementException e) {
                swipe().up();
            }
        }
        return elements;
    }

    protected List<IOSElement> findElementsBy(By by, Direction direction) {
        List<IOSElement> elements = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                elements = iosDriver.findElements(by);
                break;
            } catch (NoSuchElementException e) {
                swipe().toDirection(direction);
            }
        }
        return elements;
    }

    protected List<WebElement> findElementsBy(By by, int timeout) {
        return (new WebDriverWait(iosDriver, timeout))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected boolean isElementExist(String elementLocator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(iosDriver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(elementLocator)));
            return true;
        } catch (Exception e) {
            LogUtil.error("Element with locator : " + elementLocator + " is not present!");
            return false;
        }
    }

    protected boolean isElementVisible(String elementLocator) {
        try {
            return Boolean.parseBoolean(iosDriver.findElement(getLocator(elementLocator)).getAttribute("visible"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    protected boolean isElementEnabled(String elementLocator) {
        try {
            return Boolean.parseBoolean(iosDriver.findElement(getLocator(elementLocator)).getAttribute("enabled"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    protected boolean isElementSelected(String elementLocator) {
        try {
            return Boolean.parseBoolean(iosDriver.findElement(getLocator(elementLocator)).getAttribute("selected"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    protected boolean isElementChecked(String elementLocator) {
        try {
            return Boolean.parseBoolean(iosDriver.findElement(getLocator(elementLocator)).getAttribute("checked"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    protected String getElementAttributeValue(String elementLocator, String attribute) {
        if (isElementVisible(elementLocator)) {
            return iosDriver.findElement(getLocator(elementLocator)).getAttribute(attribute);
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    protected void validateElementVisible(String elementLocator) {
        if (isElementVisible(elementLocator)) {
            validateValue().equalsTrue(isElementVisible(elementLocator));
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    protected void validateElementWithText(String elementLocator, String text) {
        if (isElementVisible(elementLocator)) {
            validateValue().equals(text, getText(elementLocator));
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    protected void validateElementContainsText(String elementLocator, String text) {
        if (isElementVisible(elementLocator)) {
            validateValue().contains(text, getText(elementLocator));
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    protected String getText(String elementLocator) {
        try {
            return iosDriver.findElement(getLocator(elementLocator)).getText();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    protected String getText(String elementLocator, int index) {
        try {
            return iosDriver.findElements(getLocator(elementLocator)).get(index).getText();
        } catch (InvalidElementStateException e) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
        }
    }

    protected void validateEnabled(String elementLocator) {
        validateValue().equalsTrue(isElementEnabled(elementLocator), "Element with locator : " + elementLocator + " is not enabled!");
    }

    protected void validateEnabled(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementEnabled(elementLocator), errorMessage);
    }

    protected void validateDisabled(String elementLocator) {
        validateValue().equalsFalse(isElementEnabled(elementLocator), "Element with locator : " + elementLocator + " is enabled!");
    }

    protected void validateDisabled(String elementLocator, String errorMessage) {
        validateValue().equalsFalse(isElementEnabled(elementLocator), errorMessage);
    }

    protected void validateSelected(String elementLocator) {
        validateValue().equalsTrue(isElementSelected(elementLocator), "Element with locator : " + elementLocator + " is not selected!");
    }

    protected void validateSelected(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementSelected(elementLocator), errorMessage);
    }

    protected void validateNotSelected(String elementLocator) {
        validateValue().equalsFalse(isElementSelected(elementLocator), "Element with locator : " + elementLocator + " is selected!");
    }

    protected void validateNotSelected(String elementLocator, String errorMessage) {
        validateValue().equalsFalse(isElementSelected(elementLocator), errorMessage);
    }

    protected void validateDisplayed(String elementLocator) {
        validateValue().equalsTrue(isElementVisible(elementLocator), "Element with locator : " + elementLocator + " is not displayed on screen!");
    }

    protected void validateDisplayed(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementVisible(elementLocator), errorMessage);
    }

    protected void validateNotDisplayed(String elementLocator) {
        validateValue().equalsFalse(isElementVisible(elementLocator), "Element with locator : " + elementLocator + " is displayed on screen!");
    }

    protected void validateNotDisplayed(String elementLocator, String errorMessage) {
        validateValue().equalsFalse(isElementVisible(elementLocator), errorMessage);
    }

    protected void validateExist(String elementLocator, int timeout) {
        validateValue().equalsTrue(isElementExist(elementLocator, timeout), "Element with locator : " + elementLocator + " doesn't exist!");
    }

    protected void validateExist(String elementLocator, int timeout, String errorMessage) {
        validateValue().equalsTrue(isElementExist(elementLocator, timeout), errorMessage);
    }

    protected void validateNotExist(String elementLocator, int timeout) {
        validateValue().equalsFalse(isElementExist(elementLocator, timeout), "Element with locator : " + elementLocator + " do exist!");
    }

    protected void validateNotExist(String elementLocator, int timeout, String errorMessage) {
        validateValue().equalsFalse(isElementExist(elementLocator, timeout), errorMessage);
    }

    protected void validateChecked(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementChecked(elementLocator), errorMessage);
    }

    protected void validateStaleness(IOSElement iosElement, int timeoutInSeconds) {
        validateValue().equalsTrue((new WebDriverWait(iosDriver, timeoutInSeconds)).until(ExpectedConditions.stalenessOf(iosElement)));
    }

    protected void takeScreenshot(String name) {
        File scrFile = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
        File imageFile = new File("screenshot/" + name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(scrFile), imageFile);
            LogUtil.info("Screenshot taken!");
        } catch (IOException e) {
            LogUtil.error("Failed to take screenshot!");
            e.printStackTrace();
        }
    }

    protected void takeScreenshot(String path, String name) {
        File scrFile = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
        File imageFile = new File(path + "/" + name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(scrFile), imageFile);
            LogUtil.info("Screenshot taken!");
        } catch (IOException e) {
            LogUtil.error("Failed to take screenshot!");
            e.printStackTrace();
        }
    }

    protected void hideKeyboard() {
        try {
            iosDriver.hideKeyboard();
        } catch (Exception e) {
            LogUtil.info("No visible keyboard!");
        }
    }
}
