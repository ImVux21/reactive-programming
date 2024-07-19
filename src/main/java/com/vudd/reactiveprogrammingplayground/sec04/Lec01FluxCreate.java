package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
    public static void main(String[] args) {
//        Flux.create(fluxSink -> {
//            fluxSink.next(1);
//            fluxSink.next(2);
//            fluxSink.next(3);
//            fluxSink.complete();
//        })
//                .subscribe(Util.subscriber());

        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("viet nam"));
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
