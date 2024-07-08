package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        flux.subscribe(Util.subscriber("sub1"));
        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + "a")
                .subscribe(Util.subscriber("sub2"));
    }
}
