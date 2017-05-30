package com.tistory.heowc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ScheduledThreadApplication {

    public static void main(String [] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        IntStream.range(0, 100)
                .forEach(action -> scheduledExecutorService.schedule(() -> {
                            System.out.println(scheduledExecutorService.toString());
                        },1000L, TimeUnit.MILLISECONDS));
    }
}
