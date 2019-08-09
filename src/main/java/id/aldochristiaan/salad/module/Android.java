package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.module.android.*;
import id.aldochristiaan.salad.module.general.Randomize;
import id.aldochristiaan.salad.module.general.ValidateValue;
import id.aldochristiaan.salad.util.ChangeContext;
import id.aldochristiaan.salad.util.Direction;
import id.aldochristiaan.salad.util.FakerUtil;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class Android extends Mobile {

    protected AndroidDriver<AndroidElement> androidDriver;

    public Android(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    protected CheckElement checkElement() {
        return new CheckElement(androidDriver);
    }

    protected GetElement getElement() {
        return new GetElement(androidDriver);
    }

    protected GetMultipleElement getMultipleElement() {
        return new GetMultipleElement(androidDriver);
    }

    protected GetTextFromElement getTextFromElement() {
        return new GetTextFromElement(androidDriver);
    }

    protected LongTapElement longTapElement() {
        return new LongTapElement(androidDriver);
    }

    protected Swipe swipe() {
        return new Swipe(androidDriver);
    }

    protected TapElement tapElement() {
        return new TapElement(androidDriver);
    }

    protected ValidateElement validateElement() {
        return new ValidateElement(androidDriver);
    }

    protected ValidateValue validateValue() {
        return new ValidateValue();
    }

    protected TypeText typeText() {
        return new TypeText(androidDriver);
    }

    protected ChangeContext changeContext() {
        return new ChangeContext(androidDriver);
    }

    protected Toast toast() {
        return new Toast(androidDriver);
    }

    protected Randomize randomize() {
        return new Randomize();
    }

    protected FakerUtil fakerUtil() {
        return new FakerUtil();
    }

    protected Deeplink deeplink() {
        return new Deeplink(androidDriver);
    }

    protected EspressoSwipe espressoSwipe() {
        return new EspressoSwipe(androidDriver);
    }

    protected Drawer drawer() {
        return new Drawer(androidDriver);
    }

    protected Flash flash() {
        return new Flash(androidDriver);
    }

    protected ViewPager viewPager() {
        return new ViewPager(androidDriver);
    }

    protected Navigate navigate() {
        return new Navigate(androidDriver);
    }

    protected ValidateToast validateToast() {
        return new ValidateToast(androidDriver);
    }

    protected UiAutomator uiAutomator() {
        return new UiAutomator(androidDriver);
    }

    protected Espresso espresso() {
        return new Espresso(androidDriver);
    }

    protected AndroidElement findElementBy(By by) {
        AndroidElement element = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                element = androidDriver.findElement(by);
                if (checkElement().isPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipe().up();
            }
        }
        if (element == null) {
            throw new NoSuchElementException("Couldn't find this element : " + by.toString());
        }
        return element;
    }

    protected AndroidElement findElementBy(By by, Direction direction) {
        AndroidElement element = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                element = androidDriver.findElement(by);
                if (checkElement().isPresent(element)) break;
            } catch (NoSuchElementException e) {
                swipe().toDirection(direction);
            }
        }
        if (element == null) {
            throw new NoSuchElementException("Couldn't find this element : " + by.toString());
        }
        return element;
    }

    protected AndroidElement findElementBy(By by, int timeout) {
        return (AndroidElement) (new WebDriverWait(androidDriver, timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<AndroidElement> findElementsBy(By by) {
        List<AndroidElement> elements = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                elements = androidDriver.findElements(by);
                if (checkElement().isPresent(elements.get(0))) break;
            } catch (NoSuchElementException e) {
                swipe().up();
            }
        }
        if (elements == null) {
            throw new NoSuchElementException("Couldn't find this element : " + by.toString());
        }
        return elements;
    }

    protected List<AndroidElement> findElementsBy(By by, Direction direction) {
        List<AndroidElement> elements = null;
        for (int i = 0; i < MAX_SWIPE_COUNT; i++) {
            try {
                elements = androidDriver.findElements(by);
                if (checkElement().isPresent(elements.get(0))) break;
            } catch (NoSuchElementException e) {
                swipe().toDirection(direction);
            }
        }
        if (elements == null) {
            throw new NoSuchElementException("Couldn't find this element : " + by.toString());
        }
        return elements;
    }

    protected List<WebElement> findElementsBy(By by, int timeout) {
        return (new WebDriverWait(androidDriver, timeout))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
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
}
