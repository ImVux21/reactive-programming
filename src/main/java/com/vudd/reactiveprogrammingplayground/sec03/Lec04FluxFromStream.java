package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        var list = List.of("a", "b", "c");
        var stream = list.stream();

        Flux<String> fromStream = Flux.fromStream(stream);
        fromStream.subscribe(Util.subscriber("sub1"));
        fromStream.subscribe(Util.subscriber("sub2"));

    }
}
