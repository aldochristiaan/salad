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
     * Get all properties
     */
    public Properties getProperties() {
        return new Properties(properties);
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

