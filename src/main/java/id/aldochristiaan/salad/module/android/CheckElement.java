package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckElement extends Android {

    public CheckElement(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public boolean isPresent(AndroidElement androidElement) {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 10);
            wait.until(ExpectedConditions.visibilityOf(androidElement));
            return true;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            return false;
        }
    }

    public boolean isPresentByLocator(String elementLocator, int timeout) {
        By by = getLocator(elementLocator);
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            LogUtil.error("Element with locator : " + elementLocator + " is not present!");
            return false;
        }
    }

    public boolean isNotPresentByLocator(String elementLocator, int timeout) {
        By by = getLocator(elementLocator);
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            LogUtil.info("Element with locator : " + elementLocator + " is still present!");
            return false;
        }
    }
}
