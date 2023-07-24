package programmers.level2.미로탈출;

import java.util.*;

/*
    return: 최소시간
*/
class Solution {
    static int[][] miro;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int solution(String[] maps) {

        miro = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];

        for(int x = 0; x < maps.length; x++){
            String map = maps[x];
            for(int y = 0; y < map.length(); y++){
                if(map.charAt(y) == 'O'){
                    miro[x][y] = 1;
                    continue;
                }

                if(map.charAt(y) == 'X'){
                    miro[x][y] = 0;
                    continue;
                }

                if(map.charAt(y) == 'S'){
                    miro[x][y] = 3;
                    continue;
                }

                if(map.charAt(y) == 'L'){
                    miro[x][y] = 5;
                    continue;
                }

                if(map.charAt(y) == 'E'){
                    miro[x][y] = 10;
                    continue;
                }
            }
        }

        // for(int[] m : miro){
        //     System.out.println(Arrays.toString(m));
        // }

        // 시작 위치 찾기
        int startX = 0;
        int startY = 0;

        for(int i = 0 ; i < miro.length; i++){
            for(int j = 0; j < miro[0].length; j++){
                if(miro[i][j] == 3){
                    startX = i;
                    startY = j;
                }
            }
        }

        // 레버 위치 찾ㄱ기
        int levorX = 0;
        int levorY = 0;

        for(int i = 0 ; i < miro.length; i++){
            for(int j = 0; j < miro[0].length; j++){
                if(miro[i][j] == 5){
                    levorX = i;
                    levorY = j;
                }
            }
        }

        int levor = bfs(startX, startY);
        visited = new boolean[maps.length][maps[0].length()];
        int exit = bfs(levorX, levorY);

        if(levor == -1 || exit == -1){
            return -1;
        }

        return levor + exit;
    }

    static int bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y,0));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Point point = queue.poll();

            if(miro[point.x][point.y] == 5 && miro[x][y] == 3){
                return point.point;
            }

            if(miro[point.x][point.y] == 10 && miro[x][y] == 5){
                return point.point;
            }

            for(int i = 0; i < 4; i++){
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= miro.length || ny >= miro[0].length){
                    continue;
                }

                if(!visited[nx][ny] && (miro[nx][ny] == 1 || miro[nx][ny] == 10  || miro[nx][ny] == 5 || miro[nx][ny] == 3)){
                    queue.add(new Point(nx,ny, point.point + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    static class Point{
        int x;
        int y;
        int point;

        Point(int x, int y, int point){
            this.x = x;
            this.y = y;
            this.point = point;
        }
    }
}