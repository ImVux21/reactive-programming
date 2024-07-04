package com.vudd.reactiveprogrammingplayground.sec02;

import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUserById(3)
                .subscribe(
                        System.out::println,
                        err -> System.err.println(err.getMessage()),
                        () -> System.out.println("Completed")
                );
    }

    private static Mono<String> getUserById(int id) {
        return switch (id) {
            case 1 -> Mono.just("vudd");
            case 2 -> Mono.empty(); // equivalent to null, if emnpty, then onComplete will be called
            default ->
                    Mono.error(new RuntimeException("Not found")); // equivalent to throw new RuntimeException("Not found")
        };
    }
}
