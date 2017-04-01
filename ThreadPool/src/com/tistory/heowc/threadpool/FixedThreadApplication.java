package com.tistory.heowc.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class FixedThreadApplication {

    public static void main(String [] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        IntStream.range(0, 100)
                .mapToObj(value -> executorService.submit(() -> {
                    try {
                        if (value%2 == 0) {
                            Thread.sleep(1000L);
                        }

                        return String.valueOf(value * 2);
                    } catch (InterruptedException e) {
                        return "";
                    }
                }))
                .forEach(action -> {
                    try {
                        System.out.println("Thread Info  : " + executorService.toString());
                        System.out.println("Result Value : " + action.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }
}
