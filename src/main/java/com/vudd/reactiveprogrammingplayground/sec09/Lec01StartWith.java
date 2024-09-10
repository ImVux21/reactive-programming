package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Lec01StartWith {
    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {
        demo4();

        Util.sleepSeconds(5);
    }

    private static void demo1() {
        producer1()
                .startWith(-1, 0)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .startWith(List.of(-2, -1, 0))
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        producer1()
                .startWith(producer2())
                .startWith(1000)
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
