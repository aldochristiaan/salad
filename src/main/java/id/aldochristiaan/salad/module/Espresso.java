package id.aldochristiaan.salad.module;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.android.espresso.*;
import id.aldochristiaan.salad.module.android.uiautomator2.Toast;
import id.aldochristiaan.salad.util.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class Espresso extends Mobile {

    protected final AndroidDriver androidDriver;
    private final ValidateValue validateValue = new ValidateValue();
    private final Randomize randomize = new Randomize();
    private final FakerUtil fakerUtil = new FakerUtil();

    public Espresso(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    // === Modules ===
    protected Tap tap() { return new Tap(androidDriver); }
    protected Type type() { return new Type(androidDriver); }
    protected Swipe swipe() { return new Swipe(androidDriver); }
    protected SwipeTo swipeTo() { return new SwipeTo(androidDriver); }
    protected MultipleTap multipleTap() { return new MultipleTap(androidDriver); }
    protected Flash flash() { return new Flash(androidDriver); }
    protected GetElement getElement() { return new GetElement(androidDriver); }
    protected GetMultipleElement getMultipleElement() { return new GetMultipleElement(androidDriver); }
    protected Toast toast() { return new Toast(androidDriver); }
    protected Drawer drawer() { return new Drawer(androidDriver); }
    protected Navigate navigate() { return new Navigate(androidDriver); }
    protected ViewPager viewPager() { return new ViewPager(androidDriver); }
    protected ValidateToast validateToast() { return new ValidateToast(androidDriver); }
    protected WebAtoms webAtoms() { return new WebAtoms(androidDriver); }
    protected UiAutomator uiAutomator() { return new UiAutomator(androidDriver); }


    // === Validation ===
    protected boolean isElementExist(String locator) {
        try {
            androidDriver.findElement(getLocator(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean getBooleanAttribute(String locator, String attribute) {
        if (!isElementExist(locator)) throw new NoSuchElementException("Element not found: " + locator);
        return Boolean.parseBoolean(androidDriver.findElement(getLocator(locator)).getAttribute(attribute));
    }

    protected void validateElementVisible(String locator) {
        validateValue.equalsTrue(getBooleanAttribute(locator, "visible"), "Element not visible: " + locator);
    }

    protected void validateElementText(String locator, String expected, boolean exactMatch) {
        if (!isElementExist(locator)) throw new NoSuchElementException("Element not found: " + locator);
        String actual = getText(locator);
        if (exactMatch) validateValue.equals(expected, actual);
        else validateValue.contains(expected, actual);
    }

    protected void validateEnabled(String locator, String msg) {
        validateValue.equalsTrue(getBooleanAttribute(locator, "enabled"), msg);
    }

    protected void validateDisabled(String locator, String msg) {
        validateValue.equalsFalse(getBooleanAttribute(locator, "enabled"), msg);
    }

    protected void validateSelected(String locator, String msg) {
        validateValue.equalsTrue(getBooleanAttribute(locator, "selected"), msg);
    }

    protected void validateNotSelected(String locator, String msg) {
        validateValue.equalsFalse(getBooleanAttribute(locator, "selected"), msg);
    }

    protected void validateChecked(String locator, String msg) {
        validateValue.equalsTrue(getBooleanAttribute(locator, "checked"), msg);
    }

    protected void validateExist(String locator, String msg) {
        validateValue.equalsTrue(isElementExist(locator), msg);
    }

    protected void validateNotExist(String locator, String msg) {
        validateValue.equalsFalse(isElementExist(locator), msg);
    }

    protected void validateDisplayed(String locator, int timeout, String msg) {
        validateValue.equalsTrue(isElementDisplayed(locator, timeout), msg);
    }

    protected void validateNotDisplayed(String locator, int timeout, String msg) {
        validateValue.equalsFalse(isElementDisplayed(locator, timeout), msg);
    }

    protected void validateStaleness(WebElement element, int timeout) {
        validateValue.equalsTrue(new WebDriverWait(androidDriver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.stalenessOf(element)));
    }

    // === Element Display Polling ===
    protected boolean isElementDisplayed(String locator, int timeout) {
        return waitForElementPosition(locator, -1, timeout);
    }

    protected boolean isElementDisplayed(String locator, int index, int timeout) {
        return waitForElementPosition(locator, index, timeout);
    }

    private boolean waitForElementPosition(String locator, int index, int timeout) {
        int screenHeight = androidDriver.manage().window().getSize().getHeight();
        for (int i = 0; i < timeout * 5; i++) {
            try {
                int y = (index >= 0)
                        ? androidDriver.findElements(getLocator(locator)).get(index).getLocation().getY()
                        : androidDriver.findElement(getLocator(locator)).getLocation().getY();
                return screenHeight >= y;
            } catch (Exception e) {
                delay(200);
            }
        }
        return false;
    }

    // === Text & Attribute ===
    protected String getText(String locator) {
        return getText(locator, -1);
    }

    protected String getText(String locator, int index) {
        try {
            return index < 0
                    ? androidDriver.findElement(getLocator(locator)).getText()
                    : androidDriver.findElements(getLocator(locator)).get(index).getText();
        } catch (Exception e) {
            throw new RuntimeException("Error getting text from: " + locator, e);
        }
    }

    protected String getElementAttributeValue(String locator, String attribute) {
        if (!isElementExist(locator)) throw new NoSuchElementException("Element not found: " + locator);
        return androidDriver.findElement(getLocator(locator)).getAttribute(attribute);
    }

    // === Gestures (W3C Actions) ===
    protected void tapAt(int x, int y, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, duration));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(Collections.singletonList(tap));
    }

    protected void swipe(int xStart, int yStart, int xEnd, int yEnd, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), xStart, yStart));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), xEnd, yEnd));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(Collections.singletonList(swipe));
    }

    // === System Actions ===
    protected void pressBackButton() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    protected void pressEnterButton() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    protected void pressSearchButton() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER)
                .withFlag(KeyEventFlag.SOFT_KEYBOARD)
                .withFlag(KeyEventFlag.KEEP_TOUCH_MODE)
                .withFlag(KeyEventFlag.EDITOR_ACTION));
    }

    protected void hideKeyboard() {
        try {
            androidDriver.hideKeyboard();
        } catch (Exception e) {
            LogUtil.info("No visible keyboard.");
        }
    }

    protected void openDeeplink(String url) {
        androidDriver.get(url);
    }

    // === Screenshot ===
    protected void takeScreenshot(String name) {
        takeScreenshot("screenshot", name);
    }

    protected void takeScreenshot(String path, String name) {
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        File imageFile = new File(dir, name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(scrFile), imageFile);
            LogUtil.info("Screenshot saved: " + imageFile.getAbsolutePath());
        } catch (IOException e) {
            LogUtil.error("Failed to save screenshot: " + e.getMessage());
        }
    }

    // === Espresso Backdoor ===
    protected Object backdoor(ImmutableMap<String, Object> args) {
        return androidDriver.executeScript("mobile: backdoor", args);
    }

    // === Utilities ===
    protected ValidateValue validateValue() { return validateValue; }
    protected Randomize randomize() { return randomize; }
    protected FakerUtil fakerUtil() { return fakerUtil; }
}
