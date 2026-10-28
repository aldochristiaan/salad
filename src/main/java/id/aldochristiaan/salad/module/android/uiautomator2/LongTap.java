package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
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
        // W3C actions: press at the element centre and hold. The legacy /touch/perform
        // endpoint (TouchAction) is not supported by modern Appium drivers.
        Dimension size = element.getSize();
        int x = element.getLocation().x + size.width / 2;
        int y = element.getLocation().y + size.height / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longTap = new Sequence(finger, 1);
        longTap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        longTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longTap.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(1000)));
        longTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(List.of(longTap));
    }
}
