package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec09.application.OrderService;
import com.vudd.reactiveprogrammingplayground.sec09.application.UserService;

public class Lec09MonoFlatMapMany {
    public static void main(String[] args) {
        UserService.getUserId("sam")
                .flatMapMany(OrderService::getOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
