package tak.poc.util;

import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


public class UserAction {

    public static void scrollToTop(Page page) {
        page.mouse().wheel(0, -100000000);
    }

}
