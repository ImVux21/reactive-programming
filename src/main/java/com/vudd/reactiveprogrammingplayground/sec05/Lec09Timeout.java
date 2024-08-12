package com.vudd.reactiveprogrammingplayground.sec05;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;


// timeout operator is used to throw an exception if the source does not emit any item within the specified time

public class Lec09Timeout {
    public static void main(String[] args) {
//        Flux.range(1, 10)
//                .delayElements(java.time.Duration.ofSeconds(1))
//                .timeout(java.time.Duration.ofSeconds(2), Flux.just(-1))
//                .subscribe(System.out::println);

        // multiple timeout operators can be used in a single pipeline
        var mono = Flux.range(1, 10)
                .delayElements(java.time.Duration.ofSeconds(1))
                .timeout(java.time.Duration.ofSeconds(1), Flux.just(-1));
        mono
                .timeout(java.time.Duration.ofMillis(200), Flux.just(-2))
                .subscribe(System.out::println);

        Util.sleepSeconds(5);


    }
}
