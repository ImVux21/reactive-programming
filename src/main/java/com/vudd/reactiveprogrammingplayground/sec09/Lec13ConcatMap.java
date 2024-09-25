package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec13ConcatMap {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .concatMap(i -> Flux.range(1, 5)
                        .map(j -> i + ":" + j))
                .subscribe(Util.subscriber());
    }
}
