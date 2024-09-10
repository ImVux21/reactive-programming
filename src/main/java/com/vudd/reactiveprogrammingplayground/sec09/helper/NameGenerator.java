package com.vudd.reactiveprogrammingplayground.sec09.helper;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    private final static Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private static final List<String> redisNames = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux
                .generate(sink -> {
                            log.info("generated name");
                            Util.sleepSeconds(1);
                            String name = getName();
                            redisNames.add(name);
                            sink.next(name);
                        }
                )
                .startWith(getFromRedis())
                .cast(String.class);
    }

    private Iterable<?> getFromRedis() {
        return redisNames;
    }

    private String getName() {
        Util.sleepSeconds(1);
        return Util.faker().name().fullName();
    }
}
