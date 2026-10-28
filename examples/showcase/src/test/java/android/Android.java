package android;

import android.pages.EspressoPages;
import android.pages.UiAutomator2Pages;
import id.aldochristiaan.salad.util.Driver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Thin facade handed to tests. It exposes the page objects that match the active driver so a
 * test only ever sees pages whose API its driver actually supports (e.g. {@code webAtoms()} is
 * Espresso-only, {@code mobileGesture()} is UiAutomator2-only).
 */
public class Android {

    private final UiAutomator2Pages u2;
    private final EspressoPages esp;

    public Android(AndroidDriver androidDriver, Driver driverType) {
        this.u2 = new UiAutomator2Pages(androidDriver);
        this.esp = new EspressoPages(androidDriver);
    }

    public UiAutomator2Pages u2() {
        return u2;
    }

    public EspressoPages esp() {
        return esp;
    }
}
