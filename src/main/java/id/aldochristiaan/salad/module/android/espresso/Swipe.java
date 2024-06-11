package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

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
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", webElement);
        args.put("swiper", swipeSpeed.toString());
        args.put("startCoordinates", startCoordinates.toString());
        args.put("endCoordinates", endCoordinates.toString());
        args.put("precisionDescriber", precisionDescriber.toString());
        androidDriver.executeScript("mobile:swipe", args);
    }

    public void element(
            String elementLocator,
            SwipeSpeed swipeSpeed,
            Coordinates startCoordinates,
            Coordinates endCoordinates,
            PrecisionDescriber precisionDescriber,
            int iteration) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", webElement);
        args.put("swiper", swipeSpeed.toString());
        args.put("startCoordinates", startCoordinates.toString());
        args.put("endCoordinates", endCoordinates.toString());
        args.put("precisionDescriber", precisionDescriber.toString());
        for (int i = 0; i < iteration; i++) {
            androidDriver.executeScript("mobile:swipe", args);
        }
    }
}
