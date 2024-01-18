package baekjoon.silver.silver1.silver_1932;

import java.util.Scanner;

public class Silver_1932 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] dp = new int[n + 1][n + 1];
        int[][] map = new int[n + 1][n + 1];

        for(int i = 1 ; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dp[1][1] = map[1][1];
        int max =  map[1][1];

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + map[i][j];
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
    }
}
