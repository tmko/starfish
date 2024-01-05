package tak.poc.bankGeneral;

import com.microsoft.playwright.Page;
import tak.poc.Processor;

import java.util.Objects;

public class HoldBrowserOpen extends Processor.Step {
    public HoldBrowserOpen() {
        super("HoldBrowserOpen", null);
    }

    protected void action(Page page) {
        if (Objects.isNull(page)) {
            return;
        }

        Runnable DoNothingButHoldBrowserOpen = () -> {};
        page.waitForClose(DoNothingButHoldBrowserOpen);
    }

}
