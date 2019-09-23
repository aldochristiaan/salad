package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Toast extends UiAutomator2 {

    public Toast(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public String getText() {
        WebDriverWait wait = new WebDriverWait(androidDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//android.widget.Toast[1]"))));
        WebElement toastView = androidDriver.findElement(By.xpath("//android.widget.Toast[1]"));
        return toastView.getAttribute("name");
    }
}
