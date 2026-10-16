package ios.pages;

import id.aldochristiaan.salad.module.XCUITest;
import io.appium.java_client.ios.IOSDriver;

/** Base page for all iOS showcase pages. */
public abstract class BaseIosPage extends XCUITest {

    public BaseIosPage(IOSDriver iosDriver) {
        super(iosDriver);
    }
}
