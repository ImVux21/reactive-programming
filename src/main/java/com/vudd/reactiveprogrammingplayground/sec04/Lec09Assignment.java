package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {
    public static void main(String[] args) {
        Path path = Path.of("src/main/resources/sec04/file.txt");
        FileReaderServiceImpl readerService = new FileReaderServiceImpl();
        readerService.read(path)
                .takeUntil(s -> s.equalsIgnoreCase("line17"))
                .subscribe(
                        Util.subscriber()
                );
    }
}
