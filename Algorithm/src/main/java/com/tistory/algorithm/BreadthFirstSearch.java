package com.tistory.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * <b>너비 우선 탐색</b>
 *  - 스택을 사용하지 않고 큐를 사용하여, 트리나 그래프에서의 탐색에 사용되는 알고리즘.
 *
 * 1 1 1 1 1 1
 * 0 0 1 0 0 1
 * 1 1 1 0 1 1
 * 1 0 0 0 1 0
 * 1 1 1 0 1 0
 * 0 0 1 1 1 1
 *
 * 답 : 13
 * </pre>
 */
public class BreadthFirstSearch {

    private static int N = 6;
    private static int [][] MAP = new int[N][N];
    private static int MIN = N * N;
    private static Queue<Point> QUEUE = new LinkedList<>();

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MAP[i][j] = scanner.nextInt();
            }
        }

        getShortRoute(new Point(0, 0, 1));

        System.out.printf("최단 거리 : %d\n", MIN);
    }

    private static void getShortRoute(Point point) {
        QUEUE.offer(point);

        while ( !QUEUE.isEmpty() ) {
            printShortRouteStatus();

            Point selectedPoint = QUEUE.poll();

            MAP[selectedPoint.y][selectedPoint.x] = 0;

            if ( isUp(selectedPoint) ) {
                QUEUE.offer(new Point(selectedPoint.x, selectedPoint.y - 1, selectedPoint.count + 1));
            }
            if ( isDown(selectedPoint) ) {
                QUEUE.offer(new Point(selectedPoint.x, selectedPoint.y + 1, selectedPoint.count + 1));
            }
            if ( isLeft(selectedPoint) ) {
                QUEUE.offer(new Point(selectedPoint.x - 1, selectedPoint.y, selectedPoint.count + 1));
            }
            if ( isLight(selectedPoint) ) {
                QUEUE.offer(new Point(selectedPoint.x + 1, selectedPoint.y, selectedPoint.count + 1));
            }

            // 마지막 위치
            if (selectedPoint.x == N - 1 && selectedPoint.y == N - 1) {
                if (MIN > selectedPoint.count) MIN = selectedPoint.count;
                return;
            }
        }
    }

    private static boolean isUp(Point point) {
        return point.y > 0 && MAP[point.y - 1][point.x] == 1;
    }

    private static boolean isDown(Point point) {
        return point.y < N - 1 && MAP[point.y + 1][point.x] == 1;
    }

    private static boolean isLeft(Point point) {
        return point.x > 0 && MAP[point.y][point.x - 1] == 1;
    }

    private static boolean isLight(Point point) {
        return point.x < N - 1 && MAP[point.y][point.x + 1] == 1;
    }

    private static class Point {
        int x;
        int y;
        int count;

        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    '}';
        }
    }

    private static void printShortRouteStatus() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============================");
    }
}