package id.aldochristiaan.salad.util;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class for common driver operations
 */
public final class DriverUtils {

    private DriverUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Get boolean attribute value from element
     */
    public static boolean getBooleanAttribute(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        return Boolean.parseBoolean(value);
    }

    /**
     * Get string attribute value from element
     */
    public static String getStringAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * Get screen dimensions
     */
    public static Dimension getScreenDimension(WebDriver driver) {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        return new Dimension(size.getWidth(), size.getHeight());
    }

    /**
     * Inner class to represent screen dimensions
     */
    public static class Dimension {
        private final int width;
        private final int height;

        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    /**
     * Safe delay with interrupt handling
     */
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtil.error("Thread was interrupted during delay", e);
        }
    }
}

