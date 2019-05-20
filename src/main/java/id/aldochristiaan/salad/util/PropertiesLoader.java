package id.aldochristiaan.salad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadCapabilities(String propertiesFileName) {
        Properties capabilitiesProperties = new Properties();
        try {
            FileInputStream properties = new FileInputStream("./" + propertiesFileName);
            capabilitiesProperties.load(properties);
            properties.close();
        } catch (IOException e) {
            LogUtil.error("File not found : capabilities.properties");
            e.printStackTrace();
        }
        return capabilitiesProperties;
    }

}
