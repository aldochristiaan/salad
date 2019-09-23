package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static id.aldochristiaan.salad.Salad.MAX_SWIPE_COUNT;

public class SwipeTo extends Espresso {

    public SwipeTo(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            swipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
        }
    }

    public void element(String elementLocator, String swipeLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinate, Coordinates endCoordinate) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            swipe().element(swipeLocator, swipeSpeed, startCoordinate, endCoordinate, PrecisionDescriber.FINGER);
        }
    }

    public void element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, index, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            swipe().element(swipeLocator, swipeSpeed, Coordinates.CENTER, Coordinates.TOP_CENTER, PrecisionDescriber.FINGER);
        }
    }

    public void element(String elementLocator, int index, String swipeLocator, SwipeSpeed swipeSpeed, Coordinates startCoordinate, Coordinates endCoordinate) {
        for (int swipeCount = 0; !isElementDisplayed(elementLocator, index, 1) && swipeCount <= MAX_SWIPE_COUNT; ++swipeCount) {
            swipe().element(swipeLocator, swipeSpeed, startCoordinate, endCoordinate, PrecisionDescriber.FINGER);
        }
    }
}
