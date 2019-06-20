package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class Deeplink extends Ios {

    public Deeplink(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void open(String deeplink){
        iosDriver.get(deeplink);
    }
}
