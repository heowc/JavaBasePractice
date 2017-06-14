package com.tistory.sort;

/**
 * <pre>버블 정렬</pre>
 * - 바로 왼쪽 배열값과 비교하며 뒤로 미뤄지는 정렬
 */
public class BubbleSort {

    private Integer[] array;
    private int size;

    public BubbleSort(Integer[] array) {
        this.array = array;
        this.size  = array.length;
    }

    public void sort() {

        int temp;

        for (int i = 0; i < size-1; i++) {
            System.out.printf("%d번째 ==>", i);
            print();

            for (int j = 0; j < size-1; j++) {
                if(array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    print();
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d", array[i]);
        }
        System.out.println();
    }
}
