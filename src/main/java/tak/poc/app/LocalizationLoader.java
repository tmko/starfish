package tak.poc.app;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.ResourceBundle;


@Slf4j
public class LocalizationLoader {
    private static final String LOCALIZED_MESSAGE = "localization.messages";

    private ResourceBundle bundle = ResourceBundle.getBundle(LOCALIZED_MESSAGE, Locale.getDefault());

    private LocalizationLoader (String language, String country) {
        if ( language != null && country != null ) {
            bundle = ResourceBundle.getBundle(LOCALIZED_MESSAGE, Locale.of(language, country));
        }
    }

    public String get (String key) {
        return bundle.getString(key);
    }

}