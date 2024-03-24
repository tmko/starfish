package tak.poc.app;

import lombok.extern.slf4j.Slf4j;
import tak.poc.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;


@Slf4j
public class PropertiesLoader {
    public static final String APPLICATION_PROPERTIES = "application.properties";
    public static final String SECRET_PROPERTIES = "application.secret";

    public Properties getConfiguration (Properties properties) {
        Properties defaultProperties = getProperties(Main.class.getClassLoader());
        Properties appDirProperties = getProperties(Paths.get(".", APPLICATION_PROPERTIES));
        Properties secretProperties = getProperties(Paths.get(".", SECRET_PROPERTIES));

        return merge(properties, merge(appDirProperties, merge(defaultProperties, secretProperties)));
    }
    public static String getResource (String packageLocation) {
        final String src = "resources/" + packageLocation;

        String content = "";
        try ( InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(packageLocation)) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.atError().addKeyValue("src", src).log("problem reading content, due to {}", e);
        }
        return content;
    }

    private Properties merge (Properties newSubSetPlacement, Properties basedTotalSetValue) {
        newSubSetPlacement = Optional.ofNullable(newSubSetPlacement).orElseGet(Properties::new);
        basedTotalSetValue = Optional.ofNullable(basedTotalSetValue).orElseGet(Properties::new);
        basedTotalSetValue.putAll(newSubSetPlacement);
        return basedTotalSetValue;
    }

    private Properties getProperties (Path location) {
        File file = location.toFile();
        String src = file.getAbsolutePath();

        Properties properties = new Properties();
        try ( InputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
        } catch (Exception e) {
            log.atWarn().addKeyValue("src", src).log("problem reading resource file, due to {}", e);
        }
        return properties;
    }

    private Properties getProperties (ClassLoader resourceLoader) {
        final String src = "resources/" + APPLICATION_PROPERTIES;

        Properties properties = new Properties();
        try ( InputStream inputStream = resourceLoader.getResourceAsStream(APPLICATION_PROPERTIES)) {
            properties.load(inputStream);
        } catch (Exception e) {
            log.atError().addKeyValue("src", src).log("problem reading resource file, due to {}", e);
        }
        return properties;
    }


}