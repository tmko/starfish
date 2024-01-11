package tak.poc.bankGeneral;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import tak.poc.Processor;

@Slf4j
public class BlankBrowser extends Processor.Step {

    protected void setup (Page page) {
        String url = "https://authentication.td.com/uap-ui/?consumer=easyweb&locale=en_CA#/uap/login";
        log.info("Page URL {}", url);
        page.navigate(url);
    }

    public BlankBrowser() {
        super("BlankBrowser", null);
    }

    protected void processStep(Page page) {
        return;
    }

}
