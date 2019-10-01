package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class Tap extends XCUITest {

    public Tap(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void element(String elementLocator) {
        findElementBy(getLocator(elementLocator)).click();
    }

    public void element(String elementLocator, int index) {
        findElementsBy(getLocator(elementLocator)).get(index).click();
    }

    public void location(int x, int y, Duration time) {
        TouchAction touchAction = new TouchAction(iosDriver);
        touchAction.press(new PointOption().withCoordinates(x, y)).waitAction(new WaitOptions().withDuration(time)).release().perform();
    }
}
