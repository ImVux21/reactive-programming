package com.vudd.reactiveprogrammingplayground.sec02;

import com.vudd.reactiveprogrammingplayground.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07MonoFromRunnable {
    private static final Logger lgo = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(2)
                .subscribe(
                        Util.subscriber()
                );
    }

    private static Mono<String> getProductName(int productId) {
        if (productId == 1) return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        // why Mono.fromRunnable(() -> Util.faker().commerce().productName()) isn't return anything?
        // because Mono.fromRunnable() is used to perform side effect, not to return anything (Mono<Void>)
        // what is the side effect?
        // the side effect is to notify the business that the product is unavailable
//        return Mono.fromRunnable(() -> Util.faker().commerce().productName());
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId) {
        lgo.info("Notify the business on unavailable product {}", productId);
    }
}
