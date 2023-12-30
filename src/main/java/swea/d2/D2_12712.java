package swea.d2;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class D2_12712
{
    static int n,m;
    static int[][] map;
    static int answer = 0;

    // 4방 탐색 델타
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // X자 탐색 델타
    static int[] dcx = {-1, -1, 1, 1};
    static int[] dcy = {-1, 1, 1, -1};

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            n = sc.nextInt();
            m = sc.nextInt();
            answer = 0;

            map = new int[n][n];

            // input map data
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    map[i][j] = sc.nextInt();
                }
            }

//            // output test
//            for(int[] test : map){
//                System.out.println(Arrays.toString(test));
//            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int plusSum = spray(dx,dy,i,j);
                    int crossSum = spray(dcx, dcy, i, j);
                    int max = Math.max(plusSum,crossSum);
                    answer = Math.max(max, answer);
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static int spray(int[] dx, int[] dy, int x, int y) {
        int sum = map[x][y];

        for (int i = 1; i < m; i++) {
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d] * i;
                int ny = y + dy[d] * i;

                // 범위 체크
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                sum += map[nx][ny];
            }
        }

        return sum;
    }
}