package baekjoon.silver.silver1.silver_7562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Silver_7562 {
    static boolean visited[][];
    static int chessSize;
    static int goalX;
    static int goalY;

    static Point chessPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스 입력
        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            // 체스판 크기 입력
            chessSize = Integer.parseInt(br.readLine());

            // 체스판 생성
            visited = new boolean[chessSize][chessSize];

            // 체스 위치 설정
            st = new StringTokenizer(br.readLine());
            int nightX = Integer.parseInt(st.nextToken());
            int nightY = Integer.parseInt(st.nextToken());
            chessPoint = new Point(nightX,nightY,0);

            // 목적지 설정
            st = new StringTokenizer(br.readLine());
            goalX = Integer.parseInt(st.nextToken());
            goalY = Integer.parseInt(st.nextToken());

            bfs(chessPoint);
        }
    }

    public static void bfs(Point chessPoint){
        Queue<Point> queue = new LinkedList<>();
        queue.add(chessPoint);
        visited[chessPoint.x][chessPoint.y] = true;

        int xDirection [] = {1,1,-1,-1,2,2,-2,-2};
        int yDirection [] = {2,-2,2,-2,1,-1,1,-1};


        while(!queue.isEmpty()){
            Point point = queue.poll();
            // 목적지 도착
            if(goalX == point.x && goalY == point.y){
                System.out.println(point.point);
                return;
            }

            for(int i = 0; i < 8; i++){
                int x = point.x + xDirection[i];
                int y = point.y + yDirection[i];
                if(x >= 0 && x < chessSize && y >= 0 && y < chessSize && !visited[x][y]){
                    queue.add(new Point(x,y, point.point + 1));
                    visited[x][y] = true;
                }
            }
        }
    }
    public static class Point{
        int x;
        int y;
        int point;

        public Point(int x, int y, int point){
            this.x = x;
            this.y = y;
            this.point = point;
        }
    }
}
