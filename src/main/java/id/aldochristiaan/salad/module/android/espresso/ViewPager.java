package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.ScrollDirection;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class ViewPager extends Espresso {

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
        Map<String, Object> args = Map.of(
                "element", webElement,
                "scrollToPage", page
        );
        androidDriver.executeScript("mobile:scrollToPage", args);
    }

    private void performScroll(String elementLocator, ScrollDirection direction, boolean smoothScroll, int iteration) {
        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "scrollTo", direction.toString().toLowerCase(),
                "smoothScroll", smoothScroll
        );
        for (int i = 0; i < iteration; i++) {
            androidDriver.executeScript("mobile:scrollToPage", args);
        }
    }
}
