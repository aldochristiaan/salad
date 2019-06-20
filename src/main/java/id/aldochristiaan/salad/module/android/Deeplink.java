package id.aldochristiaan.salad.module.android;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Deeplink extends Android {

    public Deeplink(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void open(String deeplink){
        androidDriver.get(deeplink);
    }
}
