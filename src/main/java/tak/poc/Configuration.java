package tak.poc;

import lombok.Data;
import tak.poc.app.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

@Data
public final class Configuration {
    public static final String APPLICATION_CONFIGURATION = "application.properties";
    public static final String APPLICATION_SECRET = "application.secret";

    private String version = "0.0.0";
    private String username = "";
    private String password = "";

    public Configuration update (Properties values) {
        if ( values == null ) {
            return this;
        }

        version = values.getProperty("version", version);
        username = values.getProperty("username", username);
        password = values.getProperty("password", password);

        return this;
    }

    public static Configuration init (Properties values) throws IOException {
        ResourceLoader loader = new ResourceLoader();
        return new Configuration()
                .update(loader.loadFromResource(APPLICATION_CONFIGURATION))
                .update(loader.loadFromResource(APPLICATION_SECRET))
                .update(values);
    }
}