package com.vudd.reactiveprogrammingplayground.sec02.assignment;

import org.slf4j.Logger;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path PATH = Path.of("src/main/resources/sec02/");

    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> {
            log.info("Reading file: {}", fileName);
            return Files.readString(PATH.resolve(fileName));
        });
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> {
            log.info("Writing file: {}", fileName);
            writeToFile(fileName, content);
        });
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> {
            log.info("Deleting file: {}", fileName);
            deleteFile(fileName);
        });
    }

    private void writeToFile(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
            log.info("File {} has been written", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
            log.info("File {} has been deleted", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
