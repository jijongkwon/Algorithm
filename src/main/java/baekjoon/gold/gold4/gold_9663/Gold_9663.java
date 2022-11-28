package baekjoon.gold.gold4.gold_9663;

import java.util.Scanner;

public class Gold_9663 {
    static int n;
    static int count = 0;
    static int chessMap[][];
    static boolean visitedMap[][];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 퀸의 개수 및 칸의 크기 ( n * n )
        n = scanner.nextInt();

        chessMap = new int[n][n];
        visitedMap = new boolean[n][n];

        backTracking(0);

        System.out.println(count);
    }

    public static void backTracking(int x){

        if (x == n) {
            count++;
            return;
        }

        for(int y = 0; y < n; y++){
            if(!visitedMap[x][y] && chessMap[x][y] == 0){
                chessMap[x][y] = 1;
                visitedMap[x][y] = true;

                // 놓인 위치에 따른 퀸의 공격 방향 방문 확인
                checkVisitedAndChange(x,y);

                // 재귀
                backTracking(x + 1);

                //초기화
                chessMap[x][y] = 0;
                clear();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (chessMap[i][j] == 1){
                            checkVisitedAndChange(i, j);
                        }
                    }
                }
            }
        }
    }

    /* 양옆과 위로는 체크 할 필요가 없다 */
    public static void checkVisitedAndChange(int x, int y){
        // 아래 방향
        for(int i = x; i < n; i++){
            visitedMap[i][y] = true;
        }

        // 왼쪽 대각선
        int tmpX = x;
        int tmpY = y;
        while(tmpX < n  && tmpY >= 0){
            visitedMap[tmpX++][tmpY--] = true;
        }

        // 오른쪽 대각선
        while(x < n && y < n){
            visitedMap[x++][y++] = true;
        }
    }

    public static void clear(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visitedMap[i][j] = false;
            }
        }
    }
}
