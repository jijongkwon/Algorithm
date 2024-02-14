import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int count = 0;
    static int r, c;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String temp = sc.next();
            for (int j = 0; j < c; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            if (dfs(i, 0)) {
                count++;
            }
        }

        System.out.println(count);
    }

    static boolean dfs(int x, int y) {
        // 종료 조건
        if (y == c - 1) {
            return true;
        }

        // 메모이제이션
        if (visited[x][y]) {
            return false;
        }

        visited[x][y] = true;

        // 인접한 세 방향으로 이동
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < r && ny < c && map[nx][ny] != 'x' && !visited[nx][ny]) {
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }

        return false;
    }
}