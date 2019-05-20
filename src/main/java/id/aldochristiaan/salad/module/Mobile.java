package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static id.aldochristiaan.salad.Salad.ELEMENT_PROPERTIES;

public class Mobile {

    protected By getLocator(String elementLocator) {
        String elementValue = ELEMENT_PROPERTIES.getProperty(elementLocator);
        String[] locator = elementValue.split("_");
        String locatorType = locator[0];
        String locatorValue = elementValue.substring(elementValue.indexOf("_") + 1);

        switch (locatorType) {
            case "id":
                return By.id(locatorValue);
            case "accessibilityId":
                return MobileBy.AccessibilityId(locatorValue);
            case "name":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("name == '" + locatorValue + "'");
            case "label":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("label == '" + locatorValue + "'");
            case "value":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("value == '" + locatorValue + "'");
            case "labelcontains":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("label CONTAINS '" + locatorValue + "'");
            case "xpath":
                return By.xpath(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "text":
                return By.xpath("/*//*[@text=\"" + locatorValue + "\"]");
            case "containsText":
                return By.xpath("/*//*[contains(@text, '" + locatorValue + "')]");
            case "translationText":
                return By.xpath("//*[contains(@text,'" + locatorValue + "') or contains(@text, " +
                        "translate('" + locatorValue + "', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')) or " +
                        "contains(@text, translate('" + locatorValue + "', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]");
            default:
                LogUtil.error("Couldn't find locator : " + locatorValue + " ! Please check properties file!");
                throw new NoSuchElementException("Couldn't find locator : " + locatorValue);
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
}
