package tak.poc.webpage;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;
import tak.poc.AppConf;
import tak.poc.Processor;
import tak.poc.util.Intro;
import tak.poc.util.UserAction;

@Slf4j
public class EasywebLaunch extends Processor.Step {
    private static final String TOOLTIP = "We took privacy seriously,</br>Please manually enter username and password,</br> than click here";
    private static final String LOGIN_BUTTON_SECLECTOR =
            "body > app-root > main > core-login-template > div > " +
            "section.otp-login > div > div.light-green.otp-box.otp-separate-right-nav > div > " +
            "div:nth-child(1) > div > core-login-form > form > div.td-row.order-5 > div";



    public EasywebLaunch() {
        super("EasywebLaunch", null);
    }

    protected void setup (Page page) {
        String url = "https://authentication.td.com/uap-ui/?consumer=easyweb&locale=en_CA#/uap/login";
        log.info("Page URL {}", url);
        page.navigate(url);
    }

    protected void beforeAction (Page page) {
        int textFound = page.getByText("EasyWeb Login").all().size();
        log.info("Is EasyWeb Login found? found={}", textFound);

        int passwordFound = page.getByText("Password").all().size();
        log.info("Is password field found? found={}", passwordFound);

        boolean loginButton = null != page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Login"));
        log.info("Is loginButton found? found={}", loginButton);

        boolean isExpected =  textFound > 0 && passwordFound > 0 && loginButton;

        if (!isExpected) {
            throw new IllegalStateException("Page is unexpected.");
        }

        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }


    protected void action (Page page) throws Exception {
        Intro.addLib(page);
        Intro.onboarding(page, new Intro (LOGIN_BUTTON_SECLECTOR, TOOLTIP));
        UserAction.scrollToTop(page);

        String idLabel = AppConf.LOGIN_USERNAME_LABEL;
        String pwLabel = AppConf.LOGIN_PASSWORD_LABEL;
        if( AppConf.get().getPassword() != null && AppConf.get().getUsername() != null ) {
            page.getByLabel(idLabel).fill(AppConf.get().getUsername());
            page.getByLabel(pwLabel).fill(AppConf.get().getPassword());

        }


        page.waitForClose(UserAction.DoNothingButHoldBrowserOpen);
    }

}
