package android.pages;

import id.aldochristiaan.salad.module.UiAutomator2;
import io.appium.java_client.android.AndroidDriver;

/** Base page for all UiAutomator2 showcase pages. */
public abstract class BaseU2Page extends UiAutomator2 {

    private static final String APP_PACKAGE = "id.aldochristiaan.salad.showcase";

    public BaseU2Page(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Return to the MainActivity. Every sub-screen of the showcase app is a separate Activity
     * launched from Main, so pressing BACK once returns here. Tests run in an arbitrary order
     * against a shared session, so each navigation helper starts by going home to avoid one
     * test's screen leaking into the next.
     */
    protected void goHome() {
        for (int i = 0; i < 6 && !isElementDisplayed("MAIN_GREETING", 1); i++) {
            pressBackButton();
        }
        if (!isElementDisplayed("MAIN_GREETING", 1)) {
            // The app may have been backgrounded; bring it back to the foreground.
            androidDriver.activateApp(APP_PACKAGE);
        }
    }

    protected void openList() {
        goHome();
        tap().element("MAIN_BTN_LIST");
        // Activities are launched asynchronously; wait for the target screen so the next
        // action doesn't race the transition.
        isElementDisplayed("LIST_RECYCLER", 5);
    }

    protected void openForms() {
        goHome();
        tap().element("MAIN_BTN_FORMS");
        isElementDisplayed("FORMS_INPUT_NAME", 5);
    }

    protected void openToast() {
        goHome();
        tap().element("MAIN_BTN_TOASTS");
        isElementDisplayed("TOAST_BTN_SHORT", 5);
    }

    protected void openGestures() {
        goHome();
        tap().element("MAIN_BTN_GESTURES");
        isElementDisplayed("GESTURES_LONG_PRESS", 5);
    }

    protected void openViewpager() {
        goHome();
        tap().element("MAIN_BTN_VIEWPAGER");
        isElementDisplayed("VIEWPAGER_PAGER", 5);
    }
}
