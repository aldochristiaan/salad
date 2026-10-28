package ios;

import io.appium.java_client.ios.IOSDriver;
import ios.pages.IosShowcasePage;

/**
 * Root page object for iOS tests. Extending the showcase page exposes every XCUITest module
 * method directly on the {@code ios} handle tests use.
 */
public class Ios extends IosShowcasePage {

    public Ios(IOSDriver iosDriver) {
        super(iosDriver);
    }
}
