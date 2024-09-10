package com.vudd.reactiveprogrammingplayground.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Util {

    private static final Faker faker = Faker.instance();
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    // <T> at the front of Subscriber<T> is a generic type declaration. It tells the compiler that the type T is a generic type.
    // It denotes that Subscriber<T> is a generic type that can be parameterized with some type.
    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            log.error("Sleep interrupted", e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux
                .doOnSubscribe(s -> System.out.println(name + " subscribed"))
                .doOnNext(o -> System.out.println(name + " received: " + o))
                .doOnComplete(() -> System.out.println(name + " done"))
                .doOnCancel(() -> System.out.println(name + " cancelled"));
    }
}
