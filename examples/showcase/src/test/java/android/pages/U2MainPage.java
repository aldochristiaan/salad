package android.pages;

import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assumptions;

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
        goHome();
        getElement().withLocator("MAIN_GREETING");
        getElement().withLocator("MAIN_GREETING", 5);
        getElement().withLocator("MAIN_GREETING", Direction.UP);
        getElement().withDefaultTimeout("MAIN_GREETING");
    }

    public void showcaseGetMultipleElement() {
        openList();
        getMultipleElement().withLocator("LIST_ITEM_LABEL");
        getMultipleElement().withLocator("LIST_ITEM_LABEL", 5);
        getMultipleElement().withDefaultTimeout("LIST_ITEM_LABEL");
        int count = getMultipleElement().count("LIST_ITEM_LABEL");
        if (count < 0) {
            throw new IllegalStateException("count should never be negative");
        }
        goHome();
    }

    // --- Tap ---
    public void showcaseTap() {
        tap().element("MAIN_BTN_LIST");
        goHome();
        tap().pendingElement("MAIN_BTN_LIST", 5);
        goHome();
    }

    public void showcaseTapAt() {
        goHome();
        tap().tapAt(300, 300, java.time.Duration.ofMillis(200));
    }

    // --- Type ---
    public void showcaseType(String text) {
        openForms();
        type().pendingElement("FORMS_INPUT_NAME", 5, text);
        goHome();
    }

    // --- Swipe ---
    public void showcaseSwipe() {
        openList();
        swipe().toDirection(Direction.UP);
        swipe().to(0.5, 0.5, 0.5, 0.2);
        goHome();
    }

    // --- Long tap ---
    public void showcaseLongTap() {
        openList();
        longTap().pendingElement("LIST_ITEM_LABEL", 5, 0);
        goHome();
    }

    // --- Mobile gestures ---
    public void showcaseMobileGesture() {
        goHome();
        mobileGesture().tap("MAIN_GREETING");
        mobileGesture().doubleTap("MAIN_GREETING");
        mobileGesture().longTap("MAIN_GREETING");
        openGestures();
        mobileGesture().pinchOpen("GESTURES_ZOOM_IMAGE");
        mobileGesture().pinchClose("GESTURES_ZOOM_IMAGE");
        mobileGesture().swipe(Direction.UP, 0.5, 0.8, 0.4);
        mobileGesture().scroll(Direction.UP, 0.5, 0.8, 0.4);
        mobileGesture().fling("GESTURES_FLING_RECYCLER", Direction.UP);
        goHome();
    }

    // --- Toast ---
    public void showcaseToast() {
        openToast();
        tap().element("TOAST_BTN_SHORT");
        // The app mirrors the last issued toast text on screen — assertable on every image.
        validateValue().equalsTrue("This is a short toast".equals(getText("TOAST_STATUS")),
                "toast status text should mirror the issued toast");
        // ATD/slim system images enqueue the toast but never render the toast window, and
        // UiAutomator2 can only see toasts through that window — skip the toast APIs there.
        Assumptions.assumeTrue(toast().waitForToast(3),
                "Toast windows are not rendered on this system image (ATD/slim)");
        toast().isVisible();
        toast().getText(5);
        goHome();
    }

    // --- Context switching (WebView) ---
    public void showcaseChangeContext() {
        changeContext().toWebView();
        changeContext().toNative();
    }

    // --- Validations & state ---
    public void showcaseValidations() {
        goHome();
        validateDisplayed("MAIN_GREETING", 5);
        validateExist("MAIN_GREETING", 5);
        validateEnabled("MAIN_BTN_LIST");
        validateNotSelected("MAIN_BTN_LIST");
        openForms();
        tap().element("FORMS_CHECKBOX_TERMS");
        validateChecked("FORMS_CHECKBOX_TERMS");
        goHome();
    }

    public void showcaseStateChecks() {
        goHome();
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
