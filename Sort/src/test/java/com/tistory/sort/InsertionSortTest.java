package com.tistory.sort;

import org.junit.Before;
import org.junit.Test;

public class InsertionSortTest {

    private InsertionSort insertionSort;

    @Before
    public void before_init() throws Exception {
        Integer [] array = {3, 1, 9, 6, 4, 5, 8};
        insertionSort = new InsertionSort(array);
    }

    @Test
    public void test_sortAndPrint() {
        insertionSort.sort();
        insertionSort.print();
    }
}
