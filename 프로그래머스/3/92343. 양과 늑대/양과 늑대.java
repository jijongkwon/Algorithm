import java.util.*;

/*
10초

Info <= 17


.. 완탐?
*/
class Solution {
    boolean[] visited;
    int maxSheepCnt = 0;
    
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        dfs(0,0,0,info,edges);
        
        return maxSheepCnt;
    }
    
    void dfs(int idx, int sheepCnt, int wolfCnt, int[]info, int[][]edges){
        visited[idx] = true;
        
        // 양
        if(info[idx] == 0){
            sheepCnt++;
            maxSheepCnt = Math.max(maxSheepCnt, sheepCnt);
        }
        // 늑대
        else{
            wolfCnt++;
        }
        
        // 종료조건
        if(sheepCnt <= wolfCnt){
            visited[idx] = false;
            return;
        }
        
        // 탐색
        for(int[] edge : edges){
            if(visited[edge[0]] && !visited[edge[1]]){
                dfs(edge[1], sheepCnt, wolfCnt, info, edges);
            }
        }
        
        visited[idx] = false;
    }
}