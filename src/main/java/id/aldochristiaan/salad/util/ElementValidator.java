package id.aldochristiaan.salad.util;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Centralized element validation utility
 */
public class ElementValidator {

    private final ValidateValue validateValue;

    public ElementValidator() {
        this.validateValue = new ValidateValue();
    }

    public void validateVisible(WebElement element, String elementName) {
        validateValue.equalsTrue(element.isDisplayed(), "Element not visible: " + elementName);
    }

    public void validateText(String expected, String actual, boolean exactMatch, String elementName) {
        if (exactMatch) {
            validateValue.equals(expected, actual, "Text mismatch for: " + elementName);
        } else {
            validateValue.contains(expected, actual, "Text not found in: " + elementName);
        }
    }

    public void validateEnabled(WebElement element, String msg) {
        validateValue.equalsTrue(DriverUtils.getBooleanAttribute(element, "enabled"), msg);
    }

    public void validateDisabled(WebElement element, String msg) {
        validateValue.equalsFalse(DriverUtils.getBooleanAttribute(element, "enabled"), msg);
    }

    public void validateSelected(WebElement element, String msg) {
        validateValue.equalsTrue(DriverUtils.getBooleanAttribute(element, "selected"), msg);
    }

    public void validateNotSelected(WebElement element, String msg) {
        validateValue.equalsFalse(DriverUtils.getBooleanAttribute(element, "selected"), msg);
    }

    public void validateChecked(WebElement element, String msg) {
        validateValue.equalsTrue(DriverUtils.getBooleanAttribute(element, "checked"), msg);
    }

    public void validateExist(boolean exists, String msg) {
        validateValue.equalsTrue(exists, msg);
    }

    public void validateNotExist(boolean exists, String msg) {
        validateValue.equalsFalse(exists, msg);
    }

    public void validateStaleness(WebDriver driver, WebElement element, int timeout) {
        validateValue.equalsTrue(
            new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.stalenessOf(element))
        );
    }

    public ValidateValue getValidateValue() {
        return validateValue;
    }
}

