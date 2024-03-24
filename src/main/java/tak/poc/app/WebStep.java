package tak.poc.app;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import tak.poc.exception.RecordedException;
import tak.poc.facet.PlaywrightFacet;

@AllArgsConstructor
@Slf4j
@ToString
public class WebStep {
    private String stepName = "StepNameNotSet";

    protected void setup(PlaywrightFacet facet) throws RecordedException {}

    protected void beforeAction(PlaywrightFacet facet) throws RecordedException {}

    protected void action(PlaywrightFacet facet) throws RecordedException {}

    protected void afterAction(PlaywrightFacet facet) throws RecordedException {}

    public PlaywrightFacet apply(PlaywrightFacet facet) throws RecordedException {
        final String stepHeader = "->".repeat(10) + "Starting" + "<-".repeat(10);

        log.atInfo().addKeyValue("step", stepName).addKeyValue("act", "setup").setMessage(stepHeader).log();
        setup(facet);
        log.atInfo().addKeyValue("step", stepName).addKeyValue("act", "before").setMessage("Preparing").log();
        beforeAction(facet);
        log.atInfo().addKeyValue("step", stepName).addKeyValue("act", "action").setMessage("acting").log();
        action(facet);
        log.atInfo().addKeyValue("step", stepName).addKeyValue("act", "after").setMessage("cleaning").log();
        afterAction(facet);
        log.atInfo().addKeyValue("step", stepName).addKeyValue("act", "done").setMessage("transitioning").log();

        return facet;
    }

}