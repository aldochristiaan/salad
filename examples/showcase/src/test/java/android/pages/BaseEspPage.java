package android.pages;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;

/** Base page for all Espresso showcase pages. */
public abstract class BaseEspPage extends Espresso {
    public BaseEspPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }
}
