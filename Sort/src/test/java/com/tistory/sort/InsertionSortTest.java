package com.tistory.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsertionSortTest {

	private InsertionSort insertionSort;

	@BeforeEach
	public void before_init() throws Exception {
		Integer[] array = {3, 1, 9, 6, 4, 5, 8};
		insertionSort = new InsertionSort(array);
	}

	@Test
	void test_sortAndPrint() {
		insertionSort.sort();
		insertionSort.print();
	}
}
