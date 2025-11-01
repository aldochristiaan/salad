package id.aldochristiaan.salad.module.android.uiautomator2;

import id.aldochristiaan.salad.module.UiAutomator2;
import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.android.AndroidDriver;

import java.util.Set;

public class ChangeContext extends UiAutomator2 {

    public ChangeContext(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void toWebView() {
        switchContext("WEBVIEW");
    }

    public void toNative() {
        switchContext("NATIVE_APP");
    }

    private void switchContext(String targetContextPrefix) {
        Set<String> contextHandles = androidDriver.getContextHandles();
        for (String context : contextHandles) {
            LogUtil.info("Available Context: " + context);
            if (context.contains(targetContextPrefix)) {
                LogUtil.info("Switching to context: " + context);
                androidDriver.context(context);
                return;
            }
        }
        throw new IllegalStateException("No context found for: " + targetContextPrefix);
    }
}
