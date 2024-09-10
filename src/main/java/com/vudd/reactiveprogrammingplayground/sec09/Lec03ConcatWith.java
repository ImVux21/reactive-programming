package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ConcatWith {
    private static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        demo3();

        Util.sleepSeconds(5);
    }

    private static void demo1() {
        producer1()
                .concatWithValues(-1, 0)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .concatWith(producer2())
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        Flux
                .concat(producer1(), producer2())
                .startWith(producer2())
                .subscribe(Util.subscriber());
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
