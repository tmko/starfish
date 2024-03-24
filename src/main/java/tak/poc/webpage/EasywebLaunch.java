package tak.poc.webpage;

import lombok.extern.slf4j.Slf4j;
import tak.poc.app.WebStep;
import tak.poc.exception.RecordedException;
import tak.poc.facet.IntroJsFacet;
import tak.poc.facet.PlaywrightFacet;

import java.io.IOException;

@Slf4j
public class EasywebLaunch extends WebStep {
    private static final String TOOLTIP = "We took privacy seriously,</br>Please manually enter username and password,</br> than click here";
    private static final String LOGIN_BUTTON_SECLECTOR =
            "body > app-root > main > core-login-template > div > " +
            "section.otp-login > div > div.light-green.otp-box.otp-separate-right-nav > div > " +
            "div:nth-child(1) > div > core-login-form > form > div.td-row.order-5 > div";



    public EasywebLaunch() {
        super(EasywebLaunch.class.getCanonicalName());
    }

    protected void setup (PlaywrightFacet facet) {
        final String url = "https://authentication.td.com/uap-ui/?consumer=easyweb&locale=en_CA#/uap/login";
        facet.go(url);
    }

    protected void beforeAction (PlaywrightFacet facet) throws RecordedException {
        boolean userIdFound = facet.isTextInputFound("Username or Access Card");
        boolean passwordFound = facet.isTextInputFound("Password");
        boolean loginButtonFound = facet.isButtonFound("Login");

        boolean isExpected = userIdFound && passwordFound && loginButtonFound;

        if (!isExpected) {
            throw new RecordedException(facet, "Found unexpected page");
        }
    }


    protected void action (PlaywrightFacet facet) throws RecordedException {
        try {
            facet.insertContentToDom(IntroJsFacet.cssContent(), IntroJsFacet.jsContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        facet.addHtmlAttributeToButton("Password", "","");
        facet.eval(IntroJsFacet.triggerJSCall());


        /*
        IntroJsFacet.onboarding(page, new IntroJsFacet(LOGIN_BUTTON_SECLECTOR, TOOLTIP));
        UserAction.scrollToTop(page);

        String idLabel = AppConf.LOGIN_USERNAME_LABEL;
        String pwLabel = AppConf.LOGIN_PASSWORD_LABEL;
        if( AppConf.get().getPassword() != null && AppConf.get().getUsername() != null ) {
            page.getByLabel(idLabel).fill(AppConf.get().getUsername());
            page.getByLabel(pwLabel).fill(AppConf.get().getPassword());

        }

        page.waitForClose(UserAction.DoNothingButHoldBrowserOpen);
        */
    }

}
