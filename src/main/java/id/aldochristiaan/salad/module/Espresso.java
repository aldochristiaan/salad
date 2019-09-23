package id.aldochristiaan.salad.module;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.android.espresso.*;
import id.aldochristiaan.salad.util.FakerUtil;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.Randomize;
import id.aldochristiaan.salad.util.ValidateValue;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Espresso extends Mobile {

    protected AndroidDriver<AndroidElement> androidDriver;

    public Espresso(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    protected Tap tap() {
        return new Tap(androidDriver);
    }

    protected MultipleTap multipleTap() {
        return new MultipleTap(androidDriver);
    }

    protected Type type() {
        return new Type(androidDriver);
    }

    protected Swipe swipe() {
        return new Swipe(androidDriver);
    }

    protected SwipeTo swipeTo() {
        return new SwipeTo(androidDriver);
    }

    protected Navigate navigate() {
        return new Navigate(androidDriver);
    }

    protected GetElement getElement() {
        return new GetElement(androidDriver);
    }

    protected GetMultipleElement getMultipleElement() {
        return new GetMultipleElement(androidDriver);
    }

    protected Flash flash() {
        return new Flash(androidDriver);
    }

    protected ValidateToast validateToast() {
        return new ValidateToast(androidDriver);
    }

    protected ValidateValue validateValue() {
        return new ValidateValue();
    }

    protected UiAutomator uiAutomator() {
        return new UiAutomator(androidDriver);
    }

    protected ViewPager viewPager() {
        return new ViewPager(androidDriver);
    }

    protected Randomize randomize() {
        return new Randomize();
    }

    protected FakerUtil fakerUtil() {
        return new FakerUtil();
    }

    public boolean isElementExist(String elementLocator) {
        try {
            androidDriver.findElement(getLocator(elementLocator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(String elementLocator) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("visible"));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, e);
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

    public boolean isElementChecked(String elementLocator) {
        try {
            return Boolean.parseBoolean(androidDriver.findElement(getLocator(elementLocator)).getAttribute("checked"));
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

    public String getElementAttributeValue(String elementLocator, String attribute) {
        if (isElementExist(elementLocator)) {
            return androidDriver.findElement(getLocator(elementLocator)).getAttribute(attribute);
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    public void validateElementVisible(String elementLocator) {
        if (isElementExist(elementLocator)) {
            validateValue().equalsTrue(isElementVisible(elementLocator));
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    public void validateElementWithText(String elementLocator, String text) {
        if (isElementExist(elementLocator)) {
            validateValue().equals(text, getText(elementLocator));
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
        }
    }

    public void validateElementContainsText(String elementLocator, String text) {
        if (isElementExist(elementLocator)) {
            validateValue().contains(text, getText(elementLocator));
        } else {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator);
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

    public void validateChecked(String elementLocator, String errorMessage) {
        validateValue().equalsTrue(isElementChecked(elementLocator), errorMessage);
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

    protected void takeScreenshot(String name) {
        File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
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
        File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        File imageFile = new File(path + "/" + name + ".png");
        try {
            FileUtils.copyFile(Objects.requireNonNull(scrFile), imageFile);
            LogUtil.info("Screenshot taken!");
        } catch (IOException e) {
            LogUtil.error("Failed to take screenshot!");
        }
    }

    protected void pressBackButton() {
        androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    protected void pressEnterButton() {
        androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
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
            LogUtil.info("No visible keyboard!");
        }
    }

    protected void openDeeplink(String deeplink) {
        androidDriver.get(deeplink);
    }
}
