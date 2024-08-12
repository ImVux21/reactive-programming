package com.vudd.reactiveprogrammingplayground.sec06;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class Lec05FluxCreateIssueFix {
    public static void main(String[] args) {
        var generator = new MyGenerator();
        var flux = Flux.create(generator).share();

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }

    static class MyGenerator implements Consumer<FluxSink<Integer>> {
        private static FluxSink<Integer> sink;

        @Override
        public void accept(FluxSink<Integer> fluxSink) {
            sink = fluxSink;
        }

        public void generate() {
            if (sink != null) {
                sink.next((int) (Math.random() * 100));
            }
        }
    }
}
