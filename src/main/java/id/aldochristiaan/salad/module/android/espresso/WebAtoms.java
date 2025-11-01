package id.aldochristiaan.salad.module.android.espresso;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.AtomAction;
import id.aldochristiaan.salad.util.WebLocator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebAtoms extends Espresso {

    public WebAtoms(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void click(String webViewLocator, WebLocator locator, String webLocator) {
        performWebAtom(webViewLocator, locator, webLocator, AtomAction.webClick, null, "click");
    }

    public void clear(String webViewLocator, WebLocator locator, String webLocator) {
        performWebAtom(webViewLocator, locator, webLocator, AtomAction.clearElement, null, "clear");
    }

    public void type(String webViewLocator, WebLocator locator, String webLocator, String text) {
        performWebAtom(webViewLocator, locator, webLocator, AtomAction.webKeys, text, "type");
        hideKeyboard();
    }

    public void findElement(String webViewLocator, WebLocator locator, String webLocator) {
        try {
            List<Map<String, Object>> methodChain = buildMethodChain(locator, webLocator, null, null);
            executeWebAtom(webViewLocator, methodChain);
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't find this web element: " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }


    public void scrollIntoView(String webViewLocator, WebLocator locator, String webLocator) {
        performWebAtom(webViewLocator, locator, webLocator, AtomAction.webScrollIntoView, null, "scroll into");
    }

    private void performWebAtom(String webViewLocator, WebLocator locator, String webLocator,
                                AtomAction action, String args, String actionName) {
        try {
            List<Map<String, Object>> methodChain = buildMethodChain(locator, webLocator, action, args);
            executeWebAtom(webViewLocator, methodChain);
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't " + actionName + " this web element: " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }

    private List<Map<String, Object>> buildMethodChain(WebLocator locator, String webLocator,
                                                       AtomAction action, String args) {
        Map<String, Object> findElementAtom = ImmutableMap.of(
                "name", "findElement",
                "locator", ImmutableMap.of(
                        "using", locator.toString(),
                        "value", getWebLocator(webLocator)
                )
        );

        Map<String, Object> withElement = ImmutableMap.of(
                "name", "withElement",
                "atom", findElementAtom
        );

        if (action == null) {
            return Arrays.asList(withElement);
        }

        Map<String, Object> performAtom = args == null
                ? ImmutableMap.of("name", action.toString())
                : ImmutableMap.of("name", action.toString(), "args", args);

        Map<String, Object> perform = ImmutableMap.of(
                "name", "perform",
                "atom", performAtom
        );

        return Arrays.asList(withElement, perform);
    }

    private void executeWebAtom(String webViewLocator, List<Map<String, Object>> methodChain) {
        ImmutableMap<String, Object> args = ImmutableMap.of(
                "webviewElement", ((RemoteWebElement) androidDriver.findElement(getLocator(webViewLocator))).getId(),
                "forceJavascriptEnabled", true,
                "methodChain", methodChain
        );
        androidDriver.executeScript("mobile:webAtoms", args);
    }
}
