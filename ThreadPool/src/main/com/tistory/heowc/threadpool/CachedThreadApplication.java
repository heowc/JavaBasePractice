package com.tistory.heowc.threadpool;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CachedThreadApplication {

    public static void main(String [] args) throws InterruptedException {
        System.out.println("================ getFairIsTrueCachedExecutorService");
        ExecutorService executorService1 = getFairIsTrueCachedExecutorService();
        printExecutorService(executorService1);

        Thread.sleep(3000L);

        System.out.println("================ getFairIsFalseCachedExecutorService");
        ExecutorService executorService2 = getFairIsFalseCachedExecutorService();
        printExecutorService(executorService2);
    }

    private static ExecutorService getFairIsTrueCachedExecutorService() {
        return Executors.newCachedThreadPool();

    }

    private static ExecutorService getFairIsFalseCachedExecutorService() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                        60L, TimeUnit.SECONDS,
                                        new SynchronousQueue<Runnable>(true));
    }

    private static void printExecutorService(ExecutorService executorService) {
        IntStream.range(0, 10)
                .mapToObj(value -> executorService.submit(() -> value))
                .forEach(action -> {
                    executorService.submit(() -> {
                        try {
                            System.out.println(executorService.toString());
                            System.out.println(action.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    });
                });
    }
}
