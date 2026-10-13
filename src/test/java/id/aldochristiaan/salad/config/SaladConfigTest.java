package id.aldochristiaan.salad.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link SaladConfig}.
 */
class SaladConfigTest {

    @Test
    @DisplayName("toDuration converts seconds to Duration")
    void toDuration() {
        assertEquals(Duration.ofSeconds(30), SaladConfig.toDuration(SaladConfig.DEFAULT_TIMEOUT));
        assertEquals(Duration.ofSeconds(60), SaladConfig.toDuration(SaladConfig.DURATION_TIMEOUT));
    }

    @Test
    @DisplayName("toMillisDuration converts milliseconds to Duration")
    void toMillisDuration() {
        assertEquals(Duration.ofMillis(200), SaladConfig.toMillisDuration(SaladConfig.DEFAULT_POLLING_INTERVAL_MS));
    }

    @Test
    @DisplayName("constants hold expected values")
    void constants() {
        assertEquals(30, SaladConfig.DEFAULT_TIMEOUT);
        assertEquals(60, SaladConfig.DURATION_TIMEOUT);
        assertEquals(15, SaladConfig.MAX_SWIPE_COUNT);
        assertEquals(200, SaladConfig.DEFAULT_POLLING_INTERVAL_MS);
        assertEquals("screenshot", SaladConfig.DEFAULT_SCREENSHOT_DIR);
        assertEquals(".png", SaladConfig.SCREENSHOT_EXTENSION);
        assertEquals("/wd/hub", SaladConfig.APPIUM_HUB_PATH);
    }

    @Test
    @DisplayName("constructor is private and cannot be instantiated")
    void cannotInstantiate() {
        var constructor = SaladConfig.class.getDeclaredConstructors()[0];
        assertTrue(java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()));
    }
}
