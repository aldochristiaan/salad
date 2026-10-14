package android.pages;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;

/** Base page for all UiAutomator2 showcase pages. */
public abstract class BaseU2Page extends UiAutomator2 {
    public BaseU2Page(AndroidDriver androidDriver) {
        super(androidDriver);
    }
}
