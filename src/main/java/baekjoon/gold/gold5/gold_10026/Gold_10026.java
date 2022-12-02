package baekjoon.gold.gold5.gold_10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Gold_10026 {
    static String map[][];
    static boolean visited[][];
    static int xDirection[] = {0, 0, 1, -1};
    static int yDirection[] = {1, -1, 0, 0};
    static int n = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // R,G,B 입력 받을 크기 입력
        n = Integer.parseInt(br.readLine());
        map = new String[n][n];
        visited = new boolean[n][n];

        // R,G,B 입력
        for (int x = 0; x < n; x++) {
            String input[] = br.readLine().split("");
            for (int y = 0; y < n; y++) {
                map[x][y] = input[y];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        System.out.println(count);

        init();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        System.out.println(count);

    }

    // 초기화
    static void init() {
        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].equals("G")) {
                    map[i][j] = "R";
                }
                visited[i][j] = false;
            }
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        visited[x][y] = true;
        String color = map[x][y];

        while (!queue.isEmpty()) {
            int dir[] = queue.poll();
            for (int i = 0; i < 4; i++) {
                int xDir = dir[0] + xDirection[i];
                int yDir = dir[1] + yDirection[i];

                if (xDir < 0 || xDir >= n || yDir < 0 || yDir >= n) {
                    continue;
                }
                if (!visited[xDir][yDir] && map[xDir][yDir].equals(color)) {
                    visited[xDir][yDir] = true;
                    queue.add(new int[]{xDir, yDir});
                }
            }
        }
    }
}
