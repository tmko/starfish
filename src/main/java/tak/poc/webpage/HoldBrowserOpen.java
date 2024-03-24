package tak.poc.webpage;

import tak.poc.app.WebStep;
import tak.poc.exception.RecordedException;
import tak.poc.facet.PlaywrightFacet;

public class HoldBrowserOpen extends WebStep {
    public HoldBrowserOpen() {
        super(HoldBrowserOpen.class.getCanonicalName());
    }

    protected void action(PlaywrightFacet facet) throws RecordedException {
        facet.holdOpen();
    }

}
