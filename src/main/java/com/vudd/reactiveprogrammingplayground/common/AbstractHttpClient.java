package com.vudd.reactiveprogrammingplayground.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {
    private static final String BASE_URL = "http://localhost:7070";

    protected final HttpClient client;

    public AbstractHttpClient() {
        LoopResources loopResources = LoopResources.create("vux", 1, true); // 1 thread
        this.client = HttpClient.create() // create a new HttpClient
                .runOn(loopResources) // run on the loopResources
                .baseUrl(BASE_URL); // set the base URL
    }
}
