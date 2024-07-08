package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {
    public static void main(String[] args) {
        var list = List.of("a", "b", "c");
        Flux.fromIterable(list).subscribe(Util.subscriber());

        var array = new Integer[] {1, 2, 3, 4, 5};
        Flux.fromArray(array).subscribe(Util.subscriber());
    }
}
