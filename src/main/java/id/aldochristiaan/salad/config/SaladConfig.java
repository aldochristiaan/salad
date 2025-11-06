package id.aldochristiaan.salad.config;

import java.time.Duration;

/**
 * Centralized configuration constants for Salad framework
 */
public final class SaladConfig {

    // Timeout configurations
    public static final int DEFAULT_TIMEOUT = 30;
    public static final int DURATION_TIMEOUT = 60;
    public static final int DEFAULT_POLLING_INTERVAL_MS = 200;

    // Swipe configurations
    public static final int MAX_SWIPE_COUNT = 15;
    public static final int DEFAULT_SWIPE_DELAY_MS = 500;

    // Screenshot configurations
    public static final String DEFAULT_SCREENSHOT_DIR = "screenshot";
    public static final String SCREENSHOT_EXTENSION = ".png";

    // Appium configurations
    public static final String APPIUM_LOG_FILE_PATH = System.getProperty("user.dir") + "/target/appium.log";
    public static final String APPIUM_HUB_PATH = "/wd/hub";

    // Element display thresholds
    public static final int POLLING_MULTIPLIER = 5;

    private SaladConfig() {
        throw new UnsupportedOperationException("Configuration class cannot be instantiated");
    }

    /**
     * Convert seconds to Duration
     */
    public static Duration toDuration(int seconds) {
        return Duration.ofSeconds(seconds);
    }

    /**
     * Convert milliseconds to Duration
     */
    public static Duration toMillisDuration(int milliseconds) {
        return Duration.ofMillis(milliseconds);
    }
}

