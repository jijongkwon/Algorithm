/*
  1 ~ n 까지의 등대가 존재
  등대 사이를 오가는 뱃길이 n-1개 존재
  
  켜야하는 등대의 최소개수
  
  풀이
  모든 노드들은 연결되어 있음
  
  dfs 루트 노드부터 시작
  켜저있거나 꺼저있거나 2가지 경우
  
  자식 노드를 탐색하면서 개수를 세면 최솟값을 구하면 됨
  
*/
import java.util.*;

class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    int[][] dp;
    
    public int solution(int n, int[][] lighthouse) {
        
        visited = new boolean[n + 1];
        
        // graph 생성
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        // graph 연결
        for(int i = 0 ; i < n - 1; i++){
            int node1 = lighthouse[i][0];
            int node2 = lighthouse[i][1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        
        dfs(1);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void dfs(int nodeNum){
        visited[nodeNum] = true;
        // 0은 꺼짐, 1은 켜짐
        dp[nodeNum][0] = 0;
        dp[nodeNum][1] = 1;
        
        for(int i = 0; i < graph[nodeNum].size(); i++){
            int childNodeNum = graph[nodeNum].get(i);
            
            // 이미 방문했을 때 생략
            if(visited[childNodeNum]){
                continue;
            }
            
            dfs(childNodeNum);
            
            // 부모의 등대가 꺼져있으면 자식은 무조건 켜져야있어야함
            dp[nodeNum][0] += dp[childNodeNum][1];
            
            // 부모의 등대가 켜져있으면, 자식은 켜져있든 꺼져있든 상관없음 -> 해당 부분에서 최소값 더 해주면 됨
            dp[nodeNum][1] += Math.min(dp[childNodeNum][0], dp[childNodeNum][1]);
        }
    }
}