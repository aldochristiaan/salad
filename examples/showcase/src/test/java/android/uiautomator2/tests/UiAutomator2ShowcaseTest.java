package android.uiautomator2.tests;

import android.AndroidFactory;
import android.TestListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Drives every UiAutomator2 showcase page. Each test groups a family of API calls so a failure
 * points at the broken module.
 */
@ExtendWith(TestListener.class)
public class UiAutomator2ShowcaseTest extends AndroidFactory {

    @Test
    public void elementLookups() {
        android.u2().main().showcaseGetElement();
    }

    @Test
    public void tapAndType() {
        android.u2().main().showcaseTap();
        android.u2().main().showcaseTapAt();
        android.u2().main().showcaseType("Aldo");
    }

    @Test
    public void swipeAndLongTap() {
        android.u2().main().showcaseSwipe();
        android.u2().main().showcaseLongTap();
    }

    @Test
    public void mobileGestures() {
        android.u2().main().showcaseMobileGesture();
    }

    @Test
    public void toastHandling() {
        android.u2().main().showcaseToast();
    }

    @Test
    public void validationsAndState() {
        android.u2().main().showcaseValidations();
        android.u2().main().showcaseStateChecks();
    }

    @Test
    public void screenshot() {
        android.u2().main().showcaseScreenshot();
        android.u2().main().showcaseSystemKeys();
    }
}
