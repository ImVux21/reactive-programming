package com.vudd.reactiveprogrammingplayground.sec02;

import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {
    public static void main(String[] args) {
//        Supplier<String> stringSupplier = () -> {
//            System.out.println("Generating name...");
//            return "Vudd";
//        };
//
//        Mono<String> mono = Mono.fromSupplier(stringSupplier);
//
//        mono.subscribe(
//                System.out::println
//        );

        // Mono.fromSupplier() is lazy, it will not execute the heavy operation until it is subscribed
        // Mono.just() is eager, it will execute the heavy operation immediately
        System.out.println("Starting");
        Mono<String> justMono = Mono.just(heavyOperation());
        System.out.println("just() done");
        Mono<String> supplierMono = Mono.fromSupplier(() -> heavyOperation());
        System.out.println("fromSupplier() done");
        System.out.println("No subscription yet");

        // Output:
        // Starting
        // Performing heavy operation
        // just() done
        // fromSupplier() done
        // No subscription yet
    }

    // Helper method
    private static String heavyOperation() {
        System.out.println("Performing heavy operation");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Result";
    }
}
