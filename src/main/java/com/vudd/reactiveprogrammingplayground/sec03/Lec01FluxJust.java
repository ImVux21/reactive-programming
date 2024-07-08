package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5, "sam", "jack")
                .subscribe(Util.subscriber());
    }
}
