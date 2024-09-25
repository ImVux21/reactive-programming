package com.vudd.reactiveprogrammingplayground.sec09.application;

import com.vudd.reactiveprogrammingplayground.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {
    public static final Map<Integer, List<Order>> orderDB = Map.of(
            1, List.of(
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(100, 1000)),
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(100, 1000)),
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(100, 1000))),
            2, List.of(
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(100, 1000)),
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(100, 1000))),
            3, List.of(
                    new Order(3, Util.faker().commerce().productName(), Util.faker().random().nextInt(100, 1000))
            ));

    public static Flux<Order> getOrders(int userId) {
        return Flux.fromIterable(orderDB.get(userId))
                .delayElements(Duration.ofMillis(500))
                .transform(Util.fluxLogger("user" + userId));
    }
}
