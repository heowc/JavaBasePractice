package com.tistory.heowc.datastructure;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueueTests {

    private static Queue QUEUE;

    @BeforeClass
    public static void beforeClass_init() {
        QUEUE = new Queue(5);
    }

    @Test
    public void test_1offer() throws Exception {
        try {
            QUEUE.offer(new Node(1));
            QUEUE.offer(new Node(2));
            QUEUE.offer(new Node(3));
            QUEUE.offer(new Node(4));
            QUEUE.offer(new Node(5));
            QUEUE.offer(new Node(6));
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_2poll() throws Exception {
        try {
            System.out.println(QUEUE.poll().idx);
            System.out.println(QUEUE.poll().idx);
            System.out.println(QUEUE.poll().idx);
            System.out.println(QUEUE.poll().idx);
            System.out.println(QUEUE.poll().idx);
            System.out.println(QUEUE.poll().idx);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
