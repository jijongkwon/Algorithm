package programmers.level2.무인도여행;

import java.util.*;

class Solution {
    static String[][] map;
    static boolean[][] visitMap;
    static int height;
    static int width;
    static int value;

    public int[] solution(String[] maps) {
        int[] answer;
        List<Integer> liveMap = new ArrayList<>();

        height = maps.length;
        width = maps[0].length();

        // 2차원 배열로 map 만들기
        map = new String[width][height];
        visitMap = new boolean[width][height];

        for(int i = 0; i < height; i++){
            String mapInfo = maps[i];
            String[] mapInfoList = mapInfo.split("");
            for(int j = 0; j < width; j++){
                map[j][i] =  mapInfoList[j];
            }
        }

        // dfs
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(!visitMap[j][i] && !map[j][i].equals("X")){
                    value = 0;
                    dfs(j,i);
                    liveMap.add(value);
                }
            }
        }

        if(value == 0){
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        // 정렬
        Collections.sort(liveMap);
        answer = new int[liveMap.size()];

        for(int i = 0; i < liveMap.size(); i++){
            answer[i] = liveMap.get(i);
        }

        return answer;
    }

    public void dfs(int x, int y){
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};

        visitMap[x][y] = true;
        value += Integer.parseInt(map[x][y]);

        for(int i = 0; i < 4; i++){
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(nextX < 0 || nextY < 0 || nextX >= width || nextY >= height){
                continue;
            }
            if(visitMap[nextX][nextY] || map[nextX][nextY].equals("X")){
                continue;
            }

            dfs(nextX, nextY);
        }
    }
}