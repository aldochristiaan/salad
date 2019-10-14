package android.pages;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePage extends UiAutomator2 {

    public BasePage(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }
}
