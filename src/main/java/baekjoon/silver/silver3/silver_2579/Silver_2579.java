package baekjoon.silver.silver3.silver_2579;

import java.util.Scanner;

/**
 * Solution :
 * 1. scores[i] = Math.max(scores[i - 2], scores[i - 3] + stairs[i - 1]) + stairs[i]
 * bigO : N ^ 2 이하
 * return : 점수의 최대값
 */
public class Silver_2579 {
    static int n;
    static int[] scores;
    static int[] stairs;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        scores = new int[n + 1];
        stairs = new int[n + 1];

        for(int i = 1 ; i < n + 1 ; i++){
            stairs[i] = sc.nextInt();
        }

        scores[1] = stairs[1];

        if(n >= 2){
            scores[2] = stairs[1] + stairs[2];
        }

        for(int i = 3; i <= n; i++){
            scores[i] = Math.max(scores[i - 2], scores[i - 3] + stairs[i - 1]) + stairs[i];
        }

        System.out.println(scores[n]);
    }
}
