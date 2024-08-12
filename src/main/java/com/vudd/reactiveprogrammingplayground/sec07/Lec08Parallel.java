package com.vudd.reactiveprogrammingplayground.sec07;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

public class Lec08Parallel {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .parallel()
                .runOn(reactor.core.scheduler.Schedulers.parallel())
                .map(i -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " - " + i);
                    Util.sleepSeconds(1);
                    return i;
                })
                .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
