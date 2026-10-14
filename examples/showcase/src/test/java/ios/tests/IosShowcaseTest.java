package ios.tests;

import ios.IosFactory;
import org.junit.jupiter.api.Test;

/**
 * Drives the iOS showcase page. Each test groups a family of API calls so a failure points at
 * the broken module.
 *
 * <p>Requires a macOS runner with an iOS simulator; excluded from the Linux Android workflow.
 */
public class IosShowcaseTest extends IosFactory {

    @Test
    public void elementLookups() {
        ios.showcaseGetElement();
    }

    @Test
    public void multipleElementLookups() {
        ios.showcaseGetMultipleElement();
    }

    @Test
    public void tap() {
        ios.showcaseTap();
    }

    @Test
    public void longTap() {
        ios.showcaseLongTap();
    }

    @Test
    public void type() {
        ios.showcaseType("Aldo");
    }

    @Test
    public void swipe() {
        ios.showcaseSwipe();
    }

    @Test
    public void deeplink() {
        ios.showcaseDeeplink();
    }

    @Test
    public void textAndAttribute() {
        ios.showcaseTextAndAttribute();
    }

    @Test
    public void validations() {
        ios.showcaseValidations();
    }

    @Test
    public void screenshotAndKeyboard() {
        ios.showcaseScreenshot();
    }
}
