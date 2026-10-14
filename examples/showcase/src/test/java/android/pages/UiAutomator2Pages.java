package android.pages;

import io.appium.java_client.android.AndroidDriver;

/**
 * Container for the UiAutomator2 driver's page objects. Kept separate from the Espresso
 * container so a test only ever sees pages backed by APIs its driver supports.
 */
public class UiAutomator2Pages {

    private final AndroidDriver androidDriver;

    public UiAutomator2Pages(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public U2MainPage main() {
        return new U2MainPage(androidDriver);
    }
}
