package com.vudd.reactiveprogrammingplayground.sec01;

import com.vudd.reactiveprogrammingplayground.sec01.publisher.PublisherImpl;
import com.vudd.reactiveprogrammingplayground.sec01.subscriber.SubscriberImpl;

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
