package android.pages;

import id.aldochristiaan.salad.util.Action;
import id.aldochristiaan.salad.util.Strategy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MainPage extends BasePage {

    public MainPage(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void isOnMainPage() {
        validateDisplayed("ANDROID_TOOLBAR", 5);
    }

    public void validateDrawer() {
        validateDisplayed(constructLocator("GENERAL_TEXT", "android.studio@android.com"), 2);
        validateDisplayed(constructLocator("GENERAL_CONTAINS_TEXT", "Home"), 2);
        validateDisplayed(constructLocator("GENERAL_TRANSLATION_TEXT", "Gallery"), 2);
        validateDisplayed(constructLocator("GENERAL_XPATH", "//*[@text='Slideshow']"), 2);
    }

    public void openDrawer() {
        drawer().open("ANDROID_DRAWER");
    }

    public void closeDrawer() {
        drawer().close("ANDROID_DRAWER");
    }

    public void tapOnFABUsingUiAutomator() {
        uiAutomator().sauce(Strategy.clazz, "com.google.android.material.floatingactionbutton.FloatingActionButton", Action.click);
        tap().element("ANDROID_FLOATING_ACTION_BUTTON");
    }

    public void tapOnFAB() {
        tap().element("ANDROID_FLOATING_ACTION_BUTTON");
    }

    public void tapMoreOptions() {
        tap().element("ANDROID_MORE_OPTIONS");
        pressBackButton();
    }

    public void goToPages() {
        openDrawer();
        tap().element(constructLocator("GENERAL_TEXT", "Home"));
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is home Fragment"), 2);
        openDrawer();
        tap().element(constructLocator("GENERAL_TEXT", "Gallery"));
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is gallery Fragment"), 2);
        openDrawer();
        tap().element(constructLocator("GENERAL_TEXT", "Share"));
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is share Fragment"), 2);
    }

    public void debugElementUsingFlash() {
        flash().element("ANDROID_FLOATING_ACTION_BUTTON", 500, 4);
    }

    public void failedMethod() {
        tap().element(constructLocator("GENERAL_TEXT", "Failed"));
    }
}
