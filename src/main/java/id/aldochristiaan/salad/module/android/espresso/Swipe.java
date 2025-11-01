package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class Swipe extends Espresso {

    public Swipe(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void element(
            String elementLocator,
            SwipeSpeed swipeSpeed,
            Coordinates startCoordinates,
            Coordinates endCoordinates,
            PrecisionDescriber precisionDescriber) {
        performSwipe(elementLocator, swipeSpeed, startCoordinates, endCoordinates, precisionDescriber, 1);
    }

    public void element(
            String elementLocator,
            SwipeSpeed swipeSpeed,
            Coordinates startCoordinates,
            Coordinates endCoordinates,
            PrecisionDescriber precisionDescriber,
            int iteration) {
        performSwipe(elementLocator, swipeSpeed, startCoordinates, endCoordinates, precisionDescriber, iteration);
    }

    private void performSwipe(
            String elementLocator,
            SwipeSpeed swipeSpeed,
            Coordinates startCoordinates,
            Coordinates endCoordinates,
            PrecisionDescriber precisionDescriber,
            int iteration) {

        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "swiper", swipeSpeed.toString(),
                "startCoordinates", startCoordinates.toString(),
                "endCoordinates", endCoordinates.toString(),
                "precisionDescriber", precisionDescriber.toString()
        );

        for (int i = 0; i < iteration; i++) {
            androidDriver.executeScript("mobile:swipe", args);
        }
    }
}
