package com.tistory.algorithm;

import java.util.Scanner;

/**
 * <pre>
 * <b>깊이 우선 탐색</b>
 *  - 스택을 이용하여, 트리 혹은 그래프 같은 자료구조의 데이터를 탐색할 때 사용하는 알고리즘.
 *
 * </pre>
 */
public class DepthFirstSearch {

    static int n = 5;
    static int [][] map = new int[n][n];
    static int min = n * n;

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        getShortRoute(0, 0, 1);

        System.out.printf("최단 거리 : %d\n", min);
    }

    private static void getShortRoute(int x, int y, int length) {

        if (x == n - 1 && y == n - 1) {
            if (min > length) min = length;
            return;
        }

        map[y][x] = 0;

        if ( y > 0 && map[y - 1][x] != 0 ) getShortRoute(x, y - 1, length + 1);
        if ( y < n - 1 && map[y + 1][x] != 0 ) getShortRoute(x, y + 1, length + 1);
        if ( x > 0 && map[y][x - 1] != 0 ) getShortRoute(x - 1, y, length + 1);
        if ( x < n - 1 && map[y][x + 1] != 0 ) getShortRoute(x + 1, y, length + 1);

        map[y][x] = 1;
    }
}
