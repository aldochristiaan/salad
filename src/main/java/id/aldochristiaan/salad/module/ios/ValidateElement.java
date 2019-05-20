package id.aldochristiaan.salad.module.ios;

import id.aldochristiaan.salad.module.Ios;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class ValidateElement extends Ios {

    public ValidateElement(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void presentByLocator(String elementLocator, int timeout) {
        Assert.assertTrue("Element with locator : " + elementLocator + " is not present!", checkElement().isPresentByLocator(elementLocator, timeout));
    }

    public void presentByLocator(String elementLocator, int timeout, String message) {
        Assert.assertTrue(message, checkElement().isPresentByLocator(elementLocator, timeout));
    }

    public void notPresentByLocator(String elementLocator, int timeout) {
        Assert.assertTrue("Element with locator : " + elementLocator + " is present!", checkElement().isNotPresentByLocator(elementLocator, timeout));
    }

    public void notPresentByLocator(String elementLocator, int timeout, String message) {
        Assert.assertTrue(message, checkElement().isNotPresentByLocator(elementLocator, timeout));
    }

    public void enabledByLocator(String elementLocator, int timeout) {
        Assert.assertTrue("Element with locator : " + elementLocator + " is disabled!", getElement().withLocator(elementLocator, timeout).isEnabled());
    }

    public void enabledByLocator(String elementLocator, int timeout, String message) {
        Assert.assertTrue(message, getElement().withLocator(elementLocator, timeout).isEnabled());
    }

    public void disabledByLocator(String elementLocator, int timeout) {
        Assert.assertFalse("Element with locator : " + elementLocator + " is enabled!", getElement().withLocator(elementLocator, timeout).isEnabled());
    }

    public void disabledByLocator(String elementLocator, int timeout, String message) {
        Assert.assertFalse(message, getElement().withLocator(elementLocator, timeout).isEnabled());
    }

    public void selectedByLocator(String elementLocator, int timeout) {
        Assert.assertTrue("Element with locator : " + elementLocator + " is not selected!", getElement().withLocator(elementLocator, timeout).isSelected());
    }

    public void selectedByLocator(String elementLocator, int timeout, String message) {
        Assert.assertTrue(message, getElement().withLocator(elementLocator, timeout).isSelected());
    }

    public void notSelectedByLocator(String elementLocator, int timeout) {
        Assert.assertFalse("Element with locator : " + elementLocator + " is selected!", getElement().withLocator(elementLocator, timeout).isSelected());
    }

    public void notSelectedByLocator(String elementLocator, int timeout, String message) {
        Assert.assertFalse(message, getElement().withLocator(elementLocator, timeout).isSelected());
    }

    public void checkedByLocator(String elementLocator, int timeout) {
        Assert.assertTrue("Element with locator : " + elementLocator + " is not checked!", Boolean.parseBoolean(getElement().withLocator(elementLocator, timeout).getAttribute("checked")));
    }

    public void checkedByLocator(String elementLocator, int timeout, String message) {
        Assert.assertTrue(message, getElement().withLocator(elementLocator, timeout).isSelected());
    }

    public void uncheckedByLocator(String elementLocator, int timeout) {
        Assert.assertFalse("Element with locator : " + elementLocator + " is checked!", Boolean.parseBoolean(getElement().withLocator(elementLocator, timeout).getAttribute("checked")));
    }

    public void uncheckedByLocator(String elementLocator, int timeout, String message) {
        Assert.assertFalse(message, getElement().withLocator(elementLocator, timeout).isSelected());
    }
}
