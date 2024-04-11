import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * n * n 헛간
 * 1 ~ n 까지 *
 * (1,1)에서 출발
 * 방에는 스위치가 있음 ( 다른 방에 불을 끄고 킬 수 있음 )
 * 베시는 상하좌우로 움직임 그리고 불 켜저있는 방으로만 들어갈 수 있음 *
 * 베시가 불을 켤 수 있는 방의 최대 개수를 구하기 *
 * 제한
 * 1 <= n <= 100
 * 1 <= m <= 20,000 *
 * x, y 방에서 a, b의 방의 불을 켜고 끌 수 있음 *
 * 풀이
 * 그냥 BFS 아님 ?
 */
public class Main {
    static int n, m;
    static int[][] map;
    // (x, y) 방에서 (a, b)방의 불을 켜고 끌 수 있음
    static List<Point>[][] turnedOnMap;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        turnedOnMap = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                turnedOnMap[i][j] = new ArrayList<>();
            }
        }

        map = new int[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            turnedOnMap[x][y].add(new Point(a, b));
        }

        // bfs 탐색
        map[0][0] = 1;
        bfs();

        System.out.println(max + 1);
    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));

        boolean[][] visited = new boolean[n][n];

        // 불 켰는지 확인하는 변수
        boolean isTurnOn = false;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 불켜기
            for (Point next : turnedOnMap[p.x][p.y]) {
                if(map[next.x][next.y] == 0){
                    map[next.x][next.y] = 1;
                    isTurnOn = true;
                    max++;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                // 불이 꺼졌으면 ?
                if (map[nx][ny] != 1) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }

        // 만약에 불이 켜져 있는데 방문을 안한 상태라면 ?
        if(isTurnOn){
            // 한 번 더 체크
            bfs();
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}