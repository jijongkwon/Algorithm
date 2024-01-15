package baekjoon.gold.gold5.gold_27211;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Gold_27211 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }


        int answer = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0 && !visited[i][j]) {
                    bfs(i,j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for(int i = 0 ; i < 4; i++) {
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;

                if(nx < 0) {
                    nx = n - 1;
                }

                if(ny < 0) {
                    ny = m - 1;
                }

                if(nx >= n){
                    nx = 0;
                }

                if(ny >= m) {
                    ny = 0;
                }

                if(map[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
