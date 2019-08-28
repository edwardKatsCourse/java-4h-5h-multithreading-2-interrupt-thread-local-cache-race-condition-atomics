package com.telran.race_condition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Counter> threads = Stream.generate(() -> new Counter())
                .limit(5)
                .peek(x -> x.start())
                .collect(Collectors.toList());

        for (Counter counter : threads) {
            counter.join();
        }

        System.out.println(Counter.counter);
    }
}

class Counter extends Thread {

    public static int counter;

    public void run() {

        //Thread копирует значение в Thread Local Cache
        //Сам механизм такого кеширование называется Cache Coherency
        //Проблема, которую он порождает называется Cache Coherency Problem

        //int counterCopy = counter;
        //counterCopy++;
        //counter = counterCopy;

        //t1 counter = 0
        //t1.counter = 1

        //t2 counter = 0
        //t2.counter = 1

        counter++;

    }
}
