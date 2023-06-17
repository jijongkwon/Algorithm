package baekjoon.gold.gold3.gold_2206;

import java.util.*;
import java.io.*;

/**
 *  시간복잡도: n ^ 2 이하
 *
 *  해결 방법
 *  1. bfs
 *  2. 상하좌우 검색
 *  3. 방문여부, 벽 부순적이 있는지 체크
 *  4. 갈 수 없다면 -1 return
 */
public class Gold_2206 {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 크기 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[n][m];
        visited = new boolean[n][m][2];

        for(int i = 0; i < n; i++){
            String[] temp = br.readLine().split("");
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0, 0, false));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Point point = queue.poll();

            if(point.x == n - 1 && point.y == m - 1){
                return point.point + 1;
            }

            for(int i = 0; i < 4; i++){
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;

                if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                    continue;
                }

                if(!point.destroyed){
                    if(map[nx][ny] == 1){
                        visited[nx][ny][1] = true;
                        queue.add(new Point(nx,ny,point.point + 1, true));
                    }
                    else if(!visited[nx][ny][0]){
                        visited[nx][ny][0] = true;
                        queue.add(new Point(nx,ny, point.point + 1, false));
                    }
                }
                else{
                    if(map[nx][ny] == 0 && !visited[nx][ny][1]){
                        visited[nx][ny][1] = true;
                        queue.add(new Point(nx,ny,point.point + 1, true));
                    }
                }
            }
        }

        return -1;
    }

    static class Point{
        int x;
        int y;
        int point;
        boolean destroyed;

        Point(int x, int y, int point, boolean destroyed){
            this.x = x;
            this.y = y;
            this.point = point;
            this.destroyed = destroyed;
        }
    }
}
