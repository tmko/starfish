package tak.poc;

import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@ToString
public class DryRunConfiguration {
    @Getter private Path dryRunDir = null;

    public DryRunConfiguration () {
        try {
            dryRunDir = Files.createTempDirectory("DryRun").normalize();
            try (Stream<Path> path = Files.walk(dryRunDir)) {
                path.filter(Files::isRegularFile).map(Path::toFile).forEach(File::delete);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recordStepResult (Page page, String screenShotFileName) {
        Path thePath = dryRunDir.resolve(screenShotFileName);
        page.screenshot(
                new Page.ScreenshotOptions().setPath(thePath).setFullPage(true)
        );
    }

}
