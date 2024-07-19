package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {
    public static void main(String[] args) {
        // generate();
        generateWithState();
    }

    private static void generateWithState() {
        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    String country = Util.faker().country().name();
                    sink.next(country + "-" + counter);
                    if (counter >= 10 || country.equalsIgnoreCase("viet nam")) {
                        sink.complete();
                    }
                    return ++counter;
                }
        ).subscribe(Util.subscriber());
    }
}
