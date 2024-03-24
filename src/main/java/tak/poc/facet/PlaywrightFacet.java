package tak.poc.facet;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class PlaywrightFacet {
    private Page page;

    public void addHtmlAttributeToButton (String buttonLabel, String attrName, String attrValue) {
        page.getByLabel("Password").elementHandle().evaluateHandle(
                "node => node.setAttribute('data-intro', 'Hello work')");
    }

    public byte[] screenShot () {
        return page.screenshot();
    }

    public boolean isTextInputFound (String label) {
        return found(AriaRole.TEXTBOX, label).size() > 0;
    }

    public boolean isButtonFound (String label) {
        return found(AriaRole.BUTTON, label).size() > 0;
    }

    private List<Locator> found(AriaRole role, String label) {
        Page.GetByRoleOptions labelObj = new Page.GetByRoleOptions().setName(label);
        List<Locator> all = page.getByRole(role, labelObj).all();
        log.info("look for {} with name {}, found {}", role.name(), label, all.size());
        return all;
    }

    public void go(String url) {
        log.info("Page URL {}", url);
        page.navigate(url);

        if (url.indexOf("login") > 0) {
            page.waitForCondition( () ->
                    isTextInputFound("Username or Access Card") &&
                isTextInputFound("Password") &&
                isButtonFound("Login")
            );
        }
    }

    public void eval(String content) {
        page.evaluate(content);
    }

    public void holdOpen () {
        Runnable DoNothingButHoldBrowserOpen = () -> {};
        page.waitForClose(DoNothingButHoldBrowserOpen);
    }

    public void insertContentToDom (String cssContent, String jsContent) {
        if ( cssContent != null && !cssContent.isEmpty() ) {
            page.addStyleTag(new Page.AddStyleTagOptions().setContent(cssContent));
        }
        if ( jsContent != null && !jsContent.isEmpty() ) {
            page.addScriptTag(new Page.AddScriptTagOptions().setContent(jsContent));
        }
    }


    public void scrollToTop () {
        page.mouse().wheel(0, -100000000);
    }
}
