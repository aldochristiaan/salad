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
    private Properties properties;

    private PropertiesLoader() {
        this.properties = new Properties();
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
     * Load properties from a directory
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
     * Load properties from a single file
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
     * Load from Properties object
     */
    public void loadFromProperties(Properties props) {
        this.properties.putAll(props);
    }

    /**
     * Get property value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property with default value
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Set property
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * Remove property
     */
    public void removeProperty(String key) {
        properties.remove(key);
    }

    /**
     * Check if property exists
     */
    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    /**
     * Get all properties as an independent snapshot copy.
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
        copy.putAll(properties);
        return copy;
    }

    /**
     * Clear all properties
     */
    public void clear() {
        properties.clear();
    }

    /**
     * Reset instance (for testing)
     */
    public static void resetInstance() {
        instance = null;
    }
}

