package id.aldochristiaan.salad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadCapabilities(String propertiesFileName) {
        Properties capabilitiesProperties = new Properties();
        try (FileInputStream input = new FileInputStream("./" + propertiesFileName)) {
            capabilitiesProperties.load(input);
        } catch (IOException e) {
            LogUtil.error("Failed to load properties file: " + propertiesFileName, e);
        }
        return capabilitiesProperties;
    }
}
