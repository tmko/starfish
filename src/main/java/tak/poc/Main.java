package tak.poc;

import com.microsoft.playwright.*;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import tak.poc.app.WebStep;
import tak.poc.facet.PlaywrightFacet;
import tak.poc.webpage.BlankBrowser;
import tak.poc.webpage.EasywebLaunch;
import tak.poc.webpage.HoldBrowserOpen;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <img src="doc-files/image3-7.png" alt="Example of the application GUI"/><br>
 * sadfasdfas
 *
 *
 */
@Slf4j
@Getter
@ToString
public final class Main {
    public static Configuration CONF;



    public static void main(final String[] args) throws IOException {
        CONF = Configuration.init(System.getProperties());
        log.info(CONF.toString());
        new Main().mainProcessingByStepAsDAG();
    }

    public List<WebStep> pageNavigationSequence() {
        return Arrays.asList(
                new BlankBrowser(),
                new EasywebLaunch(),
                new HoldBrowserOpen()
        );
    }

    public void mainProcessingByStepAsDAG () {
        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(getBrowserOption())) {
                try (BrowserContext context = browser.newContext()) {
                    configureContext(context);
                    try (Page newBlankPage = context.newPage()) {
                        try {
                            PlaywrightFacet facet = new PlaywrightFacet (newBlankPage);
                            for (WebStep step : pageNavigationSequence()) {
                                facet = step.apply(facet);
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            }
        }
    }

    private BrowserType.LaunchOptions getBrowserOption () {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChromiumSandbox(true);
        return options;
    }

    private void configureContext ( BrowserContext context ) {
        context.setDefaultTimeout(60_000d);
        context.setDefaultNavigationTimeout(50_000d);
    }

}