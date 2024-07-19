package com.vudd.reactiveprogrammingplayground.sec03;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec03.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec07FluxVsList {
    public static void main(String[] args) {
//         List<String> namesList = NameGenerator.getNamesList(5);
//         System.out.println(namesList);

         Flux<String> namesFlux = NameGenerator.getNamesFlux(5);
         namesFlux.subscribe(
                 Util.subscriber()
         );
    }
}
