package com.vudd.reactiveprogrammingplayground.common;

import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {

    // <T> at the front of Subscriber<T> is a generic type declaration. It tells the compiler that the type T is a generic type.
    // It denotes that Subscriber<T> is a generic type that can be parameterized with some type.
    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static void main(String[] args) {
        var mono = Mono.just("Vu").map(i -> i + i);
        mono.subscribe(
            subscriber()
        );
        mono.subscribe(
            subscriber("Vudd")
        );
    }
}
