package tak.poc.app;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;


@Slf4j
public class ResourceLoader {

    public String loadFromResourceAsString (String file) throws IOException {
        log.atInfo().addKeyValue("file", file).setMessage("loading from resource").log();

        String result = "";
        ClassLoader resourceLoader = ResourceLoader.class.getClassLoader();
        try ( InputStream inputStream = resourceLoader.getResourceAsStream(file)) {
            if (inputStream != null) {
                byte[] content = inputStream.readAllBytes();
                result = new String(content, StandardCharsets.UTF_8);
            }
        }
        return result;
    }


    public Properties loadFromResource (String file) throws IOException {
        log.atInfo().addKeyValue("file", file).setMessage("loading from resource").log();
        Properties properties = new Properties();
        ClassLoader resourceLoader = ResourceLoader.class.getClassLoader();
        try ( InputStream inputStream = resourceLoader.getResourceAsStream(file)) {
            if ( inputStream != null ) {
                properties.load(inputStream);
            }
        }

        log.atInfo().addKeyValue("file", file).log("loaded from resource {}", properties);
        return properties;
    }


    public Properties loadFromPath (Path file) throws IOException {
        log.atInfo().addKeyValue("file", file).setMessage("loading from resource").log();
        Properties properties = new Properties();
        try ( InputStream inputStream = new FileInputStream(file.toFile())) {
                properties.load(inputStream);
        }

        log.atInfo().addKeyValue("file", file).log("loaded from resource {}", properties);
        return properties;
    }

}