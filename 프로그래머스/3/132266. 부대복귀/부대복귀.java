import java.util.*;

/*
    100,000
    500,000
    500
    
    500 bfs -> 말안됨
    -> 다익스트라
    다만 가중치 1이기때문에, dist[] 만 기록하면 됨 (클래스 생성x)
    
*/
class Solution {
    int [] dist;
    List<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        graph = new ArrayList[n + 1];
        
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();            
        }
        
        for(int i = 0; i < roads.length; i++){
            int start = roads[i][0];
            int end = roads[i][1];
            
            graph[start].add(end);
            graph[end].add(start);
        }
        
        // 기록
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        dist[destination] = 0;
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for(int next : graph[cur]){
                if(dist[next] != -1){
                    continue;                    
                }
                
                dist[next] = dist[cur] + 1;
                queue.add(next);
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            int node = sources[i];
            
            answer[i] = dist[node];
        }
        
        return answer;
    }
}