package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.module.general.Randomize;
import id.aldochristiaan.salad.module.general.ValidateValue;
import id.aldochristiaan.salad.module.ios.*;
import id.aldochristiaan.salad.util.Direction;
import id.aldochristiaan.salad.util.FakerUtil;
import id.aldochristiaan.salad.util.LogUtil;
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

public class Ios extends Mobile {

    protected IOSDriver<IOSElement> iosDriver;

    public Ios(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
    }

    protected CheckElement checkElement() {
        return new CheckElement(iosDriver);
    }

    protected GetElement getElement() {
        return new GetElement(iosDriver);
    }

    protected GetMultipleElement getMultipleElement() {
        return new GetMultipleElement(iosDriver);
    }

    protected GetTextFromElement getTextFromElement() {
        return new GetTextFromElement(iosDriver);
    }

    protected LongTapElement longTapElement() {
        return new LongTapElement(iosDriver);
    }

    protected Swipe swipe() {
        return new Swipe(iosDriver);
    }

    protected TapElement tapElement() {
        return new TapElement(iosDriver);
    }

    protected ValidateElement validateElement() {
        return new ValidateElement(iosDriver);
    }

    protected ValidateValue validateValue() {
        return new ValidateValue();
    }

    protected TypeText typeText() {
        return new TypeText(iosDriver);
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
                if (checkElement().isPresent(element)) break;
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
                if (checkElement().isPresent(element)) break;
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
                if (checkElement().isPresent(elements.get(0))) break;
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
                if (checkElement().isPresent(elements.get(0))) break;
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
