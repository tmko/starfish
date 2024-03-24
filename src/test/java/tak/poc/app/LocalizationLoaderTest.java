package tak.poc.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocalizationLoaderTest {

    @Test
    void getEnUS() {
        LocalizationLoader loader = new LocalizationLoader("en", "US");
        String message = loader.get("localizationFile");
        Assertions.assertEquals(message, "messages_en_US.properties");
    }

    @Test
    void getPropertiesFromOtherLocalization() {
        LocalizationLoader loader = new LocalizationLoader("fr", "CA");
        String message = loader.get("localizationFile");
        Assertions.assertEquals(message, "messages_fr_CA.properties");
    }
}