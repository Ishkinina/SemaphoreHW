package com.homework.cockroach;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eishkinina on 17.03.17.
 */
public class Cockroach implements Callable<String> {

    private static final AtomicInteger position = new AtomicInteger(1);
    private Random random = new Random();
    private String name;
    private int distance;
    private CountDownLatch latch;


    public Cockroach(String name, int distance, CountDownLatch latch) {

        this.name = name;
        this.distance = distance;
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        latch.countDown();
        latch.await(); // ждет пока задвижку поднимут
        int currentPosition = 0;
        while ((currentPosition += random.nextInt(10)) < distance) {
            Thread.sleep(currentPosition * 10);
        };// граница рандома от 0 до 10


        return name + position.getAndIncrement(); // безопасно для тредов, получает позицию и атомарно(никто не вмешается) позицию
    }
}
