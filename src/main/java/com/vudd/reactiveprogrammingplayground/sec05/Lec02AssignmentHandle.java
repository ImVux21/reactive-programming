package com.vudd.reactiveprogrammingplayground.sec05;

import reactor.core.publisher.Flux;

public class Lec02AssignmentHandle {
    public static void main(String[] args) {
//        Flux
//                .generate(synchronousSink -> {
//                    String country = com.vudd.reactiveprogrammingplayground.common.Util.faker().country().name();
//                    synchronousSink.next(country);
//                })
//                .cast(String.class)
//                .handle((country, synchronousSink) -> {
//                    synchronousSink.next(country);
//                    if (country.equalsIgnoreCase("viet nam")) {
//                        synchronousSink.complete();
//                    }
//                })
//                .subscribe(com.vudd.reactiveprogrammingplayground.common.Util.subscriber());

        Flux
                .generate(
                        () -> 1,
                        (counter, synchronousSink) -> {
                            String country = com.vudd.reactiveprogrammingplayground.common.Util.faker().country().name();
                            synchronousSink.next(country);
                            if (counter >= 10) synchronousSink.complete();
                            return counter + 1;
                        }
                )
                .cast(String.class)
                .handle((country, synchronousSink) -> {
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("viet nam")) {
                        synchronousSink.complete();
                    }
                })
                .subscribe(com.vudd.reactiveprogrammingplayground.common.Util.subscriber());
    }
}
