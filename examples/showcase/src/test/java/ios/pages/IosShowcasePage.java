package ios.pages;

import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

/**
 * Showcases the XCUITest driver API: element lookups, tap, long-tap, type, the full swipe
 * family, deeplinks, text/attribute access, validations, screenshots and the keyboard.
 *
 * <p>Every public XCUITest module method is exercised at least once so this page doubles as living
 * documentation of the iOS surface.
 */
public class IosShowcasePage extends BaseIosPage {

    public IosShowcasePage(IOSDriver iosDriver) {
        super(iosDriver);
    }

    // --- Element lookups ---
    public void showcaseGetElement() {
        getElement().withLocator("MAIN_LIST");
        getElement().withLocator("MAIN_LIST", 5);
        getElement().withLocator("MAIN_LIST", Direction.UP);
        getElement().withDefaultTimeout("MAIN_LIST");
    }

    public void showcaseGetMultipleElement() {
        getMultipleElement().withLocator("LIST_ITEM");
        getMultipleElement().withLocator("LIST_ITEM", 5);
    }

    // --- Tap ---
    public void showcaseTap() {
        tapElement().element("MAIN_NAV_LIST");
        tapElement().elementWithWait("MAIN_NAV_LIST", 5);
        tapElement().location(200, 300);
        tapElement().location(200, 300, java.time.Duration.ofMillis(200));
    }

    // --- Long tap ---
    public void showcaseLongTap() {
        longTapElement().element("GESTURES_LONG_PRESS");
        longTapElement().pendingElement("GESTURES_LONG_PRESS", 5);
    }

    // --- Type ---
    public void showcaseType(String text) {
        typeText().element("FORMS_NAME_FIELD", text);
        typeText().element("FORMS_EMAIL_FIELD", 1, text + "@example.com");
        typeText().pendingElement("FORMS_NAME_FIELD", 5, text);
    }

    // --- Swipe family ---
    public void showcaseSwipe() {
        swipe().up();
        swipe().down();
        swipe().left();
        swipe().right();
        swipe().toDirection(Direction.UP);
        swipe().to(100, 100, 600, 200);
        swipe().upAtSpecifiedLocator("LIST_ITEM_1");
        swipe().downAtSpecifiedLocator("LIST_ITEM_1");
        swipe().leftAtSpecifiedLocator("LIST_ITEM_1");
        swipe().rightAtSpecifiedLocator("LIST_ITEM_1");
        swipe().toLocator("LIST_ITEM_1");
        swipe().toLocator("LIST_ITEM_1", Direction.UP);
    }

    // --- Deeplink ---
    public void showcaseDeeplink() {
        deeplink().open("saladshowcase://main/list");
        deeplink().open("saladshowcase", "main/list");
    }

    // --- Text & attribute ---
    public void showcaseTextAndAttribute() {
        getText("MAIN_LIST");
        getText("LIST_ITEM_1", 0);
        getElementAttributeValue("FORMS_AGREE_TOGGLE", "value");
        findElementBy(org.openqa.selenium.By.id("name_field"));
        findElementBy(org.openqa.selenium.By.id("name_field"), 5);
        findElementsBy(org.openqa.selenium.By.className("Button"), 5);
    }

    // --- Validations ---
    public void showcaseValidations() {
        validateElementVisible("MAIN_LIST");
        validateElementWithText("MAIN_LIST", "Salad Showcase");
        validateElementContainsText("MAIN_LIST", "Salad");
        validateDisplayed("MAIN_LIST");
        validateExist("MAIN_LIST", 5);
        validateEnabled("FORMS_SUBMIT");
    }

    // --- Screenshot & keyboard ---
    public void showcaseScreenshot() {
        takeScreenshot("ios-showcase");
        hideKeyboard();
    }
}
