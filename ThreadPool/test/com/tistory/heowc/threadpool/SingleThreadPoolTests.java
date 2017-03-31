package com.tistory.heowc.threadpool;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class SingleThreadPoolTests {

    private ExecutorService executorService;

    @Before
    public void before_init() throws Exception {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void test_one() throws Exception {
        IntStream
        .range(0, 10)
        .forEach(action -> executorService.execute(() -> System.out.println("Thread Execute")));


    }
}
