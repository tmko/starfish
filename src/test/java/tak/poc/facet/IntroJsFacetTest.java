package tak.poc.facet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IntroJsFacetTest {

    @Test
    void jsContent() throws IOException {
        Assertions.assertFalse(IntroJsFacet.jsContent().isEmpty());

    }

    @Test
    void cssContent() throws IOException {
        Assertions.assertFalse(IntroJsFacet.cssContent().isEmpty());
    }

    @Test
    void triggerJSCall() {
        Assertions.assertTrue(IntroJsFacet.triggerJSCall().toLowerCase().contains("start"));
    }
}