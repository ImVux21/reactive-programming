package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    public static void main(String[] args) {

        /*
        Flux.generate():
        - SynchronousSink: is a callback that we can use to emit items
        - The callback is called for each downstream request
        - We can emit 0 or more items
        - We can complete the sequence
        - We can throw an exception
        - We can maintain a mutable state
        - We can use it to generate a sequence of items
        - It is not thread-safe
        - It is not backpressure-aware
        - It is not recommended for async operations
        - It is recommended for synchronous operations because it is not thread-safe and not backpressure-aware
        - limit the number of items emitted by using take()
        - like for loop but for reactive programming
         */
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("viet nam")) {
                synchronousSink.complete();
            }
//            synchronousSink.next(1);
//            synchronousSink.complete();
//            synchronousSink.error(new RuntimeException("oops"));
        })
//                .take(4)
                .subscribe(Util.subscriber());
    }
}
