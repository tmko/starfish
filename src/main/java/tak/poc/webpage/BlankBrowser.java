package tak.poc.webpage;

import lombok.extern.slf4j.Slf4j;
import tak.poc.app.WebStep;
import tak.poc.facet.PlaywrightFacet;

@Slf4j
public class BlankBrowser extends WebStep {

    public BlankBrowser() {
        super(BlankBrowser.class.getCanonicalName());
    }

    protected void setup (PlaywrightFacet facet) {
        facet.go("about:blank");
    }
}
