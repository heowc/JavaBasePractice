package com.tistory.sort;

import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {

    private BubbleSort bubbleSort;

    @Before
    public void before_init() throws Exception {
        Integer [] array = {3, 1, 6, 9, 4};
        bubbleSort = new BubbleSort(array);
    }

    @Test
    public void test_sortAndPrint() {
        bubbleSort.sort();
        bubbleSort.print();
    }
}
