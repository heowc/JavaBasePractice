package com.tistory.sort;

/**
 * <pre>삽입 정렬</pre>
 * - 정렬이 안된 데이터를 기준으로, 정렬된 구간에 끼워 넣는 정렬
 */
public class InsertionSort {

    private Integer[] array;
    private int size;

    public InsertionSort(Integer[] array) {
        this.array = array;
        this.size  = array.length;
    }

    public void sort() {

        int temp, min;

        for (int i = 1; i < size; i++) {
            temp = array[i];
            min = i - 1;
            while( (min >= 0) && (array[min] > temp) ) {
                array[min+1] = array[min];
                min--;
            }
            array[min+1] = temp;

            System.out.printf("%d번째 ==>", i);
            print();
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d", array[i]);
        }
        System.out.println();
    }
}
