package id.aldochristiaan.salad.module;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.android.espresso.*;
import id.aldochristiaan.salad.module.android.uiautomator2.Toast;
import id.aldochristiaan.salad.util.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;

/**
 * Espresso automation module with lazy initialization
 */
public class Espresso extends Mobile {

    protected final AndroidDriver androidDriver;
    private final ElementValidator elementValidator;

    // Lazy-initialized modules
    private Tap tapModule;
    private Type typeModule;
    private Swipe swipeModule;
    private SwipeTo swipeToModule;
    private MultipleTap multipleTapModule;
    private Flash flashModule;
    private GetElement getElementModule;
    private GetMultipleElement getMultipleElementModule;
    private Toast toastModule;
    private Drawer drawerModule;
    private Navigate navigateModule;
    private ViewPager viewPagerModule;
    private ValidateToast validateToastModule;
    private WebAtoms webAtomsModule;
    private UiAutomator uiAutomatorModule;

    // Lazy-initialized utilities
    private ValidateValue validateValueUtil;
    private Randomize randomizeUtil;
    private FakerUtil fakerUtilInstance;

    public Espresso(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        this.elementValidator = new ElementValidator();
    }

    // === Modules (Lazy Initialization) ===
    protected Tap tap() {
        if (tapModule == null) {
            tapModule = new Tap(androidDriver);
        }
        return tapModule;
    }

    protected Type type() {
        if (typeModule == null) {
            typeModule = new Type(androidDriver);
        }
        return typeModule;
    }

    protected Swipe swipe() {
        if (swipeModule == null) {
            swipeModule = new Swipe(androidDriver);
        }
        return swipeModule;
    }

    protected SwipeTo swipeTo() {
        if (swipeToModule == null) {
            swipeToModule = new SwipeTo(androidDriver);
        }
        return swipeToModule;
    }

    protected MultipleTap multipleTap() {
        if (multipleTapModule == null) {
            multipleTapModule = new MultipleTap(androidDriver);
        }
        return multipleTapModule;
    }

    protected Flash flash() {
        if (flashModule == null) {
            flashModule = new Flash(androidDriver);
        }
        return flashModule;
    }

    protected GetElement getElement() {
        if (getElementModule == null) {
            getElementModule = new GetElement(androidDriver);
        }
        return getElementModule;
    }

    protected GetMultipleElement getMultipleElement() {
        if (getMultipleElementModule == null) {
            getMultipleElementModule = new GetMultipleElement(androidDriver);
        }
        return getMultipleElementModule;
    }

    protected Toast toast() {
        if (toastModule == null) {
            toastModule = new Toast(androidDriver);
        }
        return toastModule;
    }

    protected Drawer drawer() {
        if (drawerModule == null) {
            drawerModule = new Drawer(androidDriver);
        }
        return drawerModule;
    }

    protected Navigate navigate() {
        if (navigateModule == null) {
            navigateModule = new Navigate(androidDriver);
        }
        return navigateModule;
    }

    protected ViewPager viewPager() {
        if (viewPagerModule == null) {
            viewPagerModule = new ViewPager(androidDriver);
        }
        return viewPagerModule;
    }

    protected ValidateToast validateToast() {
        if (validateToastModule == null) {
            validateToastModule = new ValidateToast(androidDriver);
        }
        return validateToastModule;
    }

    protected WebAtoms webAtoms() {
        if (webAtomsModule == null) {
            webAtomsModule = new WebAtoms(androidDriver);
        }
        return webAtomsModule;
    }

    protected UiAutomator uiAutomator() {
        if (uiAutomatorModule == null) {
            uiAutomatorModule = new UiAutomator(androidDriver);
        }
        return uiAutomatorModule;
    }


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
        if (!isElementExist(locator)) {
            throw new NoSuchElementException("Element not found: " + locator);
        }
        WebElement element = androidDriver.findElement(getLocator(locator));
        return DriverUtils.getBooleanAttribute(element, attribute);
    }

    protected void validateElementVisible(String locator) {
        elementValidator.validateVisible(
            androidDriver.findElement(getLocator(locator)),
            locator
        );
    }

    protected void validateElementText(String locator, String expected, boolean exactMatch) {
        if (!isElementExist(locator)) {
            throw new NoSuchElementException("Element not found: " + locator);
        }
        String actual = getText(locator);
        elementValidator.validateText(expected, actual, exactMatch, locator);
    }

    protected void validateEnabled(String locator, String msg) {
        WebElement element = androidDriver.findElement(getLocator(locator));
        elementValidator.validateEnabled(element, msg);
    }

    protected void validateDisabled(String locator, String msg) {
        WebElement element = androidDriver.findElement(getLocator(locator));
        elementValidator.validateDisabled(element, msg);
    }

    protected void validateSelected(String locator, String msg) {
        WebElement element = androidDriver.findElement(getLocator(locator));
        elementValidator.validateSelected(element, msg);
    }

    protected void validateNotSelected(String locator, String msg) {
        WebElement element = androidDriver.findElement(getLocator(locator));
        elementValidator.validateNotSelected(element, msg);
    }

    protected void validateChecked(String locator, String msg) {
        WebElement element = androidDriver.findElement(getLocator(locator));
        elementValidator.validateChecked(element, msg);
    }

    protected void validateExist(String locator, String msg) {
        elementValidator.validateExist(isElementExist(locator), msg);
    }

    protected void validateNotExist(String locator, String msg) {
        elementValidator.validateNotExist(isElementExist(locator), msg);
    }

    protected void validateDisplayed(String locator, int timeout, String msg) {
        elementValidator.getValidateValue().equalsTrue(
            isElementDisplayed(locator, timeout),
            msg
        );
    }

    protected void validateNotDisplayed(String locator, int timeout, String msg) {
        elementValidator.getValidateValue().equalsFalse(
            isElementDisplayed(locator, timeout),
            msg
        );
    }

    protected void validateStaleness(WebElement element, int timeout) {
        elementValidator.validateStaleness(androidDriver, element, timeout);
    }

    // === Element Display Polling ===
    protected boolean isElementDisplayed(String locator, int timeout) {
        return waitForElementPosition(locator, -1, timeout);
    }

    protected boolean isElementDisplayed(String locator, int index, int timeout) {
        return waitForElementPosition(locator, index, timeout);
    }

    private boolean waitForElementPosition(String locator, int index, int timeout) {
        DriverUtils.Dimension dimension = DriverUtils.getScreenDimension(androidDriver);
        int screenHeight = dimension.getHeight();
        int iterations = timeout * SaladConfig.POLLING_MULTIPLIER;

        for (int i = 0; i < iterations; i++) {
            try {
                int y = getElementYPosition(locator, index);
                return screenHeight >= y;
            } catch (Exception e) {
                delay(SaladConfig.DEFAULT_POLLING_INTERVAL_MS);
            }
        }
        return false;
    }

    private int getElementYPosition(String locator, int index) {
        return (index >= 0)
            ? androidDriver.findElements(getLocator(locator)).get(index).getLocation().getY()
            : androidDriver.findElement(getLocator(locator)).getLocation().getY();
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
        takeScreenshot(SaladConfig.DEFAULT_SCREENSHOT_DIR, name);
    }

    protected void takeScreenshot(String path, String name) {
        File dir = new File(path);
        if (!dir.exists() && !dir.mkdirs()) {
            LogUtil.error("Failed to create screenshot directory: " + path);
            return;
        }

        try {
            File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
            File imageFile = new File(dir, name + SaladConfig.SCREENSHOT_EXTENSION);
            FileUtils.copyFile(Objects.requireNonNull(scrFile), imageFile);
            LogUtil.info("Screenshot saved: " + imageFile.getAbsolutePath());
        } catch (IOException e) {
            LogUtil.error("Failed to save screenshot: " + e.getMessage(), e);
        }
    }

    // === Espresso Backdoor ===
    protected Object backdoor(ImmutableMap<String, Object> args) {
        return androidDriver.executeScript("mobile: backdoor", args);
    }

    // === Utilities ===
    protected ValidateValue validateValue() {
        if (validateValueUtil == null) {
            validateValueUtil = new ValidateValue();
        }
        return validateValueUtil;
    }

    protected Randomize randomize() {
        if (randomizeUtil == null) {
            randomizeUtil = new Randomize();
        }
        return randomizeUtil;
    }

    protected FakerUtil fakerUtil() {
        if (fakerUtilInstance == null) {
            fakerUtilInstance = FakerUtil.getInstance();
        }
        return fakerUtilInstance;
    }
}
