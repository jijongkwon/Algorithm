package baekjoon.silver.silver1.silver_1697;

import java.io.*;
import java.util.*;

/*
* 시간복잡도 : nlog 이하
*
*
* */
public class Silver_1697 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 출발지 n 도착치 k
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 방문 초기화
        boolean[] visited = new boolean[100001];
        visited[n] = true;

        // 위치 찾기
        int min = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{n, 0});

        while(!queue.isEmpty()){
            int[] loc = queue.poll();

            int location = loc[0];
            int move = loc[1];

            visited[location] = true;

            if(move > min){
                continue;
            }

            if(location == k){
                min = move;
                continue;
            }

            int minus = location - 1;
            int plus = location + 1;
            int multi = location * 2;

            if(minus > -1 && !visited[minus]){
                queue.add(new int[]{minus, move + 1});
            }

            if(plus < 100001 &&!visited[plus]){
                queue.add(new int[]{plus, move + 1});
            }

            if(multi < 100001 && !visited[multi]){
                queue.add(new int[]{multi, move + 1});
            }
        }

        System.out.println(min);
    }
}
