package com.tistory.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * <b>이진 탐색</b>
 *  - 비교를 거칠 때 마다, 탐색 범위를 반으로 줄이며 탐색하는 알고리즘.
 * </pre>
 */
public class BinarySearch {

    public static void main(String [] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(search(list, 7));
    }

    private static Integer search(List<Integer> list, Integer data) {
        int low = 0, high = list.size() - 1, mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (list.get(mid) > data) high = mid - 1;
            else if (list.get(mid) < data) low = mid + 1;
            else return list.get(mid);
        }

        throw new IllegalArgumentException(String.format("%d를 찾을 수 없습니다.", data));
    }
}
