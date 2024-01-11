import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;


@Slf4j
@ToString(exclude = "password")
public class AppConf {
    public static final String DEFAULT_CONFIG = "application.properties";
    public static final String DEFAULT_SECRET = "application.secret";
    private static final AppConf SINGLETON = new AppConf();

    @Getter private String password;

    @Getter private String username;

    @Getter private String version;

    private AppConf() {
        Properties properties = new Properties();

        try (InputStream resourceAsStream = AppConf.class.getResourceAsStream(DEFAULT_CONFIG)) {
            properties.load(resourceAsStream);
        } catch (Exception e) {
            log.atError()
                    .addKeyValue("file", DEFAULT_CONFIG)
                    .addKeyValue("dir", new File("").getAbsolutePath())
                    .log("Cannot load configuration", e);
            throw new RuntimeException(e);
        }

        try (InputStream secretAsStream = AppConf.class.getResourceAsStream(DEFAULT_SECRET)) {
                properties.load(secretAsStream);

        } catch (Exception e) {
            log.atError()
                    .addKeyValue("file", DEFAULT_SECRET)
                    .addKeyValue("dir", new File("").getAbsolutePath())
                    .log("Cannot load configuration", e);
                throw new RuntimeException(e);
            }
        this.password = properties.getProperty("password");
        this.username = properties.getProperty("username");
        this.version = properties.getProperty("version");
    }

    public static AppConf get () {
        return SINGLETON;
    }
}