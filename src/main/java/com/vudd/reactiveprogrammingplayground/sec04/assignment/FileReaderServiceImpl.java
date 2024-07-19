package com.vudd.reactiveprogrammingplayground.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService {
    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFiles(path),
                this::readFile
        );
    }

    private BufferedReader openFiles(Path path) throws IOException {
        log.info("Start reading file: {}", path);

        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        try {
            String line = reader.readLine();
            log.info("Reading line: {}", line);
            if (line != null) {
                sink.next(line);
            } else {
                sink.complete();
                reader.close();
            }
        } catch (IOException e) {
            sink.error(e);
        }
        return reader;
    }
}
