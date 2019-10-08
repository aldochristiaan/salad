package android;

import id.aldochristiaan.salad.Salad;
import id.aldochristiaan.salad.util.Driver;
import id.aldochristiaan.salad.util.LogLevel;
import id.aldochristiaan.salad.util.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Properties;

public class AndroidFactory {

    private static Salad salad;
    private static AndroidDriver<AndroidElement> androidDriver;
    private static Properties capabilitiesProperties;
    protected static Android android;

    @BeforeAll
    public static void setUp() {
        String elementPropertiesDirectory = "src/test/resources/elements/";
        String capabilitiesFileName = "capabilities.properties";
        capabilitiesProperties = PropertiesLoader.loadCapabilities(capabilitiesFileName);
        salad = new Salad(
                capabilitiesProperties,
                elementPropertiesDirectory,
                Driver.ESPRESSO,
                LogLevel.DEBUG
        );
        salad.start();
        android = new Android(salad.getAndroidDriver());
    }

    @AfterAll
    public static void tearDown() {
        salad.stop(Driver.ESPRESSO);
    }

}
