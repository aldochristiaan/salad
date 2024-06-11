package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

public class GetElement extends XCUITest {

    public GetElement(IOSDriver iosDriver) {
        super(iosDriver);
    }

    public WebElement withLocator(String elementLocator) {
        return findElementBy(getLocator(elementLocator));
    }

    public WebElement withLocator(String elementLocator, int timeout) {
        return findElementBy(getLocator(elementLocator), timeout);
    }

    public WebElement withLocator(String elementLocator, Direction direction) {
        return findElementBy(getLocator(elementLocator), direction);
    }
}
