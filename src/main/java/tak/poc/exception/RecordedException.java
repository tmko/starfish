package tak.poc.exception;

import tak.poc.facet.PlaywrightFacet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RecordedException extends Exception{

    public RecordedException (PlaywrightFacet facet, String msg, Exception e) {
        super(msg, e);
        byte [] screen = facet.screenShot();
        save(screen);
    }

    public RecordedException (PlaywrightFacet facet, String msg) {
        super(msg);
        byte [] screen = facet.screenShot();
        save(screen);
    }

    private void save (byte [] content) {
        String filename = System.currentTimeMillis() + ".png";
        try {
            Files.write(Paths.get("/home/tak/src/starfish/target", filename), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
