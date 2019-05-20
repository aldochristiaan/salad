package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckElement extends Ios {

    public CheckElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public boolean isPresent(IOSElement androidElement) {
        try {
            WebDriverWait wait = new WebDriverWait(iosDriver, 10);
            wait.until(ExpectedConditions.visibilityOf(androidElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPresentByLocator(String elementLocator, int timeout) {
        By by = getLocator(elementLocator);
        try {
            WebDriverWait wait = new WebDriverWait(iosDriver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            LogUtil.info("Element with locator : " + elementLocator + " is not present!");
            return false;
        }
    }

    public boolean isNotPresentByLocator(String elementLocator, int timeout) {
        By by = getLocator(elementLocator);
        try {
            WebDriverWait wait = new WebDriverWait(iosDriver, timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            LogUtil.info("Element with locator : " + elementLocator + " is still present!");
            return false;
        }
    }
}
