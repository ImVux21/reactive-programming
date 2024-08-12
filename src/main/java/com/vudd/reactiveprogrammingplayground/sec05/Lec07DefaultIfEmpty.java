package com.vudd.reactiveprogrammingplayground.sec05;

import reactor.core.publisher.Flux;

public class Lec07DefaultIfEmpty {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i > 10)
                .defaultIfEmpty(-2)
//                .filter(i -> i > 9)
//                .defaultIfEmpty(-2)
                .subscribe(System.out::println);
    }
}
