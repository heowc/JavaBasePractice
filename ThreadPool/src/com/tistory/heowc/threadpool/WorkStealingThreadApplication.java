package com.tistory.heowc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class WorkStealingThreadApplication {

    public static void main(String [] args) {
        ExecutorService executorService = Executors.newWorkStealingPool(3);

        IntStream.range(0, 500)
                .forEach(action -> executorService.execute(() -> System.out.println(executorService.toString())));
    }
}
