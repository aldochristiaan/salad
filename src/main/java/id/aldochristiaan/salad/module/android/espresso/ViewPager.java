package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.ScrollDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class ViewPager extends Espresso {

    public ViewPager(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void scrollTo(String elementLocator, ScrollDirection scrollDirection, boolean smoothScroll) {
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("scrollTo", scrollDirection.toString().toLowerCase());
        args.put("smoothScroll", smoothScroll);
        androidDriver.executeScript("mobile:scrollToPage", args);
    }

    public void scrollTo(String elementLocator, ScrollDirection scrollDirection, boolean smoothScroll, int iteration) {
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("scrollTo", scrollDirection.toString().toLowerCase());
        args.put("smoothScroll", smoothScroll);
        for (int i = 0; i < iteration; i++) {
            androidDriver.executeScript("mobile:scrollToPage", args);
        }
    }

    public void scrollToPage(String elementLocator, int page) {
        AndroidElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("scrollToPage", page);
        androidDriver.executeScript("mobile:scrollToPage", args);
    }
}
