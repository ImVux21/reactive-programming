package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec09.application.OrderService;
import com.vudd.reactiveprogrammingplayground.sec09.application.User;
import com.vudd.reactiveprogrammingplayground.sec09.application.UserService;

public class Lec11FluxFlatMap {
    public static void main(String[] args) {
        UserService.getUsers()
                .map(User::id)
                .flatMap(OrderService::getOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
