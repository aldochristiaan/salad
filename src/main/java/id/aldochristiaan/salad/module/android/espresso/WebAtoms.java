package id.aldochristiaan.salad.module.android.espresso;

import com.google.common.collect.ImmutableMap;
import id.aldochristiaan.salad.module.Espresso;
import id.aldochristiaan.salad.util.AtomAction;
import id.aldochristiaan.salad.util.WebLocator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.Arrays;

public class WebAtoms extends Espresso {

    public WebAtoms(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    public void click(String webViewLocator, WebLocator locator, String webLocator) {
        try {
            ImmutableMap<String, Object> args = ImmutableMap.of(
                    "webviewElement", androidDriver.findElement(getLocator(webViewLocator)).getId(),
                    "forceJavascriptEnabled", true,
                    "methodChain", Arrays.asList(
                            ImmutableMap.of(
                                    "name", "withElement",
                                    "atom", ImmutableMap.of(
                                            "name", "findElement",
                                            "locator", ImmutableMap.of(
                                                    "using", locator.toString(),
                                                    "value", getWebLocator(webLocator)
                                            )
                                    )
                            ),
                            ImmutableMap.of(
                                    "name", "perform",
                                    "atom", AtomAction.webClick.toString()
                            ))
            );
            androidDriver.executeScript("mobile:webAtoms", args);
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't click this web element : " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }

    public void clear(String webViewLocator, WebLocator locator, String webLocator) {
        try {
            ImmutableMap<String, Object> args = ImmutableMap.of(
                    "webviewElement", androidDriver.findElement(getLocator(webViewLocator)).getId(),
                    "forceJavascriptEnabled", true,
                    "methodChain", Arrays.asList(
                            ImmutableMap.of(
                                    "name", "withElement",
                                    "atom", ImmutableMap.of(
                                            "name", "findElement",
                                            "locator", ImmutableMap.of(
                                                    "using", locator.toString(),
                                                    "value", getWebLocator(webLocator)
                                            )
                                    )
                            ),
                            ImmutableMap.of(
                                    "name", "perform",
                                    "atom", AtomAction.clearElement.toString()
                            ))
            );
            androidDriver.executeScript("mobile:webAtoms", args);
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't clear this web element : " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }

    public void type(String webViewLocator, WebLocator locator, String webLocator, String text) {
        try {
            ImmutableMap<String, Object> args = ImmutableMap.of(
                    "webviewElement", androidDriver.findElement(getLocator(webViewLocator)).getId(),
                    "forceJavascriptEnabled", true,
                    "methodChain", Arrays.asList(
                            ImmutableMap.of(
                                    "name", "withElement",
                                    "atom", ImmutableMap.of(
                                            "name", "findElement",
                                            "locator", ImmutableMap.of(
                                                    "using", locator.toString(),
                                                    "value", getWebLocator(webLocator)
                                            )
                                    )
                            ),
                            ImmutableMap.of(
                                    "name", "perform",
                                    "atom", ImmutableMap.of(
                                            "name", AtomAction.webKeys.toString(),
                                            "args", text
                                    )
                            ))
            );
            androidDriver.executeScript("mobile:webAtoms", args);
            hideKeyboard();
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't type on this web element : " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }

    public void findElement(String webViewLocator, WebLocator locator, String webLocator) {
        try {
            ImmutableMap<String, Object> args = ImmutableMap.of(
                    "webviewElement", androidDriver.findElement(getLocator(webViewLocator)).getId(),
                    "forceJavascriptEnabled", true,
                    "methodChain", Arrays.asList(
                            ImmutableMap.of(
                                    "name", "withElement",
                                    "atom", ImmutableMap.of(
                                            "name", "findElement",
                                            "locator", ImmutableMap.of(
                                                    "using", locator.toString(),
                                                    "value", getWebLocator(webLocator)
                                            )
                                    )
                            ))
            );
            androidDriver.executeScript("mobile:webAtoms", args);
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't find this web element : " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }

    public void scrollIntoView(String webViewLocator, WebLocator locator, String webLocator) {
        try {
            ImmutableMap<String, Object> args = ImmutableMap.of(
                    "webviewElement", androidDriver.findElement(getLocator(webViewLocator)).getId(),
                    "forceJavascriptEnabled", true,
                    "methodChain", Arrays.asList(
                            ImmutableMap.of(
                                    "name", "withElement",
                                    "atom", ImmutableMap.of(
                                            "name", "findElement",
                                            "locator", ImmutableMap.of(
                                                    "using", locator.toString(),
                                                    "value", getWebLocator(webLocator)
                                            )
                                    )
                            ),
                            ImmutableMap.of(
                                    "name", "perform",
                                    "atom", AtomAction.webScrollIntoView.toString()
                            ))
            );
            androidDriver.executeScript("mobile:webAtoms", args);
        } catch (Exception e) {
            throw new NoSuchElementException("Couldn't scroll into this web element : " + webLocator + " : " + getWebLocator(webLocator), e);
        }
    }
}
