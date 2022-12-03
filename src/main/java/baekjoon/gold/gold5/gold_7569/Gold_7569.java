package baekjoon.gold.gold5.gold_7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold_7569 {
    static Queue<int[]> queue = new LinkedList<>();
    static int map[][][];
    static int xDirection[] = {0, 0, 0, 0, 1, -1};
    static int yDirection[] = {0, 0, 1, -1, 0, 0};
    static int hDirection[] = {1, -1, 0, 0, 0, 0};
    static int m, n, h;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 가로, 세로, 높이 입력
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][n][m];

        // 토마토 담기
        for (int z = 0; z < h; z++) {
            for (int y = 0; y < n; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < m; x++) {
                    map[z][y][x] = Integer.parseInt(st.nextToken());
                    if (map[z][y][x] == 1) {
                        queue.add(new int[]{z, y, x});
                    }
                }
            }
        }

        // 안 익은 토마토 -> 익은 토마토 만들기
        System.out.println(bfs());
    }

    static int bfs() {
        while (!queue.isEmpty()) {
            int queuePoll[] = queue.poll();
            for (int i = 0; i < 6; i++) {
                int height = queuePoll[0] + hDirection[i];
                int length = queuePoll[1] + yDirection[i];
                int width = queuePoll[2] + xDirection[i];

                if (width < 0 || width >= m || length < 0 || length >= n || height < 0 || height >= h) {
                    continue;
                }
                if (map[height][length][width] == 0) {
                    queue.add(new int[]{height, length, width});
                    map[height][length][width] = map[queuePoll[0]][queuePoll[1]][queuePoll[2]] + 1;
                }
            }
        }

        int result = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == 0) {
                        return -1;
                    }

                    result = Math.max(result, map[i][j][k]);
                }
            }
        }

        if (result == 1) {
            return 0;
        }

        return result - 1;
    }
}
