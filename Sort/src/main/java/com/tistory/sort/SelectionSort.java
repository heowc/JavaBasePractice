package com.tistory.sort;

/**
 * <pre>선택 정렬</pre>
 * - 이미 정렬된 배열을 제외하고 최소/최대값을 찾아 정렬
 */
public class SelectionSort {

    private Integer[] array;
    private int size;

    public SelectionSort(Integer[] array) {
        this.array = array;
        this.size  = array.length;
    }

    public void sort() {

        int temp, min;

        for (int i = 0; i < size-1; i++) {
            System.out.printf("%d번째 ==>", i);
            print();

            min = i;
            for (int j = i+1; j < size; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d", array[i]);
        }
        System.out.println();
    }
}
