package com.vudd.reactiveprogrammingplayground.sec08;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02lLimitRate {
    private static final Logger log = LoggerFactory.getLogger(Lec02lLimitRate.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");

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
                .map(Lec02lLimitRate::timeConsumingOperation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsumingOperation(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
