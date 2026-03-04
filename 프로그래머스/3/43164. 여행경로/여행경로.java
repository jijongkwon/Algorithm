import java.util.*;

/*
    항상 ICN에서 출발
    주어진 항공권 모두 사용해야함
    만약 경로가 2개 이상이면, 알파벳 순서가 앞서는 경로 return
    
    진짜 단순 bfs/dfs
*/
class Solution {
    boolean[] visited;
    List<String> result;
    int n;
    
    public String[] solution(String[][] tickets) {
        n = tickets.length;
        visited = new boolean[n];
        result = new ArrayList<>();
        
        
        Arrays.sort(tickets, (o1, o2) -> {
            if(o1[0].equals(o2[0])){
                return o1[1].compareTo(o2[1]);   
            }
            
            return o1[0].compareTo(o2[0]);
        });
        
        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", path, tickets);
        
        return result.toArray(new String[0]);
    }
    
    void dfs(String current, List<String> path, String[][] tickets) {
        // 모든 티켓 사용 완료
        if(path.size() == n + 1) {
            result = new ArrayList<>(path);
            return;
        }
        
        for(int i = 0; i < n; i++) {
            if(!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                path.add(tickets[i][1]);
                
                dfs(tickets[i][1], path, tickets);         
                
                if(!result.isEmpty()){
                    return;   
                }
                
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}