package android;

import android.pages.MainPage;
import io.appium.java_client.android.AndroidDriver;

public class Android {

    private AndroidDriver androidDriver;

    public Android(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public MainPage mainPage() {
        return new MainPage(androidDriver);
    }
}
