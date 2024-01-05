import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void AppConfTest() {
        assertEquals("1.0", AppConf.get().getVersion());
        assertEquals("fakeId", AppConf.get().getUsername());
        assertFalse(AppConf.get().toString().contains("password"));
    }
}