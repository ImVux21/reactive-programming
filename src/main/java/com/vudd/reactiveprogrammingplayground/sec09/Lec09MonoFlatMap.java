package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec09.application.PaymentService;
import com.vudd.reactiveprogrammingplayground.sec09.application.UserService;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {
        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());
    }
}