package tak.poc.util;

import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Intro {
    private String selector;
    private String tooltip;

    public static void addLib(Page page) {
        final String jss = "https://unpkg.com/intro.js/intro.js";
        final String css = "https://unpkg.com/intro.js/introjs.css";
        page.addScriptTag(new Page.AddScriptTagOptions().setUrl(jss));
        page.addStyleTag (new Page.AddStyleTagOptions().setUrl(css));
    }

    public static String onboarding (Page page, Intro ... elements) {
        String template = onboarding (elements);
        page.evaluate(template);
        return template;
    }

    public static String onboarding (Intro ... elements) {
        String COMMA = ",";
        String items = Arrays.stream(elements).map(Intro::itemTemplate).collect(Collectors.joining(COMMA));
        return overallTemplate(items);
    }

    private static String overallTemplate (String items) {
        return """
                introJs().setOptions({\s
                showBullets: false,\s
                    doneLabel: 'OK',\s
                    skipLabel: 'x',\s
                showButtons: false,\s
                 steps: [%s]\s
                }).start();
                """.formatted(items);
    }

    private static String itemTemplate (Intro x) {
        return """
               {\s
                element: document.querySelector('%s'),\s
                intro: '%s',\s
                position: 'right'\s
               }
               """.formatted(x.selector, x.tooltip);
    }

}
