package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Type extends UiAutomator2 {

    public Type(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String text) {
        typeInto(findElementBy(getLocator(elementLocator)), text);
    }

    public void element(String elementLocator, int index, String text) {
        List<WebElement> elements = findElementsBy(getLocator(elementLocator));
        if (index < elements.size()) {
            typeInto(elements.get(index), text);
        }
    }

    public void pendingElement(String elementLocator, int timeout, String text) {
        typeInto(findElementBy(getLocator(elementLocator), timeout), text);
    }

    public void pendingElement(String elementLocator, int timeout, int index, String text) {
        List<WebElement> elements = findElementsBy(getLocator(elementLocator), timeout);
        if (index < elements.size()) {
            typeInto(elements.get(index), text);
        }
    }

    private void typeInto(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
        hideKeyboard();
    }
}
