package android.pages;

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
        validateDisplayed(constructLocator("GENERAL_TEXT", "android.studio@android.com"), 5);
        validateDisplayed(constructLocator("GENERAL_CONTAINS_TEXT", "Home"), 5, "Couldn't found element with text Home");
        validateDisplayed(constructLocator("GENERAL_TRANSLATION_TEXT", "Gallery"), 5);
        validateDisplayed(constructLocator("GENERAL_XPATH", "//*[@text='Slideshow']"), 5);
    }

    public void openDrawer() {
        tap().pendingElement("ANDROID_DRAWER", 5);
    }

    public void closeDrawer() {
        swipe().left();
    }

    public void tapOnFAB() {
        tap().element("ANDROID_FLOATING_ACTION_BUTTON");
    }

    public void tapMoreOptions() {
        tap().element("ANDROID_MORE_OPTIONS");
        tap().pendingElement(constructLocator("GENERAL_TEXT", "Settings"), 2);
    }

    public void goToPages() {
        openDrawer();
        tap().pendingElement(constructLocator("GENERAL_TEXT", "Home"), 5);
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is home Fragment"), 5);
        openDrawer();
        tap().pendingElement(constructLocator("GENERAL_TEXT", "Gallery"), 5);
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is gallery Fragment"), 5);
        openDrawer();
        tap().pendingElement(constructLocator("GENERAL_TEXT", "Share"), 5);
        validateDisplayed(constructLocator("GENERAL_TEXT", "This is share Fragment"), 5);
    }

    /**
     * Can't debug element while using UiAutomator2 Driver
     */
//    public void debugElementUsingFlash() {
//        flash().element("ANDROID_FLOATING_ACTION_BUTTON", 500, 4);
//    }

    public void failedMethod() {
        tap().pendingElement(constructLocator("GENERAL_TEXT", "Failed"), 2);
    }
}
