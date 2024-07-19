package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {
    public static void main(String[] args) {
        produceOnDemand();

    }

    private static void produceOnDemand() {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for (int i = 0; i < request; i++) {
                    String country = Util.faker().country().name();
                    System.out.println("Emitting: " + country);
                    fluxSink.next(country);
                }
            });
        })
                .subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
