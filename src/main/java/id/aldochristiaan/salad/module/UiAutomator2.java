package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.module.android.uiautomator2.*;
import id.aldochristiaan.salad.util.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class UiAutomator2 extends Mobile {

    protected final AndroidDriver androidDriver;

    public UiAutomator2(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    // === Module Accessors ===
    protected Tap tap() {
        return new Tap(androidDriver);
    }

    protected Type type() {
        return new Type(androidDriver);
    }

    protected Swipe swipe() {
        return new Swipe(androidDriver);
    }

    protected GetElement getElement() {
        return new GetElement(androidDriver);
    }

    protected GetMultipleElement getMultipleElement() {
        return new GetMultipleElement(androidDriver);
    }

    protected Toast toast() {
        return new Toast(androidDriver);
    }

    protected LongTap longTap() {
        return new LongTap(androidDriver);
    }

    protected MobileGesture mobileGesture() {
        return new MobileGesture(androidDriver);
    }

    protected ChangeContext changeContext() {
        return new ChangeContext(androidDriver);
    }

    // === Utility Accessors ===
    protected ValidateValue validateValue() {
        return new ValidateValue();
    }

    protected Randomize randomize() {
        return new Randomize();
    }

    protected FakerUtil fakerUtil() {
        return new FakerUtil();
    }

    // === Element Finders ===
    protected WebElement findElementBy(By by) {
        return findElementBy(by, Direction.UP);
    }

    protected WebElement findElementBy(By by, Direction direction) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                return androidDriver.findElement(by);
            } catch (NoSuchElementException e) {
                swipe().toDirection(direction);
            }
        }
        throw new NoSuchElementException("Couldn't find this element: " + by);
    }

    protected WebElement findElementBy(By by, int timeout) {
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<WebElement> findElementsBy(By by) {
        return findElementsBy(by, Direction.UP);
    }

    protected List<WebElement> findElementsBy(By by, Direction direction) {
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                return androidDriver.findElements(by);
            } catch (NoSuchElementException e) {
                swipe().toDirection(direction);
            }
        }
        throw new NoSuchElementException("Couldn't find this element: " + by);
    }

    protected List<WebElement> findElementsBy(By by, int timeout) {
        return new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    // === Element State Checks ===
    protected boolean isElementExist(String elementLocator, int timeout) {
        try {
            new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.presenceOfElementLocated(getLocator(elementLocator)));
            return true;
        } catch (Exception e) {
            LogUtil.error("Element not present: " + elementLocator);
            return false;
        }
    }

    protected boolean isElementDisplayed(String elementLocator) {
        return androidDriver.findElement(getLocator(elementLocator)).isDisplayed();
    }

    protected boolean isElementDisplayed(String elementLocator, int timeout) {
        try {
            new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(elementLocator)));
            return true;
        } catch (Exception e) {
            LogUtil.error("Element not visible: " + elementLocator);
            return false;
        }
    }

    protected boolean isElementDisplayed(String elementLocator, int timeout, String errorMessage) {
        try {
            new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOfElementLocated(getLocator(elementLocator)));
            return true;
        } catch (Exception e) {
            LogUtil.error(errorMessage);
            return false;
        }
    }

    protected boolean isElementEnabled(String elementLocator) {
        return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("enabled"));
    }

    protected boolean isElementEnabled(String elementLocator, String errorMessage) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("enabled"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(errorMessage);
        }
    }

    protected boolean isElementSelected(String elementLocator) {
        return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("selected"));
    }

    protected boolean isElementSelected(String elementLocator, String errorMessage) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("selected"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(errorMessage);
        }
    }

    protected boolean isElementChecked(String elementLocator) {
        return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("checked"));
    }

    protected boolean isElementChecked(String elementLocator, String errorMessage) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("checked"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(errorMessage);
        }
    }

    // === Attribute & Text Access ===
    protected String getElementAttributeValue(String elementLocator, int timeout, String attribute) {
        if (isElementDisplayed(elementLocator, timeout)) {
            return androidDriver.findElement(getLocator(elementLocator)).getAttribute(attribute);
        }
        throw new NoSuchElementException("Couldn't find this element: " + elementLocator);
    }

    protected String getText(String elementLocator) {
        return androidDriver.findElement(getLocator(elementLocator)).getText();
    }

    protected String getText(String elementLocator, int index) {
        return androidDriver.findElements(getLocator(elementLocator)).get(index).getText();
    }

    // === Validation Methods ===
    protected void validateDisplayed(String elementLocator, int timeout) {
        validateValue().equalsTrue(isElementExist(elementLocator, timeout), "Element not displayed: " + elementLocator);
    }

    protected void validateDisplayed(String elementLocator, int timeout, String errorMessage) {
        validateValue().equalsTrue(isElementDisplayed(elementLocator, timeout, errorMessage));
    }

    protected void validateNotDisplayed(String elementLocator, int timeout) {
        validateValue().equalsFalse(isElementDisplayed(elementLocator, timeout), "Element is displayed: " + elementLocator);
    }

    protected void validateNotDisplayed(String elementLocator, int timeout, String errorMessage) {
        validateValue().equalsFalse(isElementDisplayed(elementLocator, timeout, errorMessage));
    }

    protected void validateExist(String elementLocator, int timeout) {
        validateValue().equalsTrue(isElementExist(elementLocator, timeout), "Element doesn't exist: " + elementLocator);
    }

    protected void validateNotExist(String elementLocator, int timeout) {
        validateValue().equalsFalse(isElementExist(elementLocator, timeout), "Element exists: " + elementLocator);
    }

    protected void validateEnabled(String elementLocator) {
        validateValue().equalsTrue(isElementEnabled(elementLocator), "Element not enabled: " + elementLocator);
    }

    protected void validateDisabled(String elementLocator) {
        validateValue().equalsFalse(isElementEnabled(elementLocator), "Element is enabled: " + elementLocator);
    }

    protected void validateSelected(String elementLocator) {
        validateValue().equalsTrue(isElementSelected(elementLocator), "Element not selected: " + elementLocator);
    }

    protected void validateNotSelected(String elementLocator) {
        validateValue().equalsFalse(isElementSelected(elementLocator), "Element is selected: " + elementLocator);
    }

    protected void validateChecked(String elementLocator) {
        validateValue().equalsTrue(isElementChecked(elementLocator), "Element not checked: " + elementLocator);
    }

    protected void validateNotChecked(String elementLocator) {
        validateValue().equalsFalse(isElementChecked(elementLocator), "Element is checked: " + elementLocator);
    }

    protected void validateStaleness(WebElement element, int timeout) {
        validateValue().equalsTrue(
                new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                        .until(ExpectedConditions.stalenessOf(element)),
                "Element did not become stale within timeout"
        );
    }

    // === Screenshot Utilities ===
    protected void takeScreenshot(String name) {
        takeScreenshot("screenshot", name);
    }

    protected void takeScreenshot(String path, String name) {
        File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        File imageFile = new File(path + "/" + name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(scrFile), imageFile);
            LogUtil.info("Screenshot taken: " + imageFile.getAbsolutePath());
        } catch (IOException e) {
            LogUtil.error("Failed to take screenshot: " + e.getMessage());
        }
    }

    // === System Actions ===
    protected void pressBackButton() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    protected void pressEnterButton() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    protected void pressSearchButton() {
        androidDriver.pressKey(
                new KeyEvent(AndroidKey.ENTER)
                        .withFlag(KeyEventFlag.SOFT_KEYBOARD)
                        .withFlag(KeyEventFlag.KEEP_TOUCH_MODE)
                        .withFlag(KeyEventFlag.EDITOR_ACTION)
        );
    }

    protected void hideKeyboard() {
        try {
            androidDriver.hideKeyboard();
        } catch (Exception e) {
            LogUtil.info("No visible keyboard to hide.");
        }
    }

    protected void openDeeplink(String deeplink) {
        androidDriver.get(deeplink);
    }
}
