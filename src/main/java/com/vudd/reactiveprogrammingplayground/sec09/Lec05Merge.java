package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {
    private static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {

        demo3();

        Util.sleepSeconds(5);
    }

    private static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.mergeSequential(producer1(), producer2(), producer3())
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        producer1()
                .mergeWith(producer2())
                .mergeWith(producer3())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer3() {
        return Flux.range(20, 4)
                .transform(Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.range(10, 4)
                .transform(Util.fluxLogger("producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer1() {
        return Flux.range(1, 4)
                .transform(Util.fluxLogger("producer1"))
                .delayElements(Duration.ofMillis(10));
    }
}
