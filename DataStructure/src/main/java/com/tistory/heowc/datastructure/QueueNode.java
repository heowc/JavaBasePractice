package com.tistory.heowc.datastructure;

public class QueueNode {

    public QueueNode nextNode;
    public int idx;

    public QueueNode(int idx) {
        this.nextNode = null;
        this.idx = idx;
    }
}
