package com.vudd.reactiveprogrammingplayground.sec02;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        log.info("Starting non-blocking I/O");

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(
                            Util.subscriber()
                    );
        }

        Util.sleepSeconds(2);
    }
}
