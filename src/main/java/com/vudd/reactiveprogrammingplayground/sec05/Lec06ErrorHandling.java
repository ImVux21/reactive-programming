package com.vudd.reactiveprogrammingplayground.sec05;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {
    public static void main(String[] args) {
        onErrorContinue();
    }

    private static void onErrorContinue() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorContinue((err, obj) -> {
                    System.out.println("Error: " + err);
                    System.out.println("Object: " + obj);
                })
                .subscribe(System.out::println);
    }

    private static void onErrorComplete() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorComplete()
                .subscribe(System.out::println);
    }

    private static void onErrorReturn() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorReturn(IllegalArgumentException.class, -3)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-1)
                .subscribe(System.out::println);
    }

    private static void onErrorResume() {
        Flux.range(1, 10)
                .map(i -> 10 / (5 - i))
                .onErrorResume(ArithmeticException.class, e -> fallback1())
                .onErrorResume(e -> fallback2())
                .onErrorReturn(-1)
                .subscribe(System.out::println);
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(1, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
