package tak.poc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tak.poc.app.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

class ConfigurationTest {

    @Test
    void updateTest() {
        Properties oldProp = new Properties();
        Properties newProp = new Properties();

        oldProp.put("version", "old");
        newProp.put("version", "new");

        Configuration conf = new Configuration().update(oldProp).update(newProp);

        Assertions.assertEquals(conf.getVersion(), newProp.get("version"));
    }

    @Test
    void configurationTest() throws IOException {
        ResourceLoader loader = new ResourceLoader();
        Properties newProperties = new Properties();
        newProperties.put("version", "5.0.0");

        Configuration conf = new Configuration()
                .update(loader.loadFromResource(Configuration.APPLICATION_SECRET))
                .update(loader.loadFromResource(Configuration.APPLICATION_CONFIGURATION))
                .update(newProperties);

        Assertions.assertEquals(conf.getVersion(), newProperties.get("version"));
        Assertions.assertFalse(conf.getUsername().isEmpty());
        Assertions.assertFalse(conf.getPassword().isEmpty());
    }
}