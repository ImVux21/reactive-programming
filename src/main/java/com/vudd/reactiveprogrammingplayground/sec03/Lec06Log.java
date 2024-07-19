package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .log("Range")
                .map(i -> i * 10)
                .log("Mapped")
                .subscribe(
                        Util.subscriber());
    }
}
