package android.pages;

import id.aldochristiaan.salad.util.Action;
import id.aldochristiaan.salad.util.Coordinates;
import id.aldochristiaan.salad.util.PrecisionDescriber;
import id.aldochristiaan.salad.util.ScrollDirection;
import id.aldochristiaan.salad.util.Strategy;
import id.aldochristiaan.salad.util.SwipeSpeed;
import id.aldochristiaan.salad.util.WebLocator;
import io.appium.java_client.android.AndroidDriver;

/**
 * Showcases the Espresso-only API: drawer, flash, navigate, multiple-tap, swipe/swipeTo with
 * Coordinates/PrecisionDescriber, ViewPager, validateToast (regexp), WebAtoms and the
 * UiAutomator backdoor.
 */
public class EspMainPage extends BaseEspPage {

    public EspMainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    // --- Drawer ---
    public void showcaseDrawer() {
        drawer().open("MAIN_DRAWER_LAYOUT");
        drawer().close("MAIN_DRAWER_LAYOUT");
    }

    // --- Flash ---
    public void showcaseFlash() {
        flash().element("MAIN_FAB");
        flash().element("MAIN_FAB", 500, 3);
    }

    // --- Navigate to a menu/drawer item by resource id ---
    public void showcaseNavigate() {
        // Opens the drawer and taps the "List Screen" menu item. The int is the compiled
        // resource id of R.id.nav_list in the committed showcase APK (0x7f080155). If the app
        // is rebuilt, refresh this value via: aapt2 dump resources <apk> | grep nav_list
        navigate().toElement("MAIN_DRAWER_LAYOUT", 0x7f080155);
    }

    // --- Tap variants ---
    public void showcaseTap() {
        tap().element("MAIN_BTN_LIST");
        tap().element("MAIN_BTN_LIST", 0);
        tap().location(300, 300, java.time.Duration.ofMillis(200));
    }

    // --- Type variants ---
    public void showcaseType(String text) {
        type().element("FORMS_INPUT_NAME", text);
        type().element("FORMS_INPUT_NAME", text, 0);
    }

    // --- Swipe with Coordinates/PrecisionDescriber ---
    public void showcaseSwipe() {
        swipe().element(
                "LIST_RECYCLER",
                SwipeSpeed.FAST,
                Coordinates.VISIBLE_CENTER,
                Coordinates.TOP_CENTER,
                PrecisionDescriber.FINGER
        );
    }

    // --- SwipeTo ---
    public void showcaseSwipeTo() {
        swipeTo().element(
                "FORMS_BTN_SUBMIT",
                "FORMS_INPUT_NAME",
                SwipeSpeed.SLOW,
                Coordinates.VISIBLE_CENTER,
                Coordinates.TOP_CENTER
        );
    }

    // --- Multiple tap ---
    public void showcaseMultipleTap() {
        multipleTap().element("MAIN_BTN_LIST", 2);
        multipleTap().element("MAIN_BTN_LIST", 2, 100);
    }

    // --- ViewPager ---
    public void showcaseViewPager() {
        viewPager().scrollTo("VIEWPAGER_PAGE_CONTENT", ScrollDirection.RIGHT, true);
        viewPager().scrollToPage("VIEWPAGER_PAGE_CONTENT", 2);
    }

    // --- Toast validation (plain + regexp) ---
    public void showcaseValidateToast() {
        validateToast().exist("short toast", false);
        validateToast().exist("Error: .* \\(code 500\\)", true);
    }

    // --- WebAtoms (inside WebView context) ---
    public void showcaseWebAtoms() {
        webAtoms().type("WEBVIEW_WEB_VIEW", WebLocator.ID, "username", "salad");
        webAtoms().clear("WEBVIEW_WEB_VIEW", WebLocator.ID, "username");
        webAtoms().findElement("WEBVIEW_WEB_VIEW", WebLocator.ID, "submit_btn");
        webAtoms().scrollIntoView("WEBVIEW_WEB_VIEW", WebLocator.ID, "bottom_section");
    }

    // --- UiAutomator backdoor ---
    public void showcaseUiAutomatorBackdoor() {
        uiAutomator().sauce(Strategy.text, "List Screen", Action.click);
    }

    // --- Validations ---
    public void showcaseValidations() {
        validateElementVisible("MAIN_GREETING");
        validateDisplayed("MAIN_GREETING", 5, "greeting should show");
        validateExist("MAIN_GREETING", "greeting should exist");
        validateEnabled("MAIN_BTN_LIST", "list button should be enabled");
        validateDisabled("FORMS_BTN_DISABLED", "disabled button should be disabled");
        validateElementText("MAIN_GREETING", "This is the Salad Showcase home screen", true);
    }

    public void showcaseStateAndText() {
        isElementExist("MAIN_GREETING");
        getText("MAIN_GREETING");
        getElementAttributeValue("MAIN_GREETING", "text");
    }

    public void showcaseScreenshotAndKeys() {
        takeScreenshot("espresso-showcase");
        hideKeyboard();
        pressBackButton();
    }
}
