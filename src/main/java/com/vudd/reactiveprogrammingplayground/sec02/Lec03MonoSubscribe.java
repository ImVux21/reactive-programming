package com.vudd.reactiveprogrammingplayground.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        var mono = Mono.just(1).map(i -> i + i);
        mono.subscribe(
            item -> log.info("onNext: {}", item),
            err -> log.error("onError: {}", err.getMessage()),
            () -> log.info("onComplete"),
            subscription -> subscription.request(5)
        );
    }
}
