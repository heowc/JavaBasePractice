package com.tistory.heowc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SingleThreadPoolApplication {

    public static void main(String [] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10)
                .forEach(action -> executorService.execute(() -> {
                    System.out.println("Thread Execute");
                }));

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        IntStream.range(0, 10)
//                .forEach(action -> executorService.execute(() -> {
//                    System.out.println("Thread Execute");
//                }));
    }
}
