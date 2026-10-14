package id.aldochristiaan.salad.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link PropertiesLoader}.
 * <p>
 * Includes a regression test for the {@code getProperties()} defaults-chain bug where
 * loaded values were invisible to {@code isEmpty()}/{@code forEach()}.
 */
class PropertiesLoaderTest {

    private PropertiesLoader loader;

    @BeforeEach
    void setUp() {
        PropertiesLoader.resetInstance();
        loader = PropertiesLoader.getInstance();
    }

    @Test
    @DisplayName("getInstance returns the same singleton instance")
    void singletonReturnsSameInstance() {
        assertSame(loader, PropertiesLoader.getInstance());
    }

    @Test
    @DisplayName("loadFromProperties merges properties and getProperty retrieves them")
    void loadFromPropertiesAndGet() {
        Properties props = new Properties();
        props.setProperty("app", "/tmp/app.apk");
        props.setProperty("deviceName", "emulator-5554");

        loader.loadFromProperties(props);

        assertEquals("/tmp/app.apk", loader.getProperty("app"));
        assertEquals("emulator-5554", loader.getProperty("deviceName"));
        assertTrue(loader.hasProperty("app"));
    }

    @Test
    @DisplayName("getProperties returns a fully populated copy (regression: defaults-chain bug)")
    void getPropertiesReturnsFlatCopy() {
        Properties props = new Properties();
        props.setProperty("app", "/tmp/app.apk");
        props.setProperty("platformName", "Android");
        loader.loadFromProperties(props);

        Properties copy = loader.getProperties();

        // These all failed before the fix because entries were stored in the defaults slot.
        assertFalse(copy.isEmpty(), "getProperties() must not return an empty-looking Properties");
        assertEquals(2, copy.size());
        assertEquals(2, copy.entrySet().size());

        final int[] forEachCount = {0};
        copy.forEach((k, v) -> forEachCount[0]++);
        assertEquals(2, forEachCount[0], "forEach must see the loaded entries");

        assertEquals("/tmp/app.apk", copy.getProperty("app"));
        assertTrue(copy.stringPropertyNames().contains("platformName"));
    }

    @Test
    @DisplayName("getProperties returns an independent snapshot")
    void getPropertiesIsDefensiveCopy() {
        loader.setProperty("key", "value");
        Properties copy = loader.getProperties();

        copy.setProperty("key", "mutated");
        copy.setProperty("extra", "1");

        assertEquals("value", loader.getProperty("key"), "Mutating the copy must not affect the loader");
        assertFalse(loader.hasProperty("extra"));
    }

    @Test
    @DisplayName("setProperty, hasProperty and removeProperty work as expected")
    void setHasRemove() {
        loader.setProperty("a", "1");
        assertTrue(loader.hasProperty("a"));

        loader.removeProperty("a");
        assertFalse(loader.hasProperty("a"));
        assertNull(loader.getProperty("a"));
    }

    @Test
    @DisplayName("getProperty returns default when key is missing")
    void getPropertyWithDefault() {
        assertEquals("fallback", loader.getProperty("missing", "fallback"));
        assertNull(loader.getProperty("missing"));
    }

    @Test
    @DisplayName("clear removes every property")
    void clearRemovesEverything() {
        loader.setProperty("a", "1");
        loader.setProperty("b", "2");

        loader.clear();

        assertTrue(loader.getProperties().isEmpty());
    }

    @Test
    @DisplayName("loadFromFile loads a real properties file")
    void loadFromFile() throws IOException {
        File file = File.createTempFile("caps", ".properties");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("udid=emulator-5554\nfullReset=false\n".getBytes());
        }

        loader.loadFromFile(file);

        assertEquals("emulator-5554", loader.getProperty("udid"));
        assertEquals("false", loader.getProperty("fullReset"));
    }

    @Test
    @DisplayName("loadFromDirectory loads every .properties file in the directory")
    void loadFromDirectory() throws IOException {
        File dir = java.nio.file.Files.createTempDirectory("elements").toFile();
        dir.deleteOnExit();

        writeProps(new File(dir, "General.properties"), "SCREEN=id_screen");
        writeProps(new File(dir, "Main.properties"), "TITLE=xpath_//android.widget.TextView");

        loader.loadFromDirectory(dir.getAbsolutePath());

        assertEquals("id_screen", loader.getProperty("SCREEN"));
        assertEquals("xpath_//android.widget.TextView", loader.getProperty("TITLE"));
    }

    @Test
    @DisplayName("loadFromDirectory rejects an invalid directory")
    void loadFromDirectoryRejectsInvalidPath() {
        assertThrows(IllegalArgumentException.class,
                () -> loader.loadFromDirectory("/definitely/not/a/directory"));
    }

    @Test
    @DisplayName("loadCapabilities loads a file from the working directory (backward compat)")
    void loadCapabilities() throws IOException {
        File file = new File("./loadcaps-test.properties");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("platformName=Android\n".getBytes());
        }

        Properties caps = PropertiesLoader.loadCapabilities("loadcaps-test.properties");

        assertEquals("Android", caps.getProperty("platformName"));
    }

    @Test
    @DisplayName("loadCapabilities returns empty Properties when the file is missing")
    void loadCapabilitiesMissingFile() {
        Properties caps = PropertiesLoader.loadCapabilities("does-not-exist.properties");

        assertNotNull(caps);
        assertTrue(caps.isEmpty());
    }

    private static void writeProps(File file, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes());
        }
    }
}
