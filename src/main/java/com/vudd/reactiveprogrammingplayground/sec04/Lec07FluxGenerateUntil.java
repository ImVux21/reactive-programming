package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {
    private static void demo1() {
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("viet nam")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.<String>generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .takeUntil(s -> s.equalsIgnoreCase("viet nam"))
                .subscribe(Util.subscriber());
    }

    public static void main(String[] args) {
        demo2();
    }
}
