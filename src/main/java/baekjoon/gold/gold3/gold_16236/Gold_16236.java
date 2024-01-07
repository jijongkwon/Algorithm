package baekjoon.gold.gold3.gold_16236;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * solution:
 * 1. 크기가 작은 상어만 먹을 수 있음, 같다면 지나갈 수는 있음
 * 2. 만약 지나갈 수 없다면, 엄마 상어에게 도움을 요청
 * 3. 먹을 수 있는 물고기라면 먹는다, 만약 많이 있다면 거리가 가장 가까운 물고기 먹음
 * 4. 거리는 지나야하는 칸의 개수의 최솟값
 * 5. 거리가 가까운 물고기가 많다면 가장 위의 있는 물고기, 그러한 물고기가 여러마리라면 가장 왼족에 있는 물고기를 먹는다.
 * 6. 이동은 1초, 먹는 시간 X
 * 7. 먹으면 빈칸, 자신의 크기와 같은 수 의 물고기를 먹을 때 1증가
 *
 * bigO: 2^n 이하
 *
 * return: 몇 초 동안 엄마 상어게게 도움을 요청하지 않고 물고기를 먹을 수 있는 시간
 */
public class Gold_16236 {
    static int n;
    static int sharkSize = 2;
    static int eatingFish = 0;
    static int count = 0;
    static int sharkX, sharkY;
    static int minX, minY, minDist;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < n; j++){
                map[i][j] = sc.nextInt();

                if(map[i][j] == 9){
                    sharkX = i;
                    sharkY = j;

                    map[i][j] = 0;
                }
            }
        }

        while(true){
            dist = new int[n][n];
            minDist = Integer.MAX_VALUE;
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;

            moveShark(sharkX, sharkY);

            if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE){
                eatingFish++;
                map[minX][minY] = 0;
                sharkX = minX;
                sharkY = minY;
                count += dist[minX][minY];

                if(eatingFish == sharkSize){
                    sharkSize++;
                    eatingFish = 0;
                }
                continue;
            }

            break;
        }

        System.out.println(count);
    }

    static void moveShark(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));

        while(!queue.isEmpty()){
            Point point = queue.poll();

            for(int i = 0 ; i < 4; i++){
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                // map 범위 안 체크
                if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                    continue;
                }

                //  지나갈 수 있는 지 확인
                if(map[nx][ny] > sharkSize){
                    continue;
                }

                // 방문확인
                if(dist[nx][ny] != 0){
                    continue;
                }

                // 거리 대입
                dist[nx][ny] = dist[point.x][point.y] + 1;

                // 먹을 수 있는 지 확인
                if(map[nx][ny] != 0 && map[nx][ny] < sharkSize){
                    // 가까운 거리 확인
                    if(minDist > dist[nx][ny]){
                        minDist = dist[nx][ny];
                        minX = nx;
                        minY = ny;
                        continue;
                    }

                    // 가까운 거리 여러마리
                    if(minDist == dist[nx][ny]){

                        // 같은 높이
                        if(minX == nx){
                            if(minY > ny){
                                minY = ny;
                            }

                            continue;
                        }

                        // 더 위에 있는 물고기 선택
                        if(minX > nx){
                            minX = nx;
                            minY = ny;
                        }
                    }
                }

                queue.add(new Point(nx,ny));
            }
        }
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
