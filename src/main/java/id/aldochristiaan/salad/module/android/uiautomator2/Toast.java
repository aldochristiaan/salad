package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.config.SaladConfig;
import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Android toast message utilities.
 * Provides methods for interacting with and validating toast messages.
 */
public class Toast extends UiAutomator2 {

    private static final By TOAST_LOCATOR = By.xpath("//android.widget.Toast[1]");

    public Toast(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Get the text of the currently displayed toast message
     * Uses default timeout from SaladConfig
     * @return Toast message text
     * @throws TimeoutException if no toast appears within timeout
     */
    public String getText() {
        return getText(SaladConfig.DEFAULT_TIMEOUT);
    }

    /**
     * Get the text of the currently displayed toast message with custom timeout
     * @param timeoutSeconds Maximum time to wait for toast in seconds
     * @return Toast message text
     * @throws TimeoutException if no toast appears within timeout
     */
    public String getText(int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(TOAST_LOCATOR));
            WebElement toastView = androidDriver.findElement(TOAST_LOCATOR);
            String toastText = toastView.getAttribute("text");
            LogUtil.info("Toast message found: " + toastText);
            return toastText;
        } catch (TimeoutException e) {
            LogUtil.error("No toast message appeared within " + timeoutSeconds + " seconds");
            throw e;
        }
    }

    /**
     * Check if a toast is currently visible
     * @return true if toast is visible, false otherwise
     */
    public boolean isVisible() {
        try {
            androidDriver.findElement(TOAST_LOCATOR);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for a toast to appear
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return true if toast appeared, false otherwise
     */
    public boolean waitForToast(int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(TOAST_LOCATOR));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
