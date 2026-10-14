package android.pages;

import io.appium.java_client.android.AndroidDriver;

/**
 * Container for the Espresso driver's page objects. Kept separate from the UiAutomator2
 * container so a test only ever sees pages backed by APIs its driver supports.
 */
public class EspressoPages {

    private final AndroidDriver androidDriver;

    public EspressoPages(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public EspMainPage main() {
        return new EspMainPage(androidDriver);
    }
}
