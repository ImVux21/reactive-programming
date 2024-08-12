package com.vudd.reactiveprogrammingplayground.sec06;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Almost the same as publish().refCount(1). But:
    - Doesn't stop when subscriber cancel. So it will start producing data even for 0 subscriber once it started.
    - Make it real hot => publish().autoConnect(0)
* */
public class Lec03HotPublisherAutoConnect {
    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {
        var movieStream = movieStream().publish().autoConnect(0);
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
