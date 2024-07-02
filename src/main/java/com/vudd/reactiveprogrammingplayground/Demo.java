package com.vudd.reactiveprogrammingplayground;

import com.vudd.reactiveprogrammingplayground.publisher.PublisherImpl;
import com.vudd.reactiveprogrammingplayground.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo();
    }

    private static void demo() throws InterruptedException {
         PublisherImpl publisher = new PublisherImpl();
         SubscriberImpl subscriber = new SubscriberImpl();
         publisher.subscribe(subscriber);

         subscriber.getSubscription().request(3);
         Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}
