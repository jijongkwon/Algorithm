package programmers.level2.피로도;

import java.util.*;

class Solution {
    // 경우의 수에 따른 횟수 리스트
    static Set<Integer> countList = new HashSet<>();

    // 방문 여부
    static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        visited = new boolean[dungeons.length];
        visitDungeons(k, dungeons);

        for(int i : countList){
            if(answer < i){
                answer = i;
            }
        }

        return answer;
    }

    static public void visitDungeons(int k, int[][] dungeons){
        for(int i = 0; i < dungeons.length; i++){
            if(k >= dungeons[i][0] && !visited[i]){
                visited[i] = true;
                visitDungeons(k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }

        checkVisited(visited);
    }

    static public void checkVisited(boolean[] check){
        int count = 0;
        for(boolean flag : check){
            if(flag){
                count++;
            }
        }

        countList.add(count);
    }
}
