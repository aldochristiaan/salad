package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.XCUITest;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;

/**
 * iOS deeplink utilities.
 * Provides methods for opening deeplinks in iOS applications.
 */
public class Deeplink extends XCUITest {

    public Deeplink(IOSDriver iosDriver) {
        super(iosDriver);
    }

    /**
     * Open a deeplink URL
     * @param deeplink The deeplink URL to open (e.g., "myapp://screen/action")
     */
    public void open(String deeplink) {
        if (deeplink == null || deeplink.trim().isEmpty()) {
            throw new IllegalArgumentException("Deeplink URL cannot be null or empty");
        }
        LogUtil.info("Opening deeplink: " + deeplink);
        iosDriver.get(deeplink);
    }

    /**
     * Open a deeplink with custom scheme
     * @param scheme App URL scheme (e.g., "myapp")
     * @param path Path within the app (e.g., "screen/action")
     */
    public void open(String scheme, String path) {
        String deeplink = scheme + "://" + path;
        open(deeplink);
    }
}
