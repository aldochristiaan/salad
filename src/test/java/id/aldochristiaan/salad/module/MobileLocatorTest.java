package id.aldochristiaan.salad.module;

import id.aldochristiaan.salad.util.PropertiesLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Mobile} locator construction.
 * <p>
 * Uses a minimal concrete subclass since {@code Mobile} is abstract and its locator
 * methods are protected.
 */
class MobileLocatorTest {

    /**
     * Exposes the protected locator methods for testing.
     */
    private static class TestMobile extends Mobile {
        By locator(String key) {
            return getLocator(key);
        }

        String webLocator(String key) {
            return getWebLocator(key);
        }

        String dynamicLocator(String key, Object... args) {
            return constructLocator(key, args);
        }
    }

    private final TestMobile mobile = new TestMobile();

    @BeforeEach
    void setUp() {
        // NOTE: do NOT call PropertiesLoader.resetInstance() here. Mobile caches the
        // singleton reference in a field at construction time, so resetting would make
        // the instance under test read from a different (stale) PropertiesLoader.
        // Clearing + re-populating the shared instance keeps both sides in sync.
        PropertiesLoader loader = PropertiesLoader.getInstance();
        loader.clear();
        loader.setProperty("BUTTON_OK", "id_com.example:id/ok");
        loader.setProperty("MORE_OPTIONS", "contentDescription_More options");
        loader.setProperty("TITLE", "text_Home");
        loader.setProperty("CONTAINS_TITLE", "containsText_elcome");
        loader.setProperty("MY_VIEW", "viewTag_myView");
        loader.setProperty("LIST_ITEM", "xpath_//android.widget.TextView[@text='a,b']");
        loader.setProperty("WEB_LINK", "https://example.com");
    }

    @Test
    @DisplayName("id locator type resolves via AppiumBy.id")
    void idLocator() {
        By by = mobile.locator("BUTTON_OK");
        assertNotNull(by);
        assertTrue(by.toString().contains("com.example:id/ok"), "Locator should carry the id value: " + by);
    }

    @Test
    @DisplayName("contentDescription becomes a content-desc XPath")
    void contentDescriptionLocator() {
        By by = mobile.locator("MORE_OPTIONS");
        assertTrue(by.toString().contains("@content-desc='More options'"), "Got: " + by);
    }

    @Test
    @DisplayName("text becomes an @text XPath")
    void textLocator() {
        By by = mobile.locator("TITLE");
        assertTrue(by.toString().contains("@text='Home'"), "Got: " + by);
    }

    @Test
    @DisplayName("containsText becomes a contains(@text) XPath")
    void containsTextLocator() {
        By by = mobile.locator("CONTAINS_TITLE");
        assertTrue(by.toString().contains("contains(@text"), "Got: " + by);
    }

    @Test
    @DisplayName("values containing underscores are not split more than once")
    void underscoreInValueIsPreserved() {
        // Value is "com.example:id/ok_button" — only the FIRST underscore separates type/value.
        PropertiesLoader.getInstance().setProperty("UNDERSCORED", "id_com.example:id/ok_button");
        By by = mobile.locator("UNDERSCORED");
        assertTrue(by.toString().contains("com.example:id/ok_button"), "Got: " + by);
    }

    @Test
    @DisplayName("missing locator throws NoSuchElementException")
    void missingLocatorThrows() {
        assertThrows(NoSuchElementException.class, () -> mobile.locator("DOES_NOT_EXIST"));
    }

    @Test
    @DisplayName("malformed locator (no type_value format) throws with a clear message")
    void malformedLocatorThrows() {
        PropertiesLoader.getInstance().setProperty("BAD", "justavalue");
        NoSuchElementException error = assertThrows(NoSuchElementException.class,
                () -> mobile.locator("BAD"));
        assertTrue(error.getMessage().contains("Invalid locator format"), "Got: " + error.getMessage());
    }

    @Test
    @DisplayName("unsupported locator type throws with a clear message")
    void unsupportedTypeThrows() {
        PropertiesLoader.getInstance().setProperty("WEIRD", "notAType_value");
        NoSuchElementException error = assertThrows(NoSuchElementException.class,
                () -> mobile.locator("WEIRD"));
        assertTrue(error.getMessage().contains("Unsupported locator type"), "Got: " + error.getMessage());
    }

    @Test
    @DisplayName("getWebLocator returns the raw value")
    void getWebLocatorReturnsValue() {
        assertEquals("https://example.com", mobile.webLocator("WEB_LINK"));
    }

    @Test
    @DisplayName("getWebLocator throws for a missing key")
    void getWebLocatorMissingThrows() {
        assertThrows(NoSuchElementException.class, () -> mobile.webLocator("NOPE"));
    }

    @Test
    @DisplayName("constructLocator formats the value with arguments and registers a TEMP key")
    void constructLocatorFormatsAndRegisters() {
        PropertiesLoader.getInstance().setProperty("ITEM_TEMPLATE", "xpath_//android.widget.TextView[@text='%s']");

        String key = mobile.dynamicLocator("ITEM_TEMPLATE", "Hello");

        assertTrue(key.startsWith("TEMP_"), "Should return a TEMP_ key, got: " + key);
        String registered = PropertiesLoader.getInstance().getProperty(key);
        assertNotNull(registered, "Constructed locator must be registered");
        assertTrue(registered.contains("Hello"), "Args must be substituted, got: " + registered);
    }
}
