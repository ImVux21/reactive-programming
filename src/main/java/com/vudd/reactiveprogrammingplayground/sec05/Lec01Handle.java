package com.vudd.reactiveprogrammingplayground.sec05;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    public static void main(String[] args) {

//        Flux<Integer> range = Flux.range(1, 10);
//        Flux<Integer> handle = range.handle((integer, synchronousSink) -> {
//            if (integer == 7) {
//                synchronousSink.complete();
//            } else {
//                synchronousSink.next(integer);
//            }
//        });
//
//        range.subscribe(System.out::println);
//        System.out.println("************");
//        handle.subscribe(System.out::println);

        Flux.range(1, 10)
                .filter(i -> i != 7)
                .handle((integer, synchronousSink) -> {
                    switch (integer) {
                        case 1 -> synchronousSink.next(-2);
                        case 2 -> synchronousSink.next("do not send");
                        case 7 -> synchronousSink.error(new RuntimeException("something went wrong"));
                        default -> synchronousSink.next(integer);
                    }
                })
//                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }
}
