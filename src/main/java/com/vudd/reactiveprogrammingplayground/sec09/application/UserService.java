package com.vudd.reactiveprogrammingplayground.sec09.application;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {
    public static final Map<String, Integer> userDB = Map.of(
            "sam", 1,
            "peter", 2,
            "lucy", 3
    );

    public static Flux<User> getUsers() {
        return Flux.fromIterable(userDB.entrySet())
                .map(e -> new User(e.getValue(), e.getKey()));
    }

    public static Mono<Integer> getUserId(String name) {
        return Mono.justOrEmpty(userDB.get(name));
    }
}
