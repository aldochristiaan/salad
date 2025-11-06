package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Espresso drawer interaction utilities.
 * Provides methods for opening and closing navigation drawers.
 */
public class Drawer extends Espresso {

    public Drawer(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    /**
     * Open a navigation drawer
     * @param elementLocator Locator for the drawer element
     */
    public void open(String elementLocator) {
        LogUtil.info("Opening drawer: " + elementLocator);
        executeDrawerCommand("mobile:openDrawer", elementLocator);
    }

    /**
     * Close a navigation drawer
     * @param elementLocator Locator for the drawer element
     */
    public void close(String elementLocator) {
        LogUtil.info("Closing drawer: " + elementLocator);
        executeDrawerCommand("mobile:closeDrawer", elementLocator);
    }

    /**
     * Execute a drawer command
     */
    private void executeDrawerCommand(String command, String elementLocator) {
        WebElement drawerElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = Map.of("element", drawerElement);
        androidDriver.executeScript(command, args);
    }
}
