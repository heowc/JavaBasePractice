package com.tistory.heowc.datastructure;

import java.util.EmptyStackException;

public class Queue {

    private final int maxSize;
    private int top = -1;

    private QueueNode frontNode;
    private QueueNode endNode;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(int idx) {
        QueueNode node = new QueueNode(idx);

        if ( isEmpty() ) {
            frontNode = node;
            endNode = node;
        } else {
            endNode.nextNode = node;
            endNode = node;
        }
    }

    public QueueNode poll() {
        if( isEmpty() ) throw new EmptyStackException();
        QueueNode node = frontNode;
        frontNode = frontNode.nextNode;
        return node;
    }

    private boolean isEmpty() {
        return frontNode == null;
    }
}
