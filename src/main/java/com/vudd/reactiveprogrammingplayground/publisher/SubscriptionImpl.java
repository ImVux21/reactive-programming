package com.vudd.reactiveprogrammingplayground.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private boolean isCancelled = false;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private static final int MAX_ITEMS = 10;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = new Faker();
    }

    @Override
    public void request(long request) {
        if (isCancelled) {
            return;
        }
        log.info("Requesting {} items", request);

        for (int i = 0; i < request && count < MAX_ITEMS; i++) {
            subscriber.onNext(faker.internet().emailAddress());
            count++;
        }

        if (count >= MAX_ITEMS) {
            log.info("No more items to emit");
            subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("Subscription cancelled");
        isCancelled = true;
    }
}
