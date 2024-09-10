package com.vudd.reactiveprogrammingplayground.sec09;

import com.vudd.reactiveprogrammingplayground.common.Util;
import com.vudd.reactiveprogrammingplayground.sec09.helper.NameGenerator;

public class Lec02StartWithUseCase {
    public static void main(String[] args) {
        NameGenerator generator = new NameGenerator();

        generator.generateNames()
                .take(2)
                .subscribe(
                        Util.subscriber("sam")
                );

        generator.generateNames()
                .take(2)
                .subscribe(
                        Util.subscriber("mike")
                );

        generator.generateNames()
                .take(3)
                .subscribe(
                        Util.subscriber("jake")
                );
    }
}
