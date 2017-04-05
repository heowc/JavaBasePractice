package com.tistory.heowc.datastructure;

public class Queue {

    private final int maxSize;
    private int top = -1;
    private Node [] nodeArray;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        nodeArray = new Node[maxSize];
    }

    public void offer(Node node) {
        if( isFull() ) throw new StackOverflowError("Custom Queue Overflow Exception");
        nodeArray[++top] = node;
    }

    public Node poll() {
        if( isEmpty() ) throw new NullPointerException("Custom Queue NullPointer Exception");
        return firstPollAndSort();
    }

    private boolean isFull() {
        return (maxSize-1) == top;
    }

    private boolean isEmpty() {
        return top == -1;
    }
    
    private Node firstPollAndSort() {
        Node node = nodeArray[0];
        for (int i = 0; i < top; i ++) {
            nodeArray[i] = nodeArray[i+1];
        }
        top--;
        return node;
    }
}
