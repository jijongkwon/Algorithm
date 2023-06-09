package baekjoon.gold.gold5.gold_7576;

import java.io.*;
import java.util.*;

/*
*   bfs 이용
*   시간복잡도 여유
* */
public class Gold_7576 {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // m 가로, n 세로 입력
        st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // 맵 초기화
        map = new int[n][m];

        // 맵 정보 입력
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < m; j++){
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }

        bfs(n, m);
    }

    static void bfs(int n, int m){
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 1){
                    queue.add(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] loc = queue.poll();
            int x = loc[0];
            int y = loc[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0|| ny < 0 || nx >= n || ny >= m){
                    continue;
                }

                if(map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        int max = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                max = Math.max(max, map[i][j]);
                if(map[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(max - 1);
    }
}
