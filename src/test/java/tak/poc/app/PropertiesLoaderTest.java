package tak.poc.app;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PropertiesLoaderTest {

    @Test
    void getConfigurationWithoutOverride() {
        PropertiesLoader loader = new PropertiesLoader();
        Properties properties = loader.getConfiguration(null);
        assertEquals(properties.getProperty("version"), "1.0");
    }

    @Test
    void getConfigurationOverRidden() {
        Properties overRidden = new Properties();
        overRidden.setProperty("version", "3.0");

        PropertiesLoader loader = new PropertiesLoader();
        Properties properties = loader.getConfiguration(overRidden);
        assertEquals(properties.getProperty("version"), "3.0");
    }

    @Test
    void getContentTest() {
        String content = PropertiesLoader.getResource("frontend/intro.js");
        assertFalse(content.isEmpty());
    }
}