package com.tistory.algorithm;

import java.util.Scanner;

/**
 * <pre>
 * <b>깊이 우선 탐색</b>
 *  - 스택을 이용하여, 트리 혹은 그래프 같은 자료구조의 데이터를 탐색할 때 사용하는 알고리즘.
 *
 * <b>예시</b>
 * 1 1 1 1 1
 * 0 0 0 0 1
 * 1 1 1 1 1
 * 1 0 1 0 0
 * 1 1 1 1 1
 *
 * 답 : 13
 * </pre>
 */
public class DepthFirstSearch {

    private static int N = 5;
    private static int [][] MAP = new int[N][N];
    private static int MIN = N * N;

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MAP[i][j] = scanner.nextInt();
            }
        }

        getShortRoute(0, 0, 1);

        System.out.printf("최단 거리 : %d\n", MIN);
    }

    private static void getShortRoute(int x, int y, int length) {

        // 마지막 위치
        if (x == N - 1 && y == N - 1) {
            if (MIN > length) MIN = length;
            return;
        }

        MAP[y][x] = 0;

        if ( isUp(x, y) ) getShortRoute(x, y - 1, length + 1);
        if ( isDown(x, y) ) getShortRoute(x, y + 1, length + 1);
        if ( isLeft(x, y) ) getShortRoute(x - 1, y, length + 1);
        if ( isRight(x, y) ) getShortRoute(x + 1, y, length + 1);

        MAP[y][x] = 1;
    }

    private static boolean isUp(int x, int y) {
        return y > 0 && MAP[y - 1][x] != 0;
    }

    private static boolean isDown(int x, int y) {
        return y < N - 1 && MAP[y + 1][x] != 0;
    }

    private static boolean isLeft(int x, int y) {
        return x > 0 && MAP[y][x - 1] != 0;
    }

    private static boolean isRight(int x, int y) {
        return x < N - 1 && MAP[y][x + 1] != 0;
    }
}
