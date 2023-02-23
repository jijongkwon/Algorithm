package baekjoon.gold.gold5.gold_1107;

import java.util.Scanner;

public class Gold_1107 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // target
        int N = sc.nextInt();

        // broken button
        int M = sc.nextInt();

        boolean[] button = new boolean[10];
        for(int i = 0; i < M; i++){
            int broken = sc.nextInt();
            button[broken] = true;
        }

        // 완전 탐색
        int result = Math.abs(N - 100);
        for(int i = 0; i < 999999; i++){
            String str = String.valueOf(i);
            int len = str.length();

            boolean pushBrokenButton = false;
            for(int j = 0; j < len; j++){
                if(button[str.charAt(j) - '0']){
                    pushBrokenButton = true;
                    break;
                }
            }

            if(!pushBrokenButton){
                int min = Math.abs(N - i) + len;
                result = Math.min(min,result);
            }
        }

        System.out.println(result);
    }
}
