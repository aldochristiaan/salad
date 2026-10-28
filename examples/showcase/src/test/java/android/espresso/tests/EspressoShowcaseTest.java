package android.espresso.tests;

import android.AndroidFactory;
import android.TestListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Drives every Espresso showcase page. Each test groups a family of API calls so a failure
 * points at the broken module.
 */
@ExtendWith(TestListener.class)
public class EspressoShowcaseTest extends AndroidFactory {

    @Test
    public void drawerAndFlash() {
        android.esp().main().showcaseDrawer();
        android.esp().main().showcaseFlash();
    }

    @Test
    public void navigateToMenuItem() {
        android.esp().main().showcaseNavigate();
    }

    @Test
    public void tapAndType() {
        android.esp().main().showcaseTap();
        android.esp().main().showcaseType("Aldo");
    }

    @Test
    public void swipeAndMultipleTap() {
        android.esp().main().showcaseSwipe();
        android.esp().main().showcaseSwipeTo();
        android.esp().main().showcaseMultipleTap();
    }

    @Test
    public void viewPagerNavigation() {
        android.esp().main().showcaseViewPager();
    }

    @Test
    public void toastValidation() {
        android.esp().main().showcaseValidateToast();
    }

    @Test
    public void validationsAndState() {
        android.esp().main().showcaseValidations();
        android.esp().main().showcaseStateAndText();
    }

    @Test
    public void screenshotAndKeys() {
        android.esp().main().showcaseScreenshotAndKeys();
    }
}
