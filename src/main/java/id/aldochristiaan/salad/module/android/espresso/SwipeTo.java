package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class SwipeTo extends Espresso {

    private static final Coordinates DEFAULT_START = Coordinates.CENTER;
    private static final Coordinates DEFAULT_END = Coordinates.TOP_CENTER;
    private static final PrecisionDescriber DEFAULT_PRECISION = PrecisionDescriber.FINGER;

    public SwipeTo(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed) {
        swipeUntilVisible(() -> isElementDisplayed(elementLocator, 1),
                swipeLocator, swipeSpeed, DEFAULT_START, DEFAULT_END);
    }

    public void element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed,
                        Coordinates startCoordinate, Coordinates endCoordinate) {
        swipeUntilVisible(() -> isElementDisplayed(elementLocator, 1),
                swipeLocator, swipeSpeed, startCoordinate, endCoordinate);
    }

    public void element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed) {
        swipeUntilVisible(() -> isElementDisplayed(elementLocator, index, 1),
                swipeLocator, swipeSpeed, DEFAULT_START, DEFAULT_END);
    }

    public void element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed,
                        Coordinates startCoordinate, Coordinates endCoordinate) {
        swipeUntilVisible(() -> isElementDisplayed(elementLocator, index, 1),
                swipeLocator, swipeSpeed, startCoordinate, endCoordinate);
    }

    private void swipeUntilVisible(SwipeCondition condition, String swipeLocator,
                                   SwipeSpeed swipeSpeed, Coordinates start, Coordinates end) {
        for (int swipeCount = 0; !condition.check() && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            swipe().element(swipeLocator, swipeSpeed, start, end, DEFAULT_PRECISION);
        }
    }

    @FunctionalInterface
    private interface SwipeCondition {
        boolean check();
    }
}
