package id.aldochristiaan.salad.util;

import id.aldochristiaan.salad.module.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.Set;

public class ChangeContext extends Android {

    public ChangeContext(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void toWebView() {
        Set<String> contextHandles = androidDriver.getContextHandles();

        for (String s : contextHandles) {
            LogUtil.info("Context : " + s);
            if (s.contains("WEBVIEW")) {
                androidDriver.context(s);
            }
        }
    }

    public void toNative() {
        Set<String> contextHandles = androidDriver.getContextHandles();

        for (String s : contextHandles) {
            LogUtil.info("Context : " + s);
            if (s.contains("NATIVE_APP")) {
                androidDriver.context(s);
            }
        }
    }
}
