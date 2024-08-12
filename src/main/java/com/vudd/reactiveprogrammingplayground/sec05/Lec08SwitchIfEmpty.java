package com.vudd.reactiveprogrammingplayground.sec05;

import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i > 10)
                .switchIfEmpty(Flux.range(100, 5))
                .subscribe(System.out::println);
    }
}
