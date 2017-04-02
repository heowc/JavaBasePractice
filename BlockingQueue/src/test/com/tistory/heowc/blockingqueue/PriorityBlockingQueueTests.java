package com.tistory.heowc.blockingqueue;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

public class PriorityBlockingQueueTests {

    private PriorityBlockingQueue<Integer> queue;

    @Before
    public void before_init() {
        queue = new PriorityBlockingQueue<>();
    }

    @Test
    public void test_offer() {
        IntStream.range(0 , 10)
                .forEach(action -> queue.offer(new Random().nextInt(100) + 1));

        queue.forEach(action -> System.out.println(queue.poll()));
    }
}
