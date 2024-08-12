package com.vudd.reactiveprogrammingplayground.sec07;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PublishOn {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Lec05PublishOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 0; i < 3; i++) {
                        log.info("Emitting: {}", i);
                        sink.next(i);
                    }
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(v -> log.info("onNext: {}", v))
                .doFirst(() -> log.info("First - doFirst"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("Second - doFirst"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);

        Util.sleepSeconds(2);
    }
}
