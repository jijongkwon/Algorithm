package baekjoon.silver.silver1.silver_2583;

import java.security.PublicKey;
import java.util.*;

/**
 *
 * return 분리된 영역 개수 및 넓이 출력
 */
public class Silver_2583 {
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> answer = new ArrayList<>();
    static int count = 0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input m,n,k
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();

        // reset map, visited
        map = new int[n][m];
        visited = new boolean[n][m];

        // input vertex
        for(int i = 0; i < k; i++ ){
            int x0 = sc.nextInt();
            int y0 = sc.nextInt();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();

            for(int j = x0; j < x1; j++){
                for(int t = y0; t < y1; t++){
                    map[j][t] = 1;
                }
            }
        }

        // bfs
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] != 1 && !visited[i][j]){
                    bfs(i,j, n, m);
                    count++;
                }
            }
        }

        //sort
        Collections.sort(answer);

        // output
        System.out.println(count);
        for(int i : answer){
            System.out.print(i + " ");
        }
    }

    static public void bfs(int x, int y, int n, int m){
        int width = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));

        while(!queue.isEmpty()){
            Point point = queue.poll();
            visited[point.x][point.y] = true;
            width++;

            for(int i = 0; i < 4; i++){
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                    continue;
                }

                if(!visited[nx][ny] && map[nx][ny] != 1){
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        answer.add(width);
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
