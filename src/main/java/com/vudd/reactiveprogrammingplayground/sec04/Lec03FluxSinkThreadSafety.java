package com.vudd.reactiveprogrammingplayground.sec04;

import com.vudd.reactiveprogrammingplayground.common.Util;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Lec03FluxSinkThreadSafety {
    private static final Logger log = Logger.getLogger(Lec03FluxSinkThreadSafety.class.getName());
    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static void demo1() {
        ArrayList<Integer> list = new ArrayList<>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }

        Util.sleepSeconds(3);
        log.info("size: " + list.size());
    }

    private static void demo2() {
       ArrayList<Integer> list = new ArrayList<>();
//        Flux.create((sink) -> {
//            Runnable runnable = () -> {
//                for (int i = 0; i < 1000; i++) {
//                    sink.next(i);
//                }
//            };
//
//            for (int i = 0; i < 10; i++) {
//                Thread.ofPlatform().start(runnable);
//            }
//        })
//                .subscribe(list::add)
//                .doOnComplete(() -> log.info("size: " + list.size()));


//        Runnable runnable = () -> {
//            for (int i = 0; i < 1000; i++) {
//                list.add(i);
//            }
//        };
//
//        for (int i = 0; i < 10; i++) {
//            Thread.ofPlatform().start(runnable);
//        }
//
//        Util.sleepSeconds(3);
//        log.info("size: " + list.size());
    }
}
