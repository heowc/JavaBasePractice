package com.tistory.heowc.datastructure;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StackTests {

    private static Stack STACK;

    @BeforeClass
    public static void beforeClass_init() {
        STACK = new Stack(5);
    }

    @Test
    public void test1_push() {
        STACK.push(new Node(1));
        STACK.push(new Node(2));
        STACK.push(new Node(3));
        STACK.push(new Node(4));
        STACK.push(new Node(5));
    }

    @Test
    public void test2_pop() {
        STACK.pop();
        STACK.pop();
        STACK.pop();
        STACK.pop();
        STACK.pop();
    }

    @Test
    public void test3_pop() {
        try {
            STACK.pop();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4_push() {
        try {
            STACK.push(new Node(1));
            STACK.push(new Node(2));
            STACK.push(new Node(3));
            STACK.push(new Node(4));
            STACK.push(new Node(5));
            STACK.push(new Node(6));
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }
}
