package com.vudd.reactiveprogrammingplayground.sec02;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec08MonoFromFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(getName())
                .subscribe(
                        Util.subscriber()
                );

        Util.sleepSeconds(2);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generating name...");
            return Util.faker().name().fullName();
        });
    }
}
