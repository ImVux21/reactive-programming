package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec04FluxRange {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(i -> Util.faker().name().firstName())
                .subscribe(
                        System.out::println,
                        System.err::println,
                        () -> System.out.println("Done"));
    }
}
