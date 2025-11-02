package id.aldochristiaan.salad.module.android.uiautomator2;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class MobileGesture extends UiAutomator2 {

    public MobileGesture(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void tap(String elementLocator) {
        executeGesture("clickGesture", ImmutableMap.of("elementId", getElementId(elementLocator)));
    }

    public void doubleTap(String elementLocator) {
        executeGesture("doubleClickGesture", ImmutableMap.of("elementId", getElementId(elementLocator)));
    }

    public void longTap(String elementLocator) {
        executeGesture("longClickGesture", ImmutableMap.of("elementId", getElementId(elementLocator)));
    }

    public void pinchOpen(String elementLocator) {
        pinchOpen(elementLocator, 0.75);
    }

    public void pinchOpen(String elementLocator, double percent) {
        executeGesture("pinchOpenGesture", ImmutableMap.of(
                "elementId", getElementId(elementLocator),
                "percent", percent
        ));
    }

    public void pinchClose(String elementLocator) {
        pinchClose(elementLocator, 0.75);
    }

    public void pinchClose(String elementLocator, double percent) {
        executeGesture("pinchCloseGesture", ImmutableMap.of(
                "elementId", getElementId(elementLocator),
                "percent", percent
        ));
    }

    public void swipe(Direction direction, double xStart, double yStart, double percent) {
        swipe(direction, xStart, yStart, 200, 200, percent);
    }

    public void swipe(Direction direction, double xStart, double yStart, int width, int height, double percent) {
        Map<String, Object> area = getArea(xStart, yStart, width, height);
        area.put("direction", direction.toString());
        area.put("percent", percent);
        executeGesture("swipeGesture", area);
    }

    public boolean scroll(String elementLocator, Direction direction, double xStart, double yStart, double percent) {
        return scroll(elementLocator, direction, xStart, yStart, 200, 200, percent);
    }

    public boolean scroll(Direction direction, double xStart, double yStart, double percent) {
        return scroll(null, direction, xStart, yStart, 200, 200, percent);
    }

    public boolean scroll(Direction direction, double xStart, double yStart, double width, double height, double percent) {
        return scroll(null, direction, xStart, yStart, width, height, percent);
    }

    private boolean scroll(String elementLocator, Direction direction, double xStart, double yStart, double width, double height, double percent) {
        Map<String, Object> area = getArea(xStart, yStart, width, height);
        area.put("direction", direction.toString());
        area.put("percent", percent);
        if (elementLocator != null) {
            area.put("elementId", getElementId(elementLocator));
        }
        return (Boolean) executeGesture("scrollGesture", area);
    }

    public void drag(String elementLocator, double xEnd, double yEnd) {
        Dimension size = androidDriver.manage().window().getSize();
        executeGesture("dragGesture", ImmutableMap.of(
                "elementId", getElementId(elementLocator),
                "endX", (int) (size.width * xEnd),
                "endY", (int) (size.height * yEnd)
        ));
    }

    public void drag(String elementLocator, double xStart, double xEnd, double yStart, double yEnd) {
        Dimension size = androidDriver.manage().window().getSize();
        executeGesture("dragGesture", ImmutableMap.of(
                "elementId", getElementId(elementLocator),
                "startX", (int) (size.width * xStart),
                "startY", (int) (size.height * yStart),
                "endX", (int) (size.width * xEnd),
                "endY", (int) (size.height * yEnd)
        ));
    }

    public boolean fling(String elementLocator, Direction direction) {
        return fling(elementLocator, direction, 500);
    }

    public boolean fling(String elementLocator, Direction direction, int speed) {
        return (Boolean) executeGesture("flingGesture", ImmutableMap.of(
                "elementId", getElementId(elementLocator),
                "direction", direction.toString(),
                "speed", speed
        ));
    }

    // Utility methods

    private Object executeGesture(String gesture, Map<String, Object> args) {
        return ((JavascriptExecutor) androidDriver).executeScript("mobile:" + gesture, args);
    }

    private String getElementId(String locator) {
        return ((RemoteWebElement) androidDriver.findElement(getLocator(locator))).getId();
    }

    private Map<String, Object> getArea(double xStart, double yStart, double width, double height) {
        Dimension size = androidDriver.manage().window().getSize();
        int x = (int) (size.width * xStart);
        int y = (int) (size.height * yStart);

        Map<String, Object> area = new HashMap<>();
        area.put("left", x);
        area.put("top", y);
        area.put("width", (int) width);
        area.put("height", (int) height);

        return area;
    }
}
