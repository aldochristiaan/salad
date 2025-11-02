package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LongTap extends UiAutomator2 {

    public LongTap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        performLongTap(findElementBy(getLocator(elementLocator)));
    }

    public void element(String elementLocator, int index) {
        List<WebElement> elements = findElementsBy(getLocator(elementLocator));
        if (index < elements.size()) {
            performLongTap(elements.get(index));
        } else {
            throw new IndexOutOfBoundsException("No element at index " + index);
        }
    }

    public void pendingElement(String elementLocator, int timeout) {
        performLongTap(findElementBy(getLocator(elementLocator), timeout));
    }

    public void pendingElement(String elementLocator, int timeout, int index) {
        List<WebElement> elements = findElementsBy(getLocator(elementLocator), timeout);
        if (index < elements.size()) {
            performLongTap(elements.get(index));
        } else {
            throw new IndexOutOfBoundsException("No element at index " + index + " after waiting " + timeout + " seconds");
        }
    }

    private void performLongTap(WebElement element) {
        new TouchAction<>(androidDriver)
                .longPress(ElementOption.element(element))
                .release()
                .perform();
    }
}
