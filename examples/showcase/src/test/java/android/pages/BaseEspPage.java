package android.pages;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;

/** Base page for all Espresso showcase pages. */
public abstract class BaseEspPage extends Espresso {

    private static final String APP_PACKAGE = "id.aldochristiaan.salad.showcase";

    public BaseEspPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Return to the MainActivity. Every sub-screen of the showcase app is a separate Activity
     * launched from Main, so pressing BACK once returns here.
     */
    protected void goHome() {
        for (int i = 0; i < 6 && !isElementDisplayed("MAIN_GREETING", 1); i++) {
            pressBackButton();
        }
        if (!isElementDisplayed("MAIN_GREETING", 1)) {
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
