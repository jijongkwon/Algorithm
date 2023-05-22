package softeer.level3.출퇴근길;

import java.util.*;
import java.io.*;

/*
    자료구조 : 단방향 그래프, dfs
    시간복잡도 : nlog 이하
*/
public class Solution
{
    static List<List<Integer>> graph;
    static List<List<Integer>> reverseGraph;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        // 정점 n, 간선 m
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 정점 초기화
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // 간선의 정보 저장
        for(int i = 0; i < m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            graph.get(x).add(y);
            reverseGraph.get(y).add(x);
        }

        // 출발지 S, 목적지 T 정점
        int S = sc.nextInt();
        int T = sc.nextInt();

        // 모든 노드의 경우 수 탐방 from 정방향, to 역방향
        int[] fromS = new int[n + 1];
        fromS[T] = 1;
        dfs(S, graph, fromS);

        int[] fromT = new int[n + 1];
        fromT[S] = 1;
        dfs(T, graph, fromT);

        int[] toS = new int[n + 1];
        dfs(S, reverseGraph, toS);

        int[] toT = new int[n + 1];
        dfs(T, reverseGraph, toT);

        // 공통 노드 뽑아내기
        int count = 0;

        for(int i = 1; i < n + 1; i++){
            if(fromT[i] == 1 && fromS[i] == 1 && toS[i] == 1 && toT[i] == 1){
                count++;
            }
        }

        System.out.println(count - 2);
    }

    // 노드 탐방
    public static void dfs(int x, List<List<Integer>> graph, int[] visited){
        if(visited[x] == 1){
            return;
        }

        visited[x] = 1;

        for(int i : graph.get(x)){
            dfs(i,graph,visited);
        }

        return;
    }
}

