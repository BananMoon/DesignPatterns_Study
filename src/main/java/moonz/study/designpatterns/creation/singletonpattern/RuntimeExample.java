package moonz.study.designpatterns.creation.singletonpattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RuntimeExample {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        log.info("max memory: {}", runtime.maxMemory());
        log.info("free memory: {}", runtime.freeMemory());
    }
}

