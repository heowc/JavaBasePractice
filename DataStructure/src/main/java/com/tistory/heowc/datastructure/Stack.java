package com.tistory.heowc.datastructure;

import java.util.EmptyStackException;

public class Stack {

    private final int maxSize;
    private int top;

    private Node [] nodeArray;

    public Stack(int maxSize) {
        this.top = -1;
        this.maxSize = maxSize;
        this.nodeArray = new Node[maxSize];
    }

    public void push(int data) {
        if ( isFull() ) throw new StackOverflowError();
        nodeArray[++top] = new Node(data);
    }

    public int pop() {
        if ( isEmpty() ) throw new EmptyStackException();
        return nodeArray[top--].idx;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return (maxSize-1) == top;
    }
}
