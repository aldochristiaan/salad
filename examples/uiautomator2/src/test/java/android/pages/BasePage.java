package android.pages;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;

public class BasePage extends UiAutomator2 {

    public BasePage(AndroidDriver androidDriver) {
        super(androidDriver);
    }
}
