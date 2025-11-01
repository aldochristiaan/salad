package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Toast extends UiAutomator2 {

    private static final By TOAST_LOCATOR = By.xpath("//android.widget.Toast[1]");
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public Toast(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public String getText() {
        WebDriverWait wait = new WebDriverWait(androidDriver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(TOAST_LOCATOR));
        WebElement toastView = androidDriver.findElement(TOAST_LOCATOR);
        return toastView.getAttribute("text"); // Use "text" instead of "name" for Android
    }
}
