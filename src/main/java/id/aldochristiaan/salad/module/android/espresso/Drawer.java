package id.aldochristiaan.salad.module.android.espresso;

import id.aldochristiaan.salad.module.Espresso;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class Drawer extends Espresso {

    public Drawer(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void open(String elementLocator) {
        WebElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        androidDriver.executeScript("mobile:openDrawer", args);
    }

    public void close(String elementLocator) {
        WebElement androidElement = androidDriver.findElement(getLocator(elementLocator));
        HashMap<String, Object> args = new HashMap<>();
        args.put("element", androidElement);
        androidDriver.executeScript("mobile:closeDrawer", args);
    }
}
