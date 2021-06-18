package com.tistory.sort;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BubbleSortTest {

    private BubbleSort bubbleSort;

    @BeforeEach
    void before_init() throws Exception {
        Integer [] array = {3, 1, 6, 9, 4};
        bubbleSort = new BubbleSort(array);
    }

    @Test
    void test_sortAndPrint() {
        bubbleSort.sort();
        bubbleSort.print();
    }
}
