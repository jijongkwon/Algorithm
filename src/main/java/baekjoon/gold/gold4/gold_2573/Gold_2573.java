package baekjoon.gold.gold4.gold_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solution
 * 1. dfs or bfs로 1년 후 동서남북에 0의 개수에 따라 빙산 높이를 감소시킨다
 * 2. 덩어리 개수를 센다
 * 3. 덩어리가 두 개가 된 이후 시간을 return 한다
 * 4. 만약 덩어리가 두 개 이상 분리 되지 않는 다면 0을 return 한다
 *
 * bigO : 2^n 이하
 * return : 두 덩어리 이상으로 분리되는 시간(년)
 */
public class Gold_2573 {
    static boolean[][] visited;
    static int[][] icebergs;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input length, weight
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        icebergs = new int[n][m];

        // input icebergs
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0 ; j < m ; j++){
                icebergs[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int answer = 0;
        while(true){
            minusIcebergs();
            answer++;

            int countIcebergs = countIcebergs();


            if(countIcebergs >= 2){
                System.out.println(answer);
                break;
            }

            if(countIcebergs == 0){
                System.out.println(0);
                break;
            }
        }


    }

    // 빙산 제거
    public static void minusIcebergs(){
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m ; j++){
                if(icebergs[i][j] != 0 && !visited[i][j]){
                    visited[i][j] = true;

                    for(int k = 0; k < 4; k++){
                        int nx = dx[k] + i;
                        int ny = dy[k] + j;

                        if(icebergs[nx][ny] == 0 && !visited[nx][ny]){
                            icebergs[i][j]--;

                            if(icebergs[i][j] == 0){
                                break;
                            }
                        }
                    }

                }
            }
        }
    }

    // 빙산 개수 세기
    public static int countIcebergs(){
        visited = new boolean[n][m];
        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m ; j++){
                if(icebergs[i][j] != 0 && !visited[i][j]){
                    dfs(i,j);
                    count++;
                }
            }
        }

        return count;
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;

        for(int i = 0 ; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                continue;
            }

            if(icebergs[nx][ny] != 0 && !visited[nx][ny]){
                dfs(nx, ny);
            }
        }
    }
}
