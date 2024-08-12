package com.vudd.reactiveprogrammingplayground.sec06;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04hotPublisherCache {
    private static final Logger log = LoggerFactory.getLogger(Lec04hotPublisherCache.class);

    // replay(1) - cache the last 1 item
    // replay() - cache all items
    // replay(2) - cache the last 2 items
    // autoConnect(0) - start producing data when there is 0 subscriber
    public static void main(String[] args) {
        var stockPrice = stockStream().replay(1).autoConnect(0);

        Util.sleepSeconds(4);

        log.info("sam is subscribing");
        stockPrice
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);

        log.info("mike is subscribing");
        stockPrice
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(i -> log.info("emitting price: {}", i))
                .cast(Integer.class);
    }
}
