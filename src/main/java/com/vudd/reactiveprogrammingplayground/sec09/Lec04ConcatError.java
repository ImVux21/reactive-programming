package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04ConcatError {
    private static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(5);
    }

    private static void demo1() {
        Flux.concat(producer1(), producer3(), producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux
                .concatDelayError(producer1(), producer3(), producer2())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer3() {
        return Flux.error(new IllegalArgumentException("oops"));
    }

    private static Flux<Integer> producer2() {
        return Flux.range(10, 4)
                .doOnSubscribe(s -> log.info("subscribed to producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer1() {
        return Flux.range(1, 4)
                .doOnSubscribe(s -> log.info("subscribed to producer1"))
                .delayElements(Duration.ofMillis(10));
    }
}
