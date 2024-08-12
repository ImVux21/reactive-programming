package com.vudd.reactiveprogrammingplayground.sec05;

import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Lec10Transform {
    public static void main(String[] args) {
        getPerson()
                .transform(addDebugger())
                .subscribe(System.out::println);

        getOrders()
                .transform(addDebugger())
                .subscribe(System.out::println);
    }

    record Person(String name, int age) {
    }

    private static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person("Name" + i, i));
    }

    record Order(String item) {
    }

    private static Flux<Order> getOrders() {
        return Flux.range(1, 10)
                .map(i -> new Order("Item" + i));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnNext(e -> System.out.println("Element: " + e))
                .doOnComplete(() -> System.out.println("Completed"));
    }
}
