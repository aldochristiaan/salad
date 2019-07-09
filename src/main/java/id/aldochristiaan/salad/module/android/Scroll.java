package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.SwipeSpeed;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class Scroll extends Android {

    public Scroll(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void element(
            String elementLocator,
            SwipeSpeed swipeSpeed,
            Coordinates startCoordinates,
            Coordinates endCoordinates,
            PrecisionDescriber precisionDescriber) {
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
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
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("swiper", swipeSpeed.toString());
        args.put("startCoordinates", startCoordinates.toString());
        args.put("endCoordinates", endCoordinates.toString());
        args.put("precisionDescriber", precisionDescriber.toString());
        for (int i = 0; i < iteration; i++) {
            androidDriver.executeScript("mobile:swipe", args);
        }
    }
}
