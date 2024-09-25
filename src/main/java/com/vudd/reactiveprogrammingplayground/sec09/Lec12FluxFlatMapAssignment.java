package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec02.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {
    public static void main(String[] args) {
        ExternalServiceClient externalServiceClient = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(externalServiceClient::getProductName)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
