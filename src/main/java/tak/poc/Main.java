package tak.poc;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@ToString
public final class Main {
    public static void main(final String[] args) {
        log.info(AppConf.get().toString());
        Processor processor = new Processor();
        processor.mainProcessingByStepAsDAG();


    }

}