package baekjoon.silver.silver2.silver_11724;

import java.io.*;
import java.util.*;

public class Silver_11724 {
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // n, m 입력
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        // 그래프 연결
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int node = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            graph.get(node).add(next);
            graph.get(next).add(node);
        }

        // 연결 요소 구하기
        int count = 0;
        visited = new boolean[n + 1];

        for(int i = 1; i <= n; i++){
            if(visited[i]){
                continue;
            }
            bfs(i, graph);
            count++;
        }

        System.out.println(count);
    }

    static void bfs(int node, List<List<Integer>> graph){
        visited[node] = true;

        List<Integer> next = graph.get(node);

        for(int i : next){
            if(!visited[i]){
                bfs(i, graph);
            }
        }
    }
}
