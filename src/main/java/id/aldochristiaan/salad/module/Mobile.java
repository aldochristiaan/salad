package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static id.aldochristiaan.salad.Salad.ELEMENT_PROPERTIES;

public class Mobile {

    protected By getLocator(String elementLocator) {

        String elementValue = ELEMENT_PROPERTIES.getProperty(elementLocator);
        if (elementValue == null) {
            LogUtil.error("Couldn't find locator : " + elementLocator + " ! Please check properties file!");
            throw new NoSuchElementException("Couldn't find locator : " + elementLocator);
        }
        String[] locator = elementValue.split("_");
        String locatorType = locator[0];
        String locatorValue = elementValue.substring(elementValue.indexOf("_") + 1);

        switch (locatorType) {
            case "id":
                return AppiumBy.id(locatorValue);
            case "accessibilityId":
                return AppiumBy.accessibilityId(locatorValue);
            case "contentDescription":
                return AppiumBy.xpath("//*[@content-desc='" + locatorValue + "']");
            case "name":
                return AppiumBy.ByIosNsPredicate.iOSNsPredicateString("name == '" + locatorValue + "'");
            case "label":
                return AppiumBy.ByIosNsPredicate.iOSNsPredicateString("label == '" + locatorValue + "'");
            case "value":
                return AppiumBy.ByIosNsPredicate.iOSNsPredicateString("value == '" + locatorValue + "'");
            case "labelcontains":
                return AppiumBy.ByIosNsPredicate.iOSNsPredicateString("label CONTAINS '" + locatorValue + "'");
            case "viewTag":
                return AppiumBy.androidViewTag(locatorValue);
            case "xpath":
                return AppiumBy.xpath(locatorValue);
            case "class":
                return AppiumBy.className(locatorValue);
            case "text":
                return AppiumBy.xpath("//*[@text='" + locatorValue + "']");
            case "containsText":
                return AppiumBy.xpath("//*[contains(@text, '" + locatorValue + "')]");
            case "translationText":
                return AppiumBy.xpath("//*[contains(@text,'" + locatorValue + "') or contains(@text, " +
                        "translate('" + locatorValue + "', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')) or " +
                        "contains(@text, translate('" + locatorValue + "', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]");
            default:
                return null;
        }
    }

    protected String getWebLocator(String webLocator) {
        String elementValue = ELEMENT_PROPERTIES.getProperty(webLocator);
        if (elementValue == null) {
            LogUtil.error("Couldn't find locator : " + webLocator + " ! Please check properties file!");
            throw new NoSuchElementException("Couldn't find locator : " + webLocator);
        } else {
            return elementValue;
        }
    }

    protected String constructLocator(String elementLocator, Object... args) {
        String elementValue = ELEMENT_PROPERTIES.getProperty(elementLocator);
        String constructedValue = String.format(elementValue, args);
        String constructedLocator = "TEMP_" + elementLocator;
        try {
            ELEMENT_PROPERTIES.remove(constructedLocator);
        } catch (NullPointerException e) {
            LogUtil.info("No properties key was found!");
        }
        ELEMENT_PROPERTIES.setProperty(
                constructedLocator,
                constructedValue
        );
        return constructedLocator;
    }

    protected void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
