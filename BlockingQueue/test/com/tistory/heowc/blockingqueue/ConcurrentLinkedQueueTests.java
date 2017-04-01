package com.tistory.heowc.blockingqueue;

import org.junit.Before;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTests {

    private ConcurrentLinkedQueue<Integer> queue;

    @Before
    public void before_init() {
        queue = new ConcurrentLinkedQueue<>();
    }


}
