package com.tistory.algorithm;

import java.util.Scanner;
/**
 * <pre>
 * <b>탐욕 알고리즘</b>
 *  - 매 선택 마다 최선의 선택을 하려고 하는 알고리즘.
 *  - 전체적인 효율이 떨어질 수 있다.
 *
 * <b>예시</b>
 *  - 14의 경우, 7원 2개가 효율적이다.
 *  - 탐욕 알고리즘을 이용하면 1원 4개, 10원 1개라는 결과가 나온다.
 * </pre>
 */
public class GreedyAlgorithm {

    public static void main(String [] args) {
        int i = 2;

        int coins [] = {1, 7, 10};
        int counts [] = new int[3];

        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();

        while ( i >= 0 ) {
            if ( coins[i] > money ) {
                i--;
            } else if ( coins[i] <= money ) {
                counts[i]++;
                money -= coins[i];
            } else {
                break;
            }
        }

        System.out.printf("\n결과 : 1원 %d개, 7원 %d개, 10원 %d개", counts[0], counts[1], counts[2]);
    }
}
