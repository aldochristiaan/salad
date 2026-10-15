package id.aldochristiaan.salad.util;

import id.aldochristiaan.salad.config.SaladConfig;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Centralized properties loader with singleton pattern
 */
public class PropertiesLoader {

    private static volatile PropertiesLoader instance;
    /** Element locators (and any key set via {@link #setProperty}). */
    private Properties properties;
    /** Appium session capabilities only — must never leak into element lookups or vice versa. */
    private Properties capabilityProperties;

    private PropertiesLoader() {
        this.properties = new Properties();
        this.capabilityProperties = new Properties();
    }

    /**
     * Load capabilities from a properties file in the working directory.
     * <p>
     * Kept for backward compatibility with existing setups and the example projects:
     * <pre>{@code
     * Properties caps = PropertiesLoader.loadCapabilities("capabilities.properties");
     * }</pre>
     * On failure an error is logged and an empty {@code Properties} is returned.
     *
     * @param fileName properties file name (resolved against {@code ./})
     * @return the loaded properties, or an empty instance when loading fails
     */
    public static Properties loadCapabilities(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("./" + fileName)) {
            props.load(fis);
        } catch (IOException e) {
            LogUtil.error("Failed to load properties file: " + fileName, e);
        }
        return props;
    }

    /**
     * Get singleton instance
     */
    public static PropertiesLoader getInstance() {
        if (instance == null) {
            synchronized (PropertiesLoader.class) {
                if (instance == null) {
                    instance = new PropertiesLoader();
                }
            }
        }
        return instance;
    }

    /**
     * Load properties from a directory (element locators).
     */
    public void loadFromDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + directory);
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".properties"));
        if (files == null || files.length == 0) {
            LogUtil.warn("No properties files found in: " + directory);
            return;
        }

        for (File file : files) {
            loadFromFile(file);
        }
    }

    /**
     * Load properties from a single file (element locators).
     */
    public void loadFromFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
            LogUtil.info("Loaded properties from: " + file.getName());
        } catch (IOException e) {
            LogUtil.error("Failed to load properties: " + file.getName(), e);
        }
    }

    /**
     * Load Appium session capabilities. Kept separate from element locators so they are
     * never forwarded to the driver as capabilities (or looked up as elements).
     */
    public void loadFromProperties(Properties props) {
        this.capabilityProperties.putAll(props);
    }

    /**
     * Get property value. Element locators take precedence; capability keys are a
     * fallback for backward compatibility with single-store setups.
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        return value != null ? value : capabilityProperties.getProperty(key);
    }

    /**
     * Get property with default value
     */
    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Set property (element store)
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * Remove property from both stores
     */
    public void removeProperty(String key) {
        properties.remove(key);
        capabilityProperties.remove(key);
    }

    /**
     * Check if property exists in either store
     */
    public boolean hasProperty(String key) {
        return properties.containsKey(key) || capabilityProperties.containsKey(key);
    }

    /**
     * Get all properties as an independent snapshot copy (both stores merged).
     * <p>
     * Note: this intentionally copies the entries into the returned instance instead of using
     * {@code new Properties(properties)}, which would store the loaded values in the
     * {@code defaults} slot. Values in the defaults slot are invisible to
     * {@link Properties#isEmpty()}, {@link Properties#forEach}, {@link Properties#entrySet()}
     * and {@link Properties#size()}, which previously caused loaded capabilities to be
     * silently ignored by callers.
     */
    public Properties getProperties() {
        Properties copy = new Properties();
        copy.putAll(capabilityProperties);
        copy.putAll(properties);
        return copy;
    }

    /**
     * Get only the Appium session capabilities. Element locators are excluded so they are
     * never sent to the driver as capabilities.
     */
    public Properties getCapabilityProperties() {
        Properties copy = new Properties();
        copy.putAll(capabilityProperties);
        return copy;
    }

    /**
     * Get only the element locator properties.
     */
    public Properties getElementProperties() {
        Properties copy = new Properties();
        copy.putAll(properties);
        return copy;
    }

    /**
     * Clear all properties (both stores)
     */
    public void clear() {
        properties.clear();
        capabilityProperties.clear();
    }

    /**
     * Reset instance (for testing)
     */
    public static void resetInstance() {
        instance = null;
    }
}

