import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // map
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;

    // 시작, 종료
    static int[] startLoc = new int[3];
    static int[] targetLoc = new int[3];

    // 동서남북
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[n][m];
        visited = new boolean[n][m][5];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작위치, 목표위치
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            startLoc[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            targetLoc[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // bfs
        System.out.println(bfs(startLoc, targetLoc));
    }

    public static int bfs(int[] startLoc, int[] targetLoc){
        Queue<Robot> queue = new LinkedList<>();

        queue.add(new Robot(startLoc[0], startLoc[1], startLoc[2], 0));
        visited[startLoc[0]][startLoc[1]][startLoc[2]] = true;

        while(!queue.isEmpty()){
            Robot robot = queue.poll();

//            System.out.println(robot);

            // done
            if(robot.x == targetLoc[0] && robot.y == targetLoc[1] && robot.dir == targetLoc[2]){
                return robot.count;
            }

            // go
            for (int i = 1; i <= 3; i++) {
                int nx = robot.x + dx[robot.dir] * i;
                int ny = robot.y + dy[robot.dir] * i;

                if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                    continue;
                }

                if(map[nx][ny] == 1){
                    break;
                }

                if(visited[nx][ny][robot.dir]){
                    continue;
                }

                visited[nx][ny][robot.dir] = true;
                queue.add(new Robot(nx, ny, robot.dir, robot.count + 1));
            }

            // turn
            int left = 0;
            int right = 0;

            // 동1 서2 남3 북4
            // 오른쪽 동0 -> 남2 -> 서1 -> 북3
            // 왼쪽 동0 -> 북3 -> 서1 -> 남2
            switch(robot.dir) {
                case 0: left = 3; right = 2; break;
                case 1: left = 2; right = 3; break;
                case 2: left = 0; right = 1; break;
                case 3: left = 1; right = 0; break;
            }

            if(!visited[robot.x][robot.y][left]){
                visited[robot.x][robot.y][left] = true;
                queue.add(new Robot(robot.x, robot.y, left, robot.count + 1));
            }

            if(!visited[robot.x][robot.y][right]){
                visited[robot.x][robot.y][right] = true;
                queue.add(new Robot(robot.x, robot.y, right, robot.count + 1));
            }
        }

        return -1;
    }

    static class Robot{
        int x;
        int y;
        int dir;
        int count;

        public Robot(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", count=" + count +
                    '}';
        }
    }
}