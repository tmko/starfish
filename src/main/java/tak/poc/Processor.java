package tak.poc;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tak.poc.bankGeneral.BlankBrowser;


@Slf4j
public class Processor {

    public Step buildProcssingStepDAG() {
        return new BlankBrowser();
    }

    public void mainProcessingByStepAsDAG () {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChromiumSandbox(true);

        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(options)) {
                try (BrowserContext context = browser.newContext()) {
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
