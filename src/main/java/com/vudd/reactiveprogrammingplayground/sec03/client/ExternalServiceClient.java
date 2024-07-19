package com.vudd.reactiveprogrammingplayground.sec03.client;

import com.vudd.reactiveprogrammingplayground.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getNames() {
        return this.client
                .get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }
}
