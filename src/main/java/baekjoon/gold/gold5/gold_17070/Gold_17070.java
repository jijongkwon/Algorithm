package baekjoon.gold.gold5.gold_17070;

import java.util.Scanner;

/**

 solution : dfs
 */
public class Gold_17070 {
    static int n, count = 0;
    static int[][] map;
    static int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dfs(0, 1, 0);
        System.out.println(count);
    }  static void dfs(int x, int y, int direction) {
        if (x == n - 1 && y == n - 1) {
            count++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx >= n || ny >= n) {
                continue;
            }

            if (direction == 0) {
                if (checkRigth(nx, ny, x, y)) {
                    dfs(nx, ny, 0);
                }

                if (checkDiagonal(nx, ny, x, y)) {
                    dfs(nx, ny, 1);
                }
                continue;
            }

            if (direction == 1) {
                if (checkRigth(nx, ny, x, y)) {
                    dfs(nx, ny, 0);
                }

                if (checkDown(nx, ny, x, y)) {
                    dfs(nx, ny, 2);
                }

                if (checkDiagonal(nx, ny, x, y)) {
                    dfs(nx, ny, 1);
                }
                continue;
            }

            if (checkDown(nx, ny, x, y)) {
                dfs(nx, ny, 2);
            }

            if (checkDiagonal(nx, ny, x, y)) {
                dfs(nx, ny, 1);
            }
        }
    }  static boolean checkRigth(int nx, int ny, int x, int y) {
        return nx == x && ny == y + 1 && map[nx][ny] != 1;
    }  static boolean checkDown(int nx, int ny, int x, int y) {
        return nx == x + 1 && ny == y && map[nx][ny] != 1;
    }  static boolean checkDiagonal(int nx, int ny, int x, int y) {
        return nx == x + 1 && ny == y + 1 && map[nx][ny] != 1 && map[nx - 1][ny] != 1 && map[nx][ny - 1] != 1;
    }
}