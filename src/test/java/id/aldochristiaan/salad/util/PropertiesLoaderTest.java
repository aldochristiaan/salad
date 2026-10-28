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

    @Test
    @DisplayName("loadCapabilities keeps colon-containing keys like appium:udid intact")
    void loadCapabilitiesPreservesColonKeys() throws IOException {
        File file = new File("./loadcaps-colon-test.properties");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(("platformName=iOS\n"
                    + "appium:automationName=XCUITest\n"
                    + "appium:deviceName=iPhone 15\n"
                    + "appium:noReset=true\n"
                    + "appium:wdaLaunchTimeout=300000\n"
                    + "appium:udid=ABC-123\n").getBytes());
        }

        Properties caps = PropertiesLoader.loadCapabilities("loadcaps-colon-test.properties");

        // Regression: java.util.Properties treats ':' as a key/value separator, so every
        // "appium:*" line collapsed into a single "appium" key (last line wins) and the
        // real capabilities never reached the Appium driver.
        assertEquals(6, caps.size(), "Every appium: line must survive as its own key");
        assertEquals("iOS", caps.getProperty("platformName"));
        assertEquals("XCUITest", caps.getProperty("appium:automationName"));
        assertEquals("iPhone 15", caps.getProperty("appium:deviceName"));
        assertEquals("true", caps.getProperty("appium:noReset"));
        assertEquals("300000", caps.getProperty("appium:wdaLaunchTimeout"));
        assertEquals("ABC-123", caps.getProperty("appium:udid"));
        assertNull(caps.getProperty("appium"), "No collapsed 'appium' key may remain");
    }

    @Test
    @DisplayName("loadCapabilities skips comments/blank lines and keeps '=' inside values")
    void loadCapabilitiesHandlesCommentsAndEqualsInValues() throws IOException {
        File file = new File("./loadcaps-comment-test.properties");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(("# comment\n"
                    + "\n"
                    + "! another comment\n"
                    + "platformName=Android\n"
                    + "app=src/test/resources/showcase-android-debug.apk\n").getBytes());
        }

        Properties caps = PropertiesLoader.loadCapabilities("loadcaps-comment-test.properties");

        assertEquals(2, caps.size());
        assertEquals("Android", caps.getProperty("platformName"));
        assertEquals("src/test/resources/showcase-android-debug.apk", caps.getProperty("app"));
    }

    @Test
    @DisplayName("loadCapabilities still supports 'key: value' lines without '='")
    void loadCapabilitiesSupportsLegacyColonSeparator() throws IOException {
        File file = new File("./loadcaps-legacy-test.properties");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("deviceName: Android Emulator\n".getBytes());
        }

        Properties caps = PropertiesLoader.loadCapabilities("loadcaps-legacy-test.properties");

        assertEquals("Android Emulator", caps.getProperty("deviceName"));
    }

    @Test
    @DisplayName("loadCapabilities unescapes '\\:' so legacy escaped files keep working")
    void loadCapabilitiesUnescapesLegacyEscapedKeys() throws IOException {
        File file = new File("./loadcaps-escaped-test.properties");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Written before the colon-safe parser: ':' had to be escaped to survive
            // Properties.load. It must still resolve to the real key.
            fos.write("appium\\:udid=emulator-5554\n".getBytes());
        }

        Properties caps = PropertiesLoader.loadCapabilities("loadcaps-escaped-test.properties");

        assertEquals("emulator-5554", caps.getProperty("appium:udid"));
        assertNull(caps.getProperty("appium\\:udid"), "The raw escaped key must not survive");
    }

    private static void writeProps(File file, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes());
        }
    }
}
