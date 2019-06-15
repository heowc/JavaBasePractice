package com.tistory.heowc.blockingqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class LinkedBlockingQueueTests {

    private LinkedBlockingQueue<Integer> queue;

    @Before
    public void before_init() {
        queue = new LinkedBlockingQueue<>(); // capacity를 지정하지 않으면, Integer.MAX_VALUE 로 지정
    }

    @Test
    public void test_offer() throws Exception {
        IntStream.range(0, 10)
                .forEach(value -> queue.offer(value));

        Assert.assertEquals("Queue Remain Capacity is ( 2^31 - 11 )",
                queue.remainingCapacity(), Integer.MAX_VALUE - 10);
        queue.clear();
    }

    @Test
    public void test_drainToFromMultiThread() {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        ExecutorService putPool = Executors.newFixedThreadPool(2);
        ExecutorService takePool = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 100; i++) {
            putPool.execute(() -> {
                int number = ThreadLocalRandom.current().nextInt(10);
                System.out.println("inserted: " + number);
                queue.offer(number);
                countDownLatch.countDown();
            });
        }

        while (countDownLatch.getCount() != 0 || !queue.isEmpty()) {
            takePool.execute(() -> {
                if (queue.size() >= 10) {
                    System.out.println(putPool);
                    List<Integer> list = new ArrayList<>();
                    queue.drainTo(list, 10);
                    System.out.println(Arrays.toString(list.toArray()));
                }
            });
        }

        System.out.println("remain size = " + queue.size());
    }
}
