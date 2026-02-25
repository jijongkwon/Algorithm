import java.util.*;

/*
    return 간선의 개수가 가장 많은 노드의 개수
    
    풀이
    1. 그래프 연결
    2. 방문하면서 개수 업그레이드 (map)
    
    끝 ..?
*/
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer>[] graph;
    public int solution(int n, int[][] edge) {
        // 초기 상태
        map.put(0, n);
        graph = new ArrayList[n + 1];
        
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        // graph 연결
        for(int i = 0; i < edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 방문
        boolean[] visited = new boolean[n + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1,0));
        visited[1] = true;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            for(int next : graph[node.number]){
                if(visited[next]){
                    continue;
                }
                
                visited[next] = true;
                queue.add(new Node(next, node.count + 1));
                map.put(node.count + 1, map.getOrDefault(node.count + 1, 0) + 1);
            }
        }
        
        return map.get(map.size() - 1);
    }
    
    class Node{
        int number;
        int count;
        
        Node(int number, int count){
            this.number = number;
            this.count = count;
        }
    }
}