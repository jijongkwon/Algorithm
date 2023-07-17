package programmers.level2.당구연습;

import java.util.*;

/*
    시간복잡도: 여유

    문제해결
    1. 직선만들고 길이 비교

    return: 공이 굴러간 거리의 최솟값
*/
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int min = Integer.MAX_VALUE;
            int length = 0;

            // 좌
            if (!(startY == targetY && startX >= targetX)) {
                length = getDistance(startX, startY, targetX * (-1), targetY);
                min = Math.min(min, length);
            }

            // 우
            if (!(startY == targetY && startX <= targetX)) {
                length = getDistance(startX, startY, m + (m - targetX), targetY);
                min = Math.min(min, length);
            }

            // 상
            if (!(startX == targetX && startY <= targetY)) {
                length = getDistance(startX, startY, targetX, n + (n - targetY));
                min = Math.min(min, length);
            }

            // 하
            if (!(startX == targetX && startY >= targetY)) {
                length = getDistance(startX, startY, targetX, targetY * (-1));
                min = Math.min(min, length);
            }

            answer[i] = min;
        }

        return answer;
    }

    public int getDistance(int sx, int sy, int tx, int ty) {
        return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
    }
}
