package com.tistory.heowc.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class FixedThreadApplication {

    public static void main(String [] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        IntStream.range(0, 10)
                .mapToObj(value -> executorService.submit(() -> String.valueOf(value * 2)))
                .forEach(action -> {
                    try {
                        System.out.println("Pool Size    : " + executorService.getPoolSize());
                        System.out.println("Result Value : " + action.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }
}
