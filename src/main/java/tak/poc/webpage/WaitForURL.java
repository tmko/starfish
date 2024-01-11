package tak.poc.webpage;

import com.microsoft.playwright.Page;
import tak.poc.Processor;

import java.util.Objects;

public class WaitForURL extends Processor.Step {
    private String urlPattern = null;

    public WaitForURL(String urlPattern){
        super("WaitForURL", null);
        this.urlPattern = urlPattern;
    }

    @Override
    protected void action(Page page) {
        if (Objects.isNull(page)) {
            return;
        }
        page.waitForURL(urlPattern);
    }

}
