package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Espresso navigation utilities.
 * Provides methods for navigating to specific menu items using Espresso.
 */
public class Navigate extends Espresso {

    public Navigate(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Navigate to a specific menu item within an element
     * @param elementLocator Locator for the navigation element
     * @param menuItemId Resource ID of the menu item to navigate to
     */
    public void toElement(String elementLocator, int menuItemId) {
        if (menuItemId < 0) {
            throw new IllegalArgumentException("Menu item ID must be non-negative");
        }

        LogUtil.info(String.format("Navigating to menu item %d in element '%s'",
                menuItemId, elementLocator));

        WebElement webElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of(
                "element", webElement,
                "menuItemId", menuItemId
        );
        androidDriver.executeScript("mobile:navigateTo", args);
    }
}
