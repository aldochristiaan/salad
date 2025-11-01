package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Tap extends UiAutomator2 {

    public Tap(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator) {
        findElementBy(getLocator(elementLocator)).click();
    }

    public void element(String elementLocator, int index) {
        List<WebElement> elements = findElementsBy(getLocator(elementLocator));
        if (index < elements.size()) {
            elements.get(index).click();
        }
    }

    public void pendingElement(String elementLocator, int timeout) {
        findElementBy(getLocator(elementLocator), timeout).click();
    }

    public void pendingElement(String elementLocator, int timeout, int index) {
        List<WebElement> elements = findElementsBy(getLocator(elementLocator), timeout);
        if (index < elements.size()) {
            elements.get(index).click();
        }
    }

    public void tapAt(int x, int y, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tapSequence = new Sequence(finger, 1);
        tapSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tapSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tapSequence.addAction(new Pause(finger, duration)); // Correct way to pause
        tapSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(Collections.singletonList(tapSequence));
    }

}
