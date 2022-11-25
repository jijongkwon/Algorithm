package baekjoon.silver.silver3.silver_15649;

import java.util.Scanner;

public class Silver_15649 {
    static int[] printNumberList;
    static boolean visitedChecking[];
    static StringBuilder sb = new StringBuilder();
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 값 입력
        n = scanner.nextInt();
        m = scanner.nextInt();

        // list 초기화
        printNumberList = new int[m];

        // 방문여부 확인
        visitedChecking = new boolean[n+1];


        // 0부터 시작
        backTracking(0);
        System.out.println(sb);
    }

    // 백 트래킹
    static public void backTracking(int index){
        if(index == m){
            for (int num : printNumberList){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= n; i++){
            //방문 한 경우
            if(visitedChecking[i]){
                continue;
            }

            //방문 안한 경우
            visitedChecking[i] = true;
            printNumberList[index] = i;
            backTracking(index+1);
            visitedChecking[i] = false;
        }
    }
}
