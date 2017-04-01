package com.tistory.heowc.blockingqueue;

import org.junit.Before;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTests {

    private SynchronousQueue<Integer> queue;

    @Before
    public void before_init() {
        queue = new SynchronousQueue<>(); // default is false
    }
}
