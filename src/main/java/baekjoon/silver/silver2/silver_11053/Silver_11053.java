package baekjoon.silver.silver2.silver_11053;

import java.util.Scanner;

/**
 * dp[i] = Math.max(dp[i - 1],
 */
public class Silver_11053 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] numbers = new int[n + 1];
        int[] dp = new int[n + 1];

        for(int i = 1 ; i <= n; i++) {
            numbers[i] = sc.nextInt();
            dp[i] = 1;
        }

        int max = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
