package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.util.DriverUtils;
import id.aldochristiaan.salad.util.LogUtil;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Optional;

import static id.aldochristiaan.salad.Salad.ELEMENT_PROPERTIES;

/**
 * Base class for mobile automation modules
 */
public abstract class Mobile {

    private final PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

    /**
     * Get locator from properties file
     */
    protected By getLocator(String elementLocator) {
        String elementValue = getPropertyValue(elementLocator);

        String[] locatorParts = elementValue.split("_", 2);
        if (locatorParts.length != 2) {
            String error = "Invalid locator format for: " + elementLocator + ". Expected format: type_value";
            LogUtil.error(error);
            throw new NoSuchElementException(error);
        }

        String locatorType = locatorParts[0];
        String locatorValue = locatorParts[1];

        return createLocator(locatorType, locatorValue);
    }

    /**
     * Create appropriate locator based on type
     */
    private By createLocator(String locatorType, String locatorValue) {
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
            case "translationText" -> AppiumBy.xpath(buildTranslationXPath(locatorValue));
            default -> throw new NoSuchElementException("Unsupported locator type: " + locatorType);
        };
    }

    /**
     * Build XPath for case-insensitive text search
     */
    private String buildTranslationXPath(String text) {
        return String.format(
            "//*[contains(@text,'%s') or " +
            "contains(@text, translate('%s', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')) or " +
            "contains(@text, translate('%s', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]",
            text, text, text
        );
    }

    /**
     * Get web locator string from properties
     */
    protected String getWebLocator(String webLocator) {
        return Optional.ofNullable(getPropertyValue(webLocator))
                .orElseThrow(() -> {
                    logMissingLocator(webLocator);
                    return new NoSuchElementException("Couldn't find locator: " + webLocator);
                });
    }

    /**
     * Construct dynamic locator with parameters
     */
    protected String constructLocator(String elementLocator, Object... args) {
        String elementValue = getPropertyValue(elementLocator);
        String constructedValue = String.format(elementValue, args);
        String constructedLocator = "TEMP_" + elementLocator;

        propertiesLoader.removeProperty(constructedLocator);
        propertiesLoader.setProperty(constructedLocator, constructedValue);

        return constructedLocator;
    }

    /**
     * Get property value with backward compatibility
     */
    private String getPropertyValue(String key) {
        String value = propertiesLoader.getProperty(key);

        // Backward compatibility
        if (value == null && ELEMENT_PROPERTIES != null) {
            value = ELEMENT_PROPERTIES.getProperty(key);
        }

        if (value == null) {
            logMissingLocator(key);
            throw new NoSuchElementException("Couldn't find locator: " + key);
        }

        return value;
    }

    /**
     * Thread-safe delay
     */
    protected void delay(int milliseconds) {
        DriverUtils.delay(milliseconds);
    }

    /**
     * Log missing locator error
     */
    private void logMissingLocator(String key) {
        LogUtil.error("Couldn't find locator: " + key + " ! Please check properties file!");
    }
}
