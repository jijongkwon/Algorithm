package softeer.level2.지도자동구축;

import java.util.*;
import java.io.*;


public class Solution
{
    /*
        피보나치 수열
        dp로 해결
    */
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] dp = new int[N + 1];

        dp[0] = 2;
        dp[1] = 3;

        for(int i = 2; i < N + 1; i++){
            dp[i] = dp[i - 1] + (int)(Math.pow(2, i - 1));
        }

        System.out.println((int)Math.pow(dp[N],2));
    }
}