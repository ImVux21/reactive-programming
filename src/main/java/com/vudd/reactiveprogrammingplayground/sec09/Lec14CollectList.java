package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec14CollectList {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());
    }
}
