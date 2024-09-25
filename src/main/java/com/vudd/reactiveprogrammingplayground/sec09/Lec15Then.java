package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {
    public static void main(String[] args) {
        List<String> records = List.of("a", "b", "c", "d", "e", "f");

        saveRecord(records)
                .then(sendNotification(records))
                // then() is used to chain a Mono<Void> after a Flux, it will wait for the Flux to complete before executing the Mono<Void>
                // then() and thenMany() are used to chain a Mono<Void> or a Flux<Void> after a Flux
                .subscribe();

        Util.sleepSeconds(5);
    }

    private static Flux<String> saveRecord(List<String> records) {
        return Flux.fromIterable(records)
                .doOnNext(r -> System.out.println("Saving " + r))
                .doOnComplete(() -> System.out.println("Saved all records"))
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> {
            System.out.println("Sending notification");
            System.out.println("Records: " + records);
        });
    }
}
