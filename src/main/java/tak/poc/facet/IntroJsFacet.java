package tak.poc.facet;

import tak.poc.app.ResourceLoader;

import java.io.IOException;

public class IntroJsFacet {
    public static final String INTRO_JS = "frontend/intro.js";
    public static final String INTRO_CSS = "frontend/introjs.css";

    public static String jsContent() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        return resourceLoader.loadFromResourceAsString(INTRO_JS);
    }

    public static String cssContent() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        return resourceLoader.loadFromResourceAsString(INTRO_CSS);
    }


    public static String triggerJSCall () {
        String optionJsObj =  "{ showBullets: false, doneLabel: 'OK', skipLabel: 'x', showButtons: false }";
        String introJs = "introJs().setOptions(%s).start();";
        return String.format(introJs, optionJsObj);
    }

}
