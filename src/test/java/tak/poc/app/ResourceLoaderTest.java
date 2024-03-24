package tak.poc.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class ResourceLoaderTest {

    @Test
    void getPropertiesWithloadFromResource() throws IOException {
        String ResourceFile = "application.properties";

        ResourceLoader loader = new ResourceLoader();
        Properties properties = loader.loadFromResource(ResourceFile);
        Assertions.assertFalse(properties.toString().isEmpty());
    }

    @Test
    void getPropertiesWithloadFromPath() throws IOException {
        Path path = Paths.get("src/test/resources/application.properties");

        ResourceLoader loader = new ResourceLoader();
        Properties properties = loader.loadFromPath(path);
        Assertions.assertFalse(properties.toString().isEmpty());
    }


    @Test
    void getPropertiesShouldBeSame() throws IOException {
        Path path = Paths.get("src/test/resources/application.properties");
        String ResourceFile = "application.properties";

        ResourceLoader loader = new ResourceLoader();
        Properties propertiesFromPath = loader.loadFromPath(path);
        Properties propertiesFromResource = loader.loadFromResource(ResourceFile);

        Assertions.assertEquals(propertiesFromResource, propertiesFromPath);
    }
}