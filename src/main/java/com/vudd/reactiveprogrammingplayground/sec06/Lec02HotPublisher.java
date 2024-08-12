package com.vudd.reactiveprogrammingplayground.sec06;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

// Hot -  1 data producer for all the subscribers
// Cold - new data producer for each subscriber
// share() - convert cold publisher to hot publisher = publish().refCount()
// Hot publisher is a publisher that is already producing data before the subscription
// it needs 1 subscriber to start producing data
// It stops producing data when all subscribers are unsubscribed
// Hot publisher is useful when you want to share the same data stream among multiple subscribers
// Re-subscription - It starts again where there is a new subscriber
// to have 2 subscribers, use publish().refCount(2)
public class Lec02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        var movieStream = movieStream().share();
//        var movieStream = movieStream().publish().refCount(2);
        Util.sleepSeconds(2);

        movieStream
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);

        movieStream
                .take(3)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                () -> {
                    log.info("received request for the next movie");
                    return 1;
                },
                (state, sink) -> {
                    sink.next("Playing Movie: " + state);
                    log.info("playing movie: {}", state);
                    return ++state;
                }
        )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
