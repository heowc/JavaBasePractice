package com.tistory.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelectionSortTest {

	private SelectionSort selectionSort;

	@BeforeEach
	public void before_init() throws Exception {
		Integer[] array = {3, 1, 6, 9, 4};
		selectionSort = new SelectionSort(array);
	}

	@Test
	public void test_sortAndPrint() {
		selectionSort.sort();
		selectionSort.print();
	}
}
