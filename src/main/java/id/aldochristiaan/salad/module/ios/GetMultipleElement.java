package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetMultipleElement extends Ios {

    public GetMultipleElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public List<IOSElement> withLocator(String elementLocator) {
        return findElementsBy(getLocator(elementLocator));
    }

    public List<WebElement> withLocator(String elementLocator, int timeout) {
        return findElementsBy(getLocator(elementLocator), timeout);
    }
}
