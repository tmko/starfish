package tak.poc.facet;

import tak.poc.app.PropertiesLoader;

public class IntroJsFacet {

    public static String jsContent() {
        return PropertiesLoader.getResource("frontend/intro.js");
    }

    public static String cssContent() {
        return PropertiesLoader.getResource("frontend/introjs.css");
    }


    public static String triggerJSCall () {
        String optionJsObj =  "{ showBullets: false, doneLabel: 'OK', skipLabel: 'x', showButtons: false }";
        String introJs = "introJs().setOptions(%s).start();";
        return String.format(introJs, optionJsObj);
    }


    /*
    //page.evaluate( String.format(introJs, optionJsObj) );

    public void addLib() {
        final String jss = "https://cdn.jsdelivr.net/npm/intro.js@7.2.0/intro.min.js";
        final String css = "https://cdn.jsdelivr.net/npm/intro.js@7.2.0/minified/introjs.min.css";
        page.addScriptTag(new Page.AddScriptTagOptions().setUrl(jss));
        page.addStyleTag (new Page.AddStyleTagOptions().setUrl(css));
    }

    public static String onboarding (Page page, IntroJsFacet... elements) {
        String template = onboarding (elements);
        page.evaluate(template);
        return template;
    }

    public static String onboarding (IntroJsFacet... elements) {
        String COMMA = ",";
        String items = Arrays.stream(elements).map(IntroJsFacet::itemTemplate).collect(Collectors.joining(COMMA));
        return overallTemplate(items);
    }

    private static String overallTemplate (String items) {
        String s = """
                introJs().setOptions({\s
                showBullets: false,\s
                    doneLabel: 'OK',\s
                    skipLabel: 'x',\s
                showButtons: false,\s
                 steps: [%s]\s
                }).start();
                """;
        String result =  String.format(s,items);
        return result;
    }

    private static String itemTemplate (IntroJsFacet x) {
        String s = """
               {\s
                element: document.querySelector('%s'),\s
                intro: '%s',\s
                position: 'right'\s
               }
               """;
        return String.format(x.selector, x.tooltip);
    }*/

}
