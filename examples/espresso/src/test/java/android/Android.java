package android;

import android.pages.MainPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Android {

    private AndroidDriver<AndroidElement> androidDriver;

    public Android(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public MainPage homePage() {
        return new MainPage(androidDriver);
    }
}
