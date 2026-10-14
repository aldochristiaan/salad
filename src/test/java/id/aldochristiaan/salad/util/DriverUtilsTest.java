package id.aldochristiaan.salad.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link DriverUtils}.
 * <p>
 * Uses a {@link Proxy}-based fake {@link WebElement} so no mocking library (or device) is needed.
 */
class DriverUtilsTest {

    /**
     * Builds a fake WebElement whose getAttribute() answers from the given map.
     */
    private static WebElement fakeElement(Map<String, String> attributes) {
        return (WebElement) Proxy.newProxyInstance(
                WebElement.class.getClassLoader(),
                new Class<?>[]{WebElement.class},
                (proxy, method, args) -> {
                    if ("getAttribute".equals(method.getName())) {
                        return attributes.get(args[0]);
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
    @DisplayName("getBooleanAttribute parses true/false attributes")
    void getBooleanAttribute() {
        Map<String, String> attrs = new HashMap<>();
        attrs.put("enabled", "true");
        attrs.put("selected", "false");
        attrs.put("checked", "null");

        assertTrue(DriverUtils.getBooleanAttribute(fakeElement(attrs), "enabled"));
        assertFalse(DriverUtils.getBooleanAttribute(fakeElement(attrs), "selected"));
        // Missing/odd values parse as false rather than throwing.
        assertFalse(DriverUtils.getBooleanAttribute(fakeElement(attrs), "checked"));
        assertFalse(DriverUtils.getBooleanAttribute(fakeElement(attrs), "missing"));
    }

    @Test
    @DisplayName("getStringAttribute returns the raw attribute value")
    void getStringAttribute() {
        Map<String, String> attrs = new HashMap<>();
        attrs.put("content-desc", "More options");

        assertEquals("More options", DriverUtils.getStringAttribute(fakeElement(attrs), "content-desc"));
        assertNull(DriverUtils.getStringAttribute(fakeElement(attrs), "missing"));
    }

    @Test
    @DisplayName("delay returns after the given milliseconds")
    void delayCompletes() {
        long start = System.currentTimeMillis();
        DriverUtils.delay(50);
        assertTrue(System.currentTimeMillis() - start >= 40, "delay should wait ~50ms");
    }

    @Test
    @DisplayName("Dimension exposes width and height")
    void dimensionGetters() {
        DriverUtils.Dimension dimension = new DriverUtils.Dimension(1080, 1920);

        assertEquals(1080, dimension.getWidth());
        assertEquals(1920, dimension.getHeight());
    }
}
