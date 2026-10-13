package id.aldochristiaan.salad.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ValidateValue}.
 */
class ValidateValueTest {

    private final ValidateValue validateValue = new ValidateValue();

    @Test
    @DisplayName("equals(String) passes on match and fails with AssertionError on mismatch")
    void equalsString() {
        assertDoesNotThrow(() -> validateValue.equals("a", "a"));
        AssertionError error = assertThrows(AssertionError.class, () -> validateValue.equals("a", "b"));
        assertTrue(error.getMessage().contains("a"));
        assertTrue(error.getMessage().contains("b"));
    }

    @Test
    @DisplayName("equals(String) with message uses the custom message")
    void equalsStringWithMessage() {
        AssertionError error = assertThrows(AssertionError.class,
                () -> validateValue.equals("x", "y", "custom message"));
        assertEquals("custom message", error.getMessage());
    }

    @Test
    @DisplayName("equals(String) treats nulls as equal via Objects.equals")
    void equalsStringNullSafe() {
        assertDoesNotThrow(() -> validateValue.equals(null, null, "both null"));
        assertThrows(AssertionError.class, () -> validateValue.equals(null, "v", "null vs value"));
    }

    @Test
    @DisplayName("boolean/int/long overloads compare correctly")
    void primitiveOverloads() {
        assertDoesNotThrow(() -> validateValue.equals(true, true));
        assertThrows(AssertionError.class, () -> validateValue.equals(true, false));
        assertDoesNotThrow(() -> validateValue.equals(1, 1));
        assertThrows(AssertionError.class, () -> validateValue.equals(1, 2));
        assertDoesNotThrow(() -> validateValue.equals(5L, 5L));
        assertThrows(AssertionError.class, () -> validateValue.equals(5L, 6L));
    }

    @Test
    @DisplayName("double comparison honours the delta")
    void doubleComparisonHonoursDelta() {
        assertDoesNotThrow(() -> validateValue.equals(1.0, 1.05, 0.1));
        assertThrows(AssertionError.class, () -> validateValue.equals(1.0, 1.5, 0.1));
        assertDoesNotThrow(() -> validateValue.notEquals(1.0, 1.5, 0.1));
        assertThrows(AssertionError.class, () -> validateValue.notEquals(1.0, 1.05, 0.1));
    }

    @Test
    @DisplayName("notEquals variants fail when values are the same")
    void notEqualsVariants() {
        assertDoesNotThrow(() -> validateValue.notEquals("a", "b"));
        assertThrows(AssertionError.class, () -> validateValue.notEquals("a", "a"));
        assertThrows(AssertionError.class, () -> validateValue.notEquals(true, true));
        assertThrows(AssertionError.class, () -> validateValue.notEquals(1, 1));
        assertThrows(AssertionError.class, () -> validateValue.notEquals(1L, 1L));
    }

    @Test
    @DisplayName("equalsTrue and equalsFalse check the condition")
    void trueFalseChecks() {
        assertDoesNotThrow(() -> validateValue.equalsTrue(true));
        assertThrows(AssertionError.class, () -> validateValue.equalsTrue(false));
        assertDoesNotThrow(() -> validateValue.equalsFalse(false));
        assertThrows(AssertionError.class, () -> validateValue.equalsFalse(true));
    }

    @Test
    @DisplayName("contains checks substring presence and is null-safe")
    void containsChecks() {
        assertDoesNotThrow(() -> validateValue.contains("ell", "hello"));
        assertThrows(AssertionError.class, () -> validateValue.contains("xyz", "hello"));
        assertThrows(AssertionError.class, () -> validateValue.contains("ell", null));

        assertTrue(validateValue.isContains("ell", "hello"));
        assertFalse(validateValue.isContains("xyz", "hello"));
        assertFalse(validateValue.isContains("ell", null));
    }
}
