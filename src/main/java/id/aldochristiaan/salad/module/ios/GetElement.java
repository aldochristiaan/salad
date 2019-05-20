package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import id.aldochristiaan.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GetElement extends Ios {

    public GetElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public IOSElement withLocator(String elementLocator) {
        return findElementBy(getLocator(elementLocator));
    }

    public IOSElement withLocator(String elementLocator, int timeout) {
        return findElementBy(getLocator(elementLocator), timeout);
    }

    public IOSElement withLocator(String elementLocator, Direction direction) {
        return findElementBy(getLocator(elementLocator), direction);
    }
}
