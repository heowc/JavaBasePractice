package com.tistory.heowc.blockingqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTests {

    private SynchronousQueue<Integer> queue;

    @Before
    public void before_init() {
        queue = new SynchronousQueue<>(); // default is false
    }

    @Test
    public void test_offer() throws Exception {
        // This queue does not have buffer
        Assert.assertFalse("Queue full", queue.offer(1));
    }
}
