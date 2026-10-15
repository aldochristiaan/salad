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
        goHome();
        drawer().open("MAIN_DRAWER_LAYOUT");
        drawer().close("MAIN_DRAWER_LAYOUT");
    }

    // --- Flash ---
    public void showcaseFlash() {
        goHome();
        flash().element("MAIN_FAB");
        flash().element("MAIN_FAB", 500, 3);
    }

    // --- Navigate to a menu/drawer item by resource id ---
    public void showcaseNavigate() {
        goHome();
        drawer().open("MAIN_DRAWER_LAYOUT");
        // NavigationView is the target (as per Salad Espresso navigate contract)
        navigate().toElement("MAIN_NAV_VIEW", 0x7f080155);
        goHome();
    }

    // --- Tap variants ---
    public void showcaseTap() {
        goHome();
        tap().element("MAIN_BTN_LIST");
        goHome();
        tap().element("MAIN_BTN_LIST", 0);
        goHome();
        tap().location(300, 300, java.time.Duration.ofMillis(200));
    }

    // --- Type variants ---
    public void showcaseType(String text) {
        openForms();
        type().element("FORMS_INPUT_NAME", text);
        type().element("FORMS_INPUT_NAME", text, 0);
        goHome();
    }

    // --- Swipe with Coordinates/PrecisionDescriber ---
    public void showcaseSwipe() {
        openList();
        swipe().element(
                "LIST_RECYCLER",
                SwipeSpeed.FAST,
                Coordinates.VISIBLE_CENTER,
                Coordinates.TOP_CENTER,
                PrecisionDescriber.FINGER
        );
        goHome();
    }

    // --- SwipeTo ---
    public void showcaseSwipeTo() {
        openForms();
        swipeTo().element(
                "FORMS_BTN_SUBMIT",
                "FORMS_INPUT_NAME",
                SwipeSpeed.SLOW,
                Coordinates.VISIBLE_CENTER,
                Coordinates.TOP_CENTER
        );
        goHome();
    }

    // --- Multiple tap ---
    public void showcaseMultipleTap() {
        goHome();
        multipleTap().element("MAIN_FAB", 2);
        multipleTap().element("MAIN_FAB", 2, 100);
        goHome();
    }

    // --- ViewPager ---
    public void showcaseViewPager() {
        openViewpager();
        viewPager().scrollTo("VIEWPAGER_PAGER", ScrollDirection.RIGHT, true);
        viewPager().scrollToPage("VIEWPAGER_PAGER", 2);
        goHome();
    }

    // --- Toast validation (plain + regexp) ---
    public void showcaseValidateToast() {
        // Toast must be triggered on Toast screen
        openToast();
        tap().element("TOAST_BTN_SHORT");
        validateToast().exist("short toast", false);
        validateToast().exist("Error: .* \\(code 500\\)", true);
        goHome();
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
        goHome();
        validateElementVisible("MAIN_GREETING");
        validateDisplayed("MAIN_GREETING", 5, "greeting should show");
        validateExist("MAIN_GREETING", "greeting should exist");
        validateEnabled("MAIN_BTN_LIST", "list button should be enabled");
        openForms();
        validateDisabled("FORMS_BTN_DISABLED", "disabled button should be disabled");
        goHome();
        validateElementText("MAIN_GREETING", "This is the Salad Showcase home screen", true);
    }

    public void showcaseStateAndText() {
        goHome();
        isElementExist("MAIN_GREETING");
        getText("MAIN_GREETING");
        getElementAttributeValue("MAIN_GREETING", "text");
    }

    public void showcaseScreenshotAndKeys() {
        goHome();
        takeScreenshot("espresso-showcase");
        hideKeyboard();
        // Press BACK from a child screen: on the root activity BACK would exit the app and
        // Espresso cannot inject the key with no resumed activity.
        openList();
        pressBackButton();
        goHome();
    }
}
