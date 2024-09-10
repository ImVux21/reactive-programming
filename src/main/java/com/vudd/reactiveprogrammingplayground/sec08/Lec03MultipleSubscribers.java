package com.vudd.reactiveprogrammingplayground.sec08;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribers {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

    public static void main(String[] args) {
        var producer = Flux
                .generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("Emitting: {}", state);
                            sink.next(state);
                            return state + 1;
                        }
                )
                .subscribeOn(Schedulers.parallel())
                .cast(Integer.class);

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec03MultipleSubscribers::timeConsumingOperation)
                .subscribe(Util.subscriber("slowSub"));

        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("fastSub"));

        Util.sleepSeconds(60);
    }

    private static int timeConsumingOperation(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
