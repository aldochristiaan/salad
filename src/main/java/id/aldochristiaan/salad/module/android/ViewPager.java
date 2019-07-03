package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import id.aldochristiaan.salad.util.ScrollDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.HashMap;

public class ViewPager extends Android {

    public ViewPager(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void scrollTo(AndroidElement androidElement, ScrollDirection scrollDirection, boolean smoothScroll) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("scrollTo", scrollDirection.toString().toLowerCase());
        args.put("smoothScroll", smoothScroll);
        androidDriver.executeScript("mobile:scrollToPage", args);
    }

    public void scrollTo(AndroidElement androidElement, ScrollDirection scrollDirection, boolean smoothScroll, int iteration) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("scrollTo", scrollDirection.toString().toLowerCase());
        args.put("smoothScroll", smoothScroll);
        for (int i = 0; i < iteration; i++) {
            androidDriver.executeScript("mobile:scrollToPage", args);
        }
    }

    public void scrollToPage(AndroidElement androidElement, int page) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        args.put("scrollToPage", page);
        androidDriver.executeScript("mobile:scrollToPage", args);
    }
}
