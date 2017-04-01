package com.tistory.heowc.blockingqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
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
    }
}
