package id.aldochristiaan.salad.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ElementValidator} using a Proxy-based fake WebElement.
 */
class ElementValidatorTest {

    private final ElementValidator validator = new ElementValidator();

    private static WebElement fakeElement(Map<String, String> attributes) {
        return (WebElement) Proxy.newProxyInstance(
                WebElement.class.getClassLoader(),
                new Class<?>[]{WebElement.class},
                (proxy, method, args) -> {
                    if ("getAttribute".equals(method.getName())) {
                        return attributes.get(args[0]);
                    }
                    if ("isDisplayed".equals(method.getName())) {
                        return Boolean.parseBoolean(attributes.getOrDefault("isDisplayed", "false"));
                    }
                    if ("toString".equals(method.getName())) {
                        return "FakeElement" + attributes;
                    }
                    if ("hashCode".equals(method.getName())) {
                        return System.identityHashCode(proxy);
                    }
                    if ("equals".equals(method.getName())) {
                        return proxy == args[0];
                    }
                    return null;
                });
    }

    @Test
    @DisplayName("validateVisible passes for a visible element, fails otherwise")
    void validateVisible() {
        Map<String, String> visible = new HashMap<>();
        visible.put("isDisplayed", "true");
        assertDoesNotThrow(() -> validator.validateVisible(fakeElement(visible), "btn"));

        assertThrows(AssertionError.class,
                () -> validator.validateVisible(fakeElement(new HashMap<>()), "btn"));
    }

    @Test
    @DisplayName("validateText supports exact and contains matching")
    void validateText() {
        assertDoesNotThrow(() -> validator.validateText("Hello", "Hello", true, "label"));
        assertThrows(AssertionError.class, () -> validator.validateText("Hello", "Hello!", true, "label"));

        assertDoesNotThrow(() -> validator.validateText("Hello", "Hello World", false, "label"));
        assertThrows(AssertionError.class, () -> validator.validateText("Bye", "Hello World", false, "label"));
    }

    @Test
    @DisplayName("validateEnabled/validateDisabled read the enabled attribute")
    void validateEnabledDisabled() {
        Map<String, String> enabled = new HashMap<>();
        enabled.put("enabled", "true");

        assertDoesNotThrow(() -> validator.validateEnabled(fakeElement(enabled), "enabled msg"));
        assertThrows(AssertionError.class, () -> validator.validateDisabled(fakeElement(enabled), "disabled msg"));
    }

    @Test
    @DisplayName("validateSelected/validateNotSelected read the selected attribute")
    void validateSelected() {
        Map<String, String> selected = new HashMap<>();
        selected.put("selected", "true");

        assertDoesNotThrow(() -> validator.validateSelected(fakeElement(selected), "selected msg"));
        assertThrows(AssertionError.class, () -> validator.validateNotSelected(fakeElement(selected), "msg"));
    }

    @Test
    @DisplayName("validateChecked reads the checked attribute")
    void validateChecked() {
        Map<String, String> checked = new HashMap<>();
        checked.put("checked", "true");

        assertDoesNotThrow(() -> validator.validateChecked(fakeElement(checked), "checked msg"));
    }

    @Test
    @DisplayName("validateExist/validateNotExist check the boolean flag")
    void validateExist() {
        assertDoesNotThrow(() -> validator.validateExist(true, "exists msg"));
        assertThrows(AssertionError.class, () -> validator.validateExist(false, "exists msg"));

        assertDoesNotThrow(() -> validator.validateNotExist(false, "not exists msg"));
        assertThrows(AssertionError.class, () -> validator.validateNotExist(true, "not exists msg"));
    }

    @Test
    @DisplayName("getValidateValue exposes the underlying validator")
    void exposesValidateValue() {
        assertNotNull(validator.getValidateValue());
        assertInstanceOf(ValidateValue.class, validator.getValidateValue());
    }
}
