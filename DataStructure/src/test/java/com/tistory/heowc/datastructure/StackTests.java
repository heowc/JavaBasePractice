package com.tistory.heowc.datastructure;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.EmptyStackException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StackTests {

    private static Stack STACK;

    @BeforeClass
    public static void beforeClass_init() {
        STACK = new Stack(5);
    }

    @Test
    public void test1_push() {
        STACK.push(1);
        STACK.push(2);
        STACK.push(3);
        STACK.push(4);
        STACK.push(5);
    }

    @Test
    public void test2_pop() {
        STACK.pop();
        STACK.pop();
        STACK.pop();
        STACK.pop();
        STACK.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void test3_pop() {
        STACK.pop();
    }

    @Test
    public void test4_push() {
        try {
            STACK.push(1);
            STACK.push(2);
            STACK.push(6);
            STACK.push(4);
            STACK.push(5);
        } catch (StackOverflowError e) {
            e.printStackTrace();
        }
    }
}
