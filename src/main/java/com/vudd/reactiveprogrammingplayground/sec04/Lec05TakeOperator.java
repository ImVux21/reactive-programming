package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    public static void main(String[] args) {
//        takeUtil();
//        takeLast();
        // take();
        takeWhile();
    } // take, takeUtil, takeLast, takeWhile

    private static void take() {
        Flux.range(1, 10)
                .log()
                .take(3)
                .log()
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("takeUtil") // stop when the condition is met
                .takeUntil(i -> i < 10) // take until the condition is true, 1 < 10 => true => complete whatever is left 2 < 10,...
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeLast() {
        Flux.range(1, 10)
                .log("takeLast")
                .takeLast(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("takeWhile") // stop when the condition is not met
                .takeWhile(i -> i < 5) // take while the condition is true, 1 < 5 => true => 2 < 5 => true => 3 < 5 => true => 4 < 5 => true => 5 < 5 => false => complete
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
