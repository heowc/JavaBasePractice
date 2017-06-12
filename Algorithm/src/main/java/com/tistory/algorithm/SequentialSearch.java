package com.tistory.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * <b>순차 탐색</b>
 *  - 데이터를 처음부터 끝까지 차례대로 비교하여 원하는 데이터를 찾아내는 알고리즘.
 * </pre>
 */
public class SequentialSearch {

    public static void main(String [] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(search(list, 7));
    }

    private static Integer search(List<Integer> list, Integer data) {
        for (Integer i: list) {
            if (i.equals(data)) {
                return i;
            }
        }

        throw new IllegalArgumentException(String.format("%d를 찾을 수 없습니다.", data));
    }
}
