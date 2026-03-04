import java.util.*;

/*
    모든 섬이 서로 연결
    
    MST
*/
class Solution {
    List<Node>[] graph;
    
    public int solution(int n, int[][] costs) {
        // graph 연결
        graph = new ArrayList[n];        
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < costs.length; i++){
            int from = costs[i][0];
            int to = costs[i][1];
            int weight = costs[i][2];
            
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }
        
        // 가중치 비교하며 연결하기
        int answer = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight;
        });
        
        pq.add(new Node(0,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(visited[cur.to]){
                continue;
            }
            
            visited[cur.to] = true;
            answer += cur.weight;
            
            for(Node next : graph[cur.to]){
                if(!visited[next.to]){
                    pq.add(next);
                }    
            }
        }
        
        return answer;
    }
    
    class Node{
        int to;
        int weight;
        
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
}