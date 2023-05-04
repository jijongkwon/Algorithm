package programmers.level2.게임맵최단거리;

import java.util.*;

class Solution {
    /*
        길 찾기 거리 최솟값 = bfs
    */
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        // 상, 하, 좌, 우 이동 방향을 저장한 배열
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});
        maps[0][0] = 0; // 방문 처리

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            // 목적지에 도착한 경우 최소 이동 거리 반환
            if (x == n-1 && y == m-1) {
                return cnt;
            }


            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어났을때
                if(nx < 0 || ny < 0 || nx >= n || ny >= m){
                    continue;
                }

                if(maps[nx][ny] == 1){
                    queue.offer(new int[]{nx,ny, cnt + 1});
                    maps[nx][ny] = 0;
                }
            }
        }

        return -1;
    }
}