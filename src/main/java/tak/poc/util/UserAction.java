package tak.poc.util;

import com.microsoft.playwright.Page;


public class UserAction {
    public static final Runnable DoNothingButHoldBrowserOpen = () -> {};

    public static void scrollToTop(Page page) {
        page.mouse().wheel(0, -100000000);
    }

}
