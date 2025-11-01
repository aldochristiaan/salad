package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.util.LogUtil;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Locale;
import java.util.Optional;

import static id.aldochristiaan.salad.Salad.ELEMENT_PROPERTIES;

public class Mobile {

    protected By getLocator(String elementLocator) {
        String elementValue = ELEMENT_PROPERTIES.getProperty(elementLocator);
        if (elementValue == null) {
            logMissingLocator(elementLocator);
        }

        String[] locatorParts = elementValue.split("_", 2);
        if (locatorParts.length != 2) {
            LogUtil.error("Invalid locator format for: " + elementLocator);
            throw new NoSuchElementException("Invalid locator format for: " + elementLocator);
        }

        String locatorType = locatorParts[0];
        String locatorValue = locatorParts[1];

        return switch (locatorType) {
            case "id" -> AppiumBy.id(locatorValue);
            case "accessibilityId" -> AppiumBy.accessibilityId(locatorValue);
            case "contentDescription" -> AppiumBy.xpath("//*[@content-desc='" + locatorValue + "']");
            case "name" -> AppiumBy.ByIosNsPredicate.iOSNsPredicateString("name == '" + locatorValue + "'");
            case "label" -> AppiumBy.ByIosNsPredicate.iOSNsPredicateString("label == '" + locatorValue + "'");
            case "value" -> AppiumBy.ByIosNsPredicate.iOSNsPredicateString("value == '" + locatorValue + "'");
            case "labelcontains" -> AppiumBy.ByIosNsPredicate.iOSNsPredicateString("label CONTAINS '" + locatorValue + "'");
            case "viewTag" -> AppiumBy.androidViewTag(locatorValue);
            case "xpath" -> AppiumBy.xpath(locatorValue);
            case "class" -> AppiumBy.className(locatorValue);
            case "text" -> AppiumBy.xpath("//*[@text='" + locatorValue + "']");
            case "containsText" -> AppiumBy.xpath("//*[contains(@text, '" + locatorValue + "')]");
            case "translationText" -> AppiumBy.xpath(
                    "//*[contains(@text,'" + locatorValue + "') or " +
                            "contains(@text, translate('" + locatorValue + "', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')) or " +
                            "contains(@text, translate('" + locatorValue + "', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]"
            );
            default -> throw new NoSuchElementException("Unsupported locator type: " + locatorType);
        };
    }

    protected String getWebLocator(String webLocator) {
        return Optional.ofNullable(ELEMENT_PROPERTIES.getProperty(webLocator))
                .orElseThrow(() -> {
                    logMissingLocator(webLocator);
                    return new NoSuchElementException("Couldn't find locator: " + webLocator);
                });
    }

    protected String constructLocator(String elementLocator, Object... args) {
        String elementValue = ELEMENT_PROPERTIES.getProperty(elementLocator);
        if (elementValue == null) {
            logMissingLocator(elementLocator);
        }

        String constructedValue = String.format(elementValue, args);
        String constructedLocator = "TEMP_" + elementLocator;

        ELEMENT_PROPERTIES.remove(constructedLocator); // Safe even if key doesn't exist
        ELEMENT_PROPERTIES.setProperty(constructedLocator, constructedValue);

        return constructedLocator;
    }

    protected void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtil.error("Thread was interrupted during delay", e);
        }
    }

    private void logMissingLocator(String key) {
        LogUtil.error("Couldn't find locator: " + key + " ! Please check properties file!");
    }
}
