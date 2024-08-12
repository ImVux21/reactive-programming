package com.vudd.reactiveprogrammingplayground.sec07;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnExamples {
    //    public static void main(String[] args) throws InterruptedException {
//        Flux<Integer> flux = Flux.range(1, 3)
//                .map(i -> {
//                    System.out.println("First map: " + Thread.currentThread().getName());
//                    return i;
//                })
//                .subscribeOn(Schedulers.boundedElastic())
//                .map(i -> {
//                    System.out.println("Second map: " + Thread.currentThread().getName());
//                    return i;
//                });
//
//        flux.subscribe(i -> System.out.println("Received: " + i));
//
//        // Wait for async operations to complete
//        Thread.sleep(1000);
//    }
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> flux = Flux.defer(() -> {
                    System.out.println("Source - Thread: " + Thread.currentThread().getName());
                    return Flux.range(1, 3);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(i -> {
                    System.out.println("Map 1 - Thread: " + Thread.currentThread().getName());
                    return i * 2;
                })
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    System.out.println("Map 2 - Thread: " + Thread.currentThread().getName());
                    return i + 1;
                });

        System.out.println("Before subscribe - Thread: " + Thread.currentThread().getName());

        flux.subscribe(i -> System.out.println("Received " + i + " - Thread: " + Thread.currentThread().getName()));

        Thread.sleep(100); // Wait for async operations
    }
}


