package com.tistory.heowc.datastructure;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.EmptyStackException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueueTests {

    private static Queue QUEUE;

    @BeforeClass
    public static void beforeClass_init() {
        QUEUE = new Queue(5);
    }

    @Test
    public void test1_offer() throws Exception {
        try {
            QUEUE.offer(1);
            QUEUE.offer(2);
            QUEUE.offer(3);
            QUEUE.offer(4);
            QUEUE.offer(5);
            QUEUE.offer(6);
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2_poll() throws Exception {
        try {
            System.out.println(QUEUE.poll());
            System.out.println(QUEUE.poll());
            System.out.println(QUEUE.poll());
            System.out.println(QUEUE.poll());
            System.out.println(QUEUE.poll());
            System.out.println(QUEUE.poll());
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }
}
