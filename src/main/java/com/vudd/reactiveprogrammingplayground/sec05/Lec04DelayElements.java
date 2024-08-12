package com.vudd.reactiveprogrammingplayground.sec05;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec04DelayElements {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .delayElements(java.time.Duration.ofSeconds(1))
                .subscribe(System.out::println);

        Util.sleepSeconds(12);
    }
}
