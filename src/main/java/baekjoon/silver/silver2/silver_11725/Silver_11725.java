package baekjoon.silver.silver2.silver_11725;

import java.io.*;
import java.util.*;

public class Silver_11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // n 입력
        int n = Integer.parseInt(br.readLine());

        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        // 그래프 연결
        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            graph.get(node).add(next);
            graph.get(next).add(node);
        }

        // 출력
        int[] parents = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while(!queue.isEmpty()){
            int v = queue.poll();
            visited[v] = true;
            List<Integer> child = graph.get(v);
            for(int i : child){
                if(!visited[i]){
                    parents[i] = v;
                    queue.add(i);
                }
            }
        }

        for(int i = 2; i < parents.length; i++){
            System.out.println(parents[i]);
        }
    }
}
