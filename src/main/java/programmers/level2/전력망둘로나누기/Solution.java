package programmers.level2.전력망둘로나누기;

import java.util.*;

class Solution {
    static List<List<Integer>> graph;  // 그래프를 나타내는 인접 리스트
    static boolean[] visited;  // 노드 방문 여부를 저장하는 배열

    public int solution(int n, int[][] wires) {
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            // 해당 엣지를 제거하고, 연결성분을 구한다.
            visited = new boolean[n+1];
            int cnt1 = dfs(wire[0], wire[1]);  // 첫 번째 연결성분 크기
            int cnt2 = n - cnt1;  // 두 번째 연결성분 크기
            answer = Math.min(answer, Math.abs(cnt1 - cnt2));
        }

        return answer;
    }

    // DFS를 이용해 연결성분을 찾는 메서드
    public static int dfs(int node, int except) {
        visited[node] = true;
        int cnt = 1;
        for (int next : graph.get(node)) {
            if (!visited[next] && next != except) {
                cnt += dfs(next, except);
            }
        }
        return cnt;
    }
}