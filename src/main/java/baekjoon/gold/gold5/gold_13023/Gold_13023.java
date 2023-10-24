package baekjoon.gold.gold5.gold_13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solution:
 * 1. 재귀를 통해 주어진 조건에 만족하지 않으면 종료
 * 2. 조건에 만족하는 경우가 존재시 종료
 *
 * bigO : 2^n 이하
 * return : 관계존재 여부
 */
public class Gold_13023 {
    static boolean[] visited;
    static boolean answer = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int peopleNumber = Integer.parseInt(st.nextToken());
        int relationNumber = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < peopleNumber; i ++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < relationNumber; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 0; i < peopleNumber; i++){
            visited = new boolean[peopleNumber];
            dfs(i, graph, 1);

            if(answer){
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    static void dfs(int index, List<List<Integer>> graph, int depth){
        if(depth == 5){
            answer = true;
            return;
        }

        visited[index] = true;
        for(int next : graph.get(index)){
            if(!visited[next]){
                dfs(next,graph,depth + 1);
            }
        }
        visited[index] = false;
    }
}
