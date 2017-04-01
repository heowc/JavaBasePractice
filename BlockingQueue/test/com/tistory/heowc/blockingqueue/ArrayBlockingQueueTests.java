package com.tistory.heowc.blockingqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;

public class ArrayBlockingQueueTests {

    private ArrayBlockingQueue<String> queue;

    @Before
    public void before_init() {
        queue = new ArrayBlockingQueue<>(10);
    }

    @Test
    public void test_add() throws Exception {
        IntStream.range(0, 11)
                .forEach(value -> {
                    try {
                        queue.add(String.valueOf(value));
                    } catch (IllegalStateException e) {
                        e.printStackTrace(); // Queue full
                    }
                });

        Assert.assertEquals("Queue full", queue.remainingCapacity(), 0);
    }

    @Test
    public void test_offer() throws Exception {
        IntStream.range(0, 11)
                .forEach(value -> {
                    boolean isOffer = queue.offer(String.valueOf(value));

                    if ( !isOffer ) {
                        System.out.println("Queue full");
                    }
                });

        Assert.assertEquals("Queue full", queue.remainingCapacity(), 0);
    }

    @Test
    public void test_put() throws Exception {
        IntStream.range(0, 10)
                .forEach(value -> {
                    try {
                        queue.put(String.valueOf(value));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        // queue 이상으로 넣을 시, wait

        Assert.assertEquals("Queue full", queue.remainingCapacity(), 0);
    }

    @Test
    public void test_take() throws Exception {
        IntStream.range(0, 1)
                .forEach(value -> queue.offer(String.valueOf(value)));
        // queue가 빌 때 take 할 경우, wait
        Assert.assertEquals("Queue take is 0", queue.take(), "0");
    }

    @Test
    public void test_poll() throws Exception {
        Assert.assertEquals("Queue poll is null", queue.poll(), null);
    }

    @Test
    public void test_remove() throws Exception {
        Assert.assertEquals("Queue remove is false", queue.remove("1"), false);
    }


    @Test
    public void test_drainTo() throws Exception {
        queue.offer("1");
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");

        Set<String> removeSet = new HashSet<>();
        removeSet.add("1");

        Assert.assertEquals("Queue drainTo count is 2",
                queue.drainTo(removeSet, 2), 2);
    }
}
