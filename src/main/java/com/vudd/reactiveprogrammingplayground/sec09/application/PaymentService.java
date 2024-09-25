package com.vudd.reactiveprogrammingplayground.sec09.application;

import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentService {
    public static final Map<Integer, Integer> userBalanceDB = Map.of(
            1, 1000,
            2, 2000,
            3, 3000
    );

    public static Mono<Integer> getUserBalance(int userId) {
        return Mono.justOrEmpty(userBalanceDB.get(userId));
    }
}
