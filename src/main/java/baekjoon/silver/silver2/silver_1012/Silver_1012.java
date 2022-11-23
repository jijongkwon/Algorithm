package baekjoon.silver.silver2.silver_1012;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Silver_1012 {
    static int cabbageField[][];
    static boolean checkField[][];
    static int fieldWidth = 0;
    static int fieldVertical = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int testCase = 0;
        int cabbageNumber = 0;
        int fieldXLocation = 0;
        int filedYLocation = 0;
        int count = 0;

        testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            fieldWidth = scanner.nextInt();
            fieldVertical = scanner.nextInt();
            cabbageNumber = scanner.nextInt();
            cabbageField = new int[fieldVertical][fieldWidth];
            checkField = new boolean[fieldVertical][fieldWidth];

            // 배추 위치 저장
            for (int j = 0; j < cabbageNumber; j++) {
                fieldXLocation = scanner.nextInt();
                filedYLocation = scanner.nextInt();
                cabbageField[filedYLocation][fieldXLocation] = 1;
            }

            //count 초기화
            count = 0;

            for (int yLocation = 0; yLocation < fieldVertical; yLocation++) {
                for (int xLocation = 0; xLocation < fieldWidth; xLocation++) {
                    if (cabbageField[yLocation][xLocation] == 1 && !checkField[yLocation][xLocation]) {
                        bfs(yLocation, xLocation);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void bfs(int yLocation, int xLocation) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{yLocation, xLocation});

        checkField[yLocation][xLocation] = true;

        int[] xDirection = {0, 0, -1, 1};
        int[] yDirection = {-1, 1, 0, 0};

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[1] + xDirection[i];
                int y = poll[0] + yDirection[i];

                //배추밭을 벗어날 때
                if (x < 0 || x >= fieldWidth || y < 0 || y >= fieldVertical) {
                    continue;
                }

                if (cabbageField[y][x] == 1 && !checkField[y][x]) {
                    queue.offer(new int[]{y, x});
                    checkField[y][x] = true;
                }
            }
        }
    }
}
