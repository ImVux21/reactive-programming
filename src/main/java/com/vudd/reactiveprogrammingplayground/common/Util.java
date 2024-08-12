package com.vudd.reactiveprogrammingplayground.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.time.Duration;

public class Util {

    private static final Faker faker = Faker.instance();

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
            e.printStackTrace();
        }
    }
}
