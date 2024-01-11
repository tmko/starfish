package tak.poc;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tak.poc.webpage.EasywebLaunch;
import tak.poc.webpage.HoldBrowserOpen;


@Slf4j
public class Processor {

    public Step buildProcssingStepDAG() {
        Step step1 = new EasywebLaunch();
        Step step3 = new HoldBrowserOpen();

        step1.next(step3);
        return step1;

    }

    public void mainProcessingByStepAsDAG () {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChromiumSandbox(true);


        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(options)) {
                try (BrowserContext context = browser.newContext()) {
                    context.setDefaultTimeout(60_000d);
                    context.setDefaultNavigationTimeout(50_000d);
                    try (Page newBlankPage = context.newPage()) {
                        try {
                            buildProcssingStepDAG().apply(newBlankPage);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            }
        }
    }


    @AllArgsConstructor
    public static class Step {
        private String stepName = "StepNameNotSet";
        private Step nextStep = null;

        // Helper method for fluent style programming
        public Step next (Step nextStep) {
            this.nextStep = nextStep;
            return this.nextStep;
        }

        protected void beforeSetup(Page page) { page.waitForLoadState(LoadState.DOMCONTENTLOADED); }
        protected void setup (Page page) { }

        protected void beforeAction(Page page) throws Exception { }
        protected void action(Page page) throws Exception { }
        protected void afterAction(Page page) throws Exception { }

        protected void apply (Page page)  throws Exception {
            log.info("{} Starting {} step {}", "->".repeat(10), stepName, "<-".repeat(10));
            beforeSetup(page);
            setup(page);

            log.info("Running {} step", stepName);
            beforeAction(page);
            action(page);
            afterAction(page);
            callNextStep(page);
        }

        private void callNextStep(Page page) throws Exception {
            if (nextStep == null) {
                log.info("End of all Steps, stopped at {}", stepName);
                return;
            }
            log.info("Step, {}, is done, calling next Step {}", stepName, nextStep.stepName);
            nextStep.apply(page);
        }
    }

}
