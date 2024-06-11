package id.aldochristiaan.salad.module.android.uiautomator2;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;

public class MobileGesture extends UiAutomator2 {

    public MobileGesture(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void tap(String elementLocator) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId()
        ));
    }

    public void doubleTap(String elementLocator) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId()
        ));
    }

    public void longTap(String elementLocator) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId()
        ));
    }

    public void pinchOpen(String elementLocator) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "percent", 0.75
        ));
    }

    public void pinchOpen(String elementLocator, double percent) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "percent", percent
        ));
    }

    public void pinchClose(String elementLocator) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "percent", 0.75
        ));
    }

    public void pinchClose(String elementLocator, double percent) {
        ((JavascriptExecutor) androidDriver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "percent", percent
        ));
    }

    public void swipe(Direction direction, double xStart, double yStart, double percent) {

        Dimension size = androidDriver.manage().window().getSize();

        int x = (int) (size.width * xStart);
        int y = (int) (size.height * yStart);

        ((JavascriptExecutor) androidDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", x, "top", y, "width", 200, "height", 200,
                "direction", direction.toString(),
                "percent", percent
        ));
    }

    public void swipe(Direction direction, double xStart, double yStart, int width, int height, double percent) {

        Dimension size = androidDriver.manage().window().getSize();

        int x = (int) (size.width * xStart);
        int y = (int) (size.height * yStart);

        ((JavascriptExecutor) androidDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", x, "top", y, "width", width, "height", height,
                "direction", direction.toString(),
                "percent", percent
        ));
    }

    public boolean scroll(String elementLocator, Direction direction, double xStart, double yStart, double percent) {
        Dimension size = androidDriver.manage().window().getSize();

        int x = (int) (size.width * xStart);
        int y = (int) (size.height * yStart);

        return (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "left", x, "top", y, "width", 200, "height", 200,
                "direction", direction.toString(),
                "percent", percent
        ));
    }

    public boolean scroll(Direction direction, double xStart, double yStart, double percent) {
        Dimension size = androidDriver.manage().window().getSize();

        int x = (int) (size.width * xStart);
        int y = (int) (size.height * yStart);

        return (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", x, "top", y, "width", 200, "height", 200,
                "direction", direction.toString(),
                "percent", percent
        ));
    }

    public boolean scroll(Direction direction, double xStart, double yStart, double width, double height, double percent) {
        Dimension size = androidDriver.manage().window().getSize();

        int x = (int) (size.width * xStart);
        int y = (int) (size.height * yStart);

        return (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", x, "top", y, "width", width, "height", height,
                "direction", direction.toString(),
                "percent", percent
        ));
    }

    public void drag(String elementLocator, double xEnd, double yEnd) {

        Dimension size = androidDriver.manage().window().getSize();

        int x = (int) (size.width * xEnd);
        int y = (int) (size.height * yEnd);

        ((JavascriptExecutor) androidDriver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "endX", x,
                "endY", y
        ));
    }

    public void drag(String elementLocator, double xStart, double xEnd, double yStart, double yEnd) {

        Dimension size = androidDriver.manage().window().getSize();

        int x0 = (int) (size.width * xStart);
        int x1 = (int) (size.width * xEnd);
        int y0 = (int) (size.height * yStart);
        int y1 = (int) (size.height * yEnd);

        ((JavascriptExecutor) androidDriver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "startX", x0,
                "startY", y0,
                "endX", x1,
                "endY", y1
        ));
    }

    public boolean fling(String elementLocator, Direction direction) {
        return (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: flingGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "direction", direction.toString(),
                "speed", 500
        ));
    }

    public boolean fling(String elementLocator, Direction direction, int speed) {
        return (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: flingGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) androidDriver.findElement(getLocator(elementLocator))).getId(),
                "direction", direction.toString(),
                "speed", speed
        ));
    }
}
