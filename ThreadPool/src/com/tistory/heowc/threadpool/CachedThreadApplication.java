package com.tistory.heowc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class CachedThreadApplication {

    public static void main(String [] args) {
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
                .forEach(action -> threadPoolExecutor.execute(() -> {
                    try {
                        Thread.sleep(1000L);
                        System.out.println(threadPoolExecutor.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));

    }
}
