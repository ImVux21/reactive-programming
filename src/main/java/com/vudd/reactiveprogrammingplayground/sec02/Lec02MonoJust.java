package com.vudd.reactiveprogrammingplayground.sec02;

import com.vudd.reactiveprogrammingplayground.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("ball");
        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(5);
        subscriber.getSubscription().request(5);
        subscriber.getSubscription().cancel();
    }
}
