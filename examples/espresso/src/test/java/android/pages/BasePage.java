package android.pages;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePage extends Espresso {

    public BasePage(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }
}
