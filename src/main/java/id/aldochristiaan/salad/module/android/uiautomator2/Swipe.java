package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class Swipe extends UiAutomator2 {

    public Swipe(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void to(double xStart, double xEnd, double yStart, double yEnd) {
        Dimension size = androidDriver.manage().window().getSize();
        int x0 = (int) (size.width * xStart);
        int x1 = (int) (size.width * xEnd);
        int y0 = (int) (size.height * yStart);
        int y1 = (int) (size.height * yEnd);
        performSwipe(x0, y0, x1, y1, Duration.ofMillis(500));
    }

    public void toDirection(Direction direction) {
        Dimension size = androidDriver.manage().window().getSize();
        int x = size.width / 2;
        int y = size.height / 2;
        int offset = 300;

        switch (direction) {
            case UP -> performSwipe(x, y + offset, x, y - offset, Duration.ofMillis(500));
            case DOWN -> performSwipe(x, y - offset, x, y + offset, Duration.ofMillis(500));
            case LEFT -> performSwipe(x + offset, y, x - offset, y, Duration.ofMillis(500));
            case RIGHT -> performSwipe(x - offset, y, x + offset, y, Duration.ofMillis(500));
        }
    }

    private void performSwipe(int xStart, int yStart, int xEnd, int yEnd, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipeSequence = new Sequence(finger, 1);
        swipeSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), xStart, yStart));
        swipeSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), xEnd, yEnd));
        swipeSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(List.of(swipeSequence));
    }
}
