package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.ScrollDirection;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ViewPager extends Espresso {

    private static final Duration DRAG_SETTLE = Duration.ofMillis(50);
    private static final Duration DRAG_DURATION = Duration.ofMillis(300);
    private static final int INTER_SWIPE_DELAY = 400;
    // Enough travel (60% of the pager width) for ViewPager2/ViewPager to snap exactly one page.
    private static final double TRAVEL_FRACTION = 0.6;
    // Swiping past the first/last page is a no-op, so a bounded loop can home to either edge.
    private static final int EDGE_MAX_SWIPES = 10;

    public ViewPager(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void scrollTo(String elementLocator, ScrollDirection scrollDirection, boolean smoothScroll) {
        performScroll(elementLocator, scrollDirection, smoothScroll, 1);
    }

    public void scrollTo(String elementLocator, ScrollDirection scrollDirection, boolean smoothScroll, int iteration) {
        performScroll(elementLocator, scrollDirection, smoothScroll, iteration);
    }

    public void scrollToPage(String elementLocator, int page) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        try {
            Map<String, Object> args = Map.of(
                    "element", webElement,
                    "scrollToPage", page
            );
            androidDriver.executeScript("mobile:scrollToPage", args);
        } catch (WebDriverException e) {
            // mobile: scrollToPage is backed by espresso-contrib's ViewPagerActions, which only
            // supports the legacy androidx.viewpager.widget.ViewPager and rejects ViewPager2 with
            // a ClassCastException. Fall back to W3C swipe gestures: the pager is assumed to
            // currently show page 0 (fresh activity) and every forward swipe moves one page.
            for (int i = 0; i < page; i++) {
                swipePager(webElement, ScrollDirection.LEFT);
            }
        }
    }

    private void performScroll(String elementLocator, ScrollDirection direction, boolean smoothScroll, int iteration) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "scrollTo", direction.toString().toLowerCase(),
                "smoothScroll", smoothScroll
        );
        try {
            for (int i = 0; i < iteration; i++) {
                androidDriver.executeScript("mobile:scrollToPage", args);
            }
        } catch (WebDriverException e) {
            // ViewPager2 fallback (see scrollToPage). FIRST/LAST swipe towards the edge until
            // the pager stops moving; swiping past the edge is a harmless no-op.
            int swipes = direction == ScrollDirection.FIRST || direction == ScrollDirection.LAST
                    ? EDGE_MAX_SWIPES : iteration;
            for (int i = 0; i < swipes; i++) {
                swipePager(webElement, direction);
            }
        }
    }

    private void swipePager(WebElement pager, ScrollDirection direction) {
        Rectangle rect = pager.getRect();
        int y = rect.y + rect.height / 2;
        // LEFT/LAST advance to the next page (finger drags leftwards), RIGHT/FIRST go back.
        boolean forward = direction == ScrollDirection.LEFT || direction == ScrollDirection.LAST;
        int startX = (int) (rect.x + rect.width * (forward ? 1 - TRAVEL_FRACTION / 2 : TRAVEL_FRACTION / 2));
        int endX = (int) (rect.x + rect.width * (forward ? TRAVEL_FRACTION / 2 : 1 - TRAVEL_FRACTION / 2));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new org.openqa.selenium.interactions.Pause(finger, DRAG_SETTLE));
        swipe.addAction(finger.createPointerMove(DRAG_DURATION, PointerInput.Origin.viewport(), endX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(List.of(swipe));
        delay(INTER_SWIPE_DELAY);
    }
}
