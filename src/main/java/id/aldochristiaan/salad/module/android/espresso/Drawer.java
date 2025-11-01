package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class Drawer extends Espresso {

    public Drawer(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void open(String elementLocator) {
        executeDrawerCommand("mobile:openDrawer", elementLocator);
    }

    public void close(String elementLocator) {
        executeDrawerCommand("mobile:closeDrawer", elementLocator);
    }

    private void executeDrawerCommand(String command, String elementLocator) {
        WebElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        Map<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        androidDriver.executeScript(command, args);
    }
}
