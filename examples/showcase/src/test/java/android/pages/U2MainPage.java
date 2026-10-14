package android.pages;

import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;

/**
 * Showcases the UiAutomator2 driver core API: tap, type, swipe, gestures, toast, context
 * switching, element lookups, validations, screenshots and system keys.
 */
public class U2MainPage extends BaseU2Page {

    public U2MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    // --- Element lookups (GetElement / GetMultipleElement) ---
    public void showcaseGetElement() {
        getElement().withLocator("MAIN_GREETING");
        getElement().withLocator("MAIN_GREETING", 5);
        getElement().withLocator("MAIN_GREETING", Direction.UP);
        getElement().withDefaultTimeout("MAIN_GREETING");
    }

    public void showcaseGetMultipleElement() {
        getMultipleElement().withLocator("LIST_ITEM_LABEL");
        getMultipleElement().withLocator("LIST_ITEM_LABEL", 5);
        getMultipleElement().withDefaultTimeout("LIST_ITEM_LABEL");
        int count = getMultipleElement().count("LIST_ITEM_LABEL");
        if (count < 0) {
            throw new IllegalStateException("count should never be negative");
        }
    }

    // --- Tap ---
    public void showcaseTap() {
        tap().element("MAIN_BTN_LIST");
        tap().pendingElement("MAIN_BTN_LIST", 5);
    }

    public void showcaseTapAt() {
        tap().tapAt(300, 300, java.time.Duration.ofMillis(200));
    }

    // --- Type ---
    public void showcaseType(String text) {
        type().pendingElement("FORMS_INPUT_NAME", 5, text);
    }

    // --- Swipe ---
    public void showcaseSwipe() {
        swipe().toDirection(Direction.UP);
        swipe().to(100, 100, 800, 300);
    }

    // --- Long tap ---
    public void showcaseLongTap() {
        longTap().pendingElement("LIST_ITEM_LABEL", 5, 0);
    }

    // --- Mobile gestures ---
    public void showcaseMobileGesture() {
        mobileGesture().tap("MAIN_GREETING");
        mobileGesture().doubleTap("MAIN_GREETING");
        mobileGesture().longTap("MAIN_GREETING");
        mobileGesture().pinchOpen("GESTURES_ZOOM_IMAGE");
        mobileGesture().pinchClose("GESTURES_ZOOM_IMAGE");
        mobileGesture().swipe(Direction.UP, 0.5, 0.8, 0.4);
        mobileGesture().scroll(Direction.UP, 0.5, 0.8, 0.4);
        mobileGesture().fling("MAIN_GREETING", Direction.UP);
    }

    // --- Toast ---
    public void showcaseToast() {
        toast().isVisible();
        toast().waitForToast(5);
        toast().getText(5);
    }

    // --- Context switching (WebView) ---
    public void showcaseChangeContext() {
        changeContext().toWebView();
        changeContext().toNative();
    }

    // --- Validations & state ---
    public void showcaseValidations() {
        validateDisplayed("MAIN_GREETING", 5);
        validateExist("MAIN_GREETING", 5);
        validateEnabled("MAIN_BTN_LIST");
        validateSelected("MAIN_BTN_LIST");
        validateChecked("FORMS_CHECKBOX_TERMS");
    }

    public void showcaseStateChecks() {
        isElementDisplayed("MAIN_GREETING", 5);
        isElementExist("MAIN_GREETING", 5);
        isElementEnabled("MAIN_BTN_LIST");
        getText("MAIN_GREETING");
        getElementAttributeValue("MAIN_GREETING", 5, "text");
    }

    // --- Screenshot & system keys ---
    public void showcaseScreenshot() {
        takeScreenshot("u2-showcase");
    }

    public void showcaseSystemKeys() {
        hideKeyboard();
    }
}
