import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N개의 방
 * 각가의 방에는 1개의 컴퓨터
 * 각가의 컴퓨터는 랜선으로 연결됨
 *
 * 직접, 간접 연결 되어 있으면 통신 가능
 *
 * 목표 : N 개의 컴퓨터 연결
 *
 * return 기부할 수 있는 래선의 최대 길이
 *
 * 풀이
 * n-1개 줄 선택
 * MST
 */
public class Main {
    static int n;
    static List<Edge>[] computer;
    static HashMap<Character, Integer> alphaToNum = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 컴퓨터 개수 입력
        n = Integer.parseInt(br.readLine());

        // 소문자
        char alpha = 'a';
        for (int i = 1; i <= 26; i++) {
            alphaToNum.put((char) (alpha + i - 1), i);
        }

        // 대문자
        alpha = 'A';
        for (int i = 27; i <= 52; i++) {
            alphaToNum.put((char) (alpha + i - 27), i);
        }

        // graph 초기화
        computer = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            computer[i] = new ArrayList<>();
        }

        // 0 일때
        alphaToNum.put('0', Integer.MAX_VALUE);

        // graph 연결
        int totalLength = 0;

        for (int i = 0; i < n; i++) {
            char[] lengths = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                int length = alphaToNum.get(lengths[j]);

                if(length != Integer.MAX_VALUE){
                    totalLength += length;
                    computer[i].add(new Edge(j, length));
                    computer[j].add(new Edge(i, length));
                }
            }
        }

        // prim
        int minLength = prim();

        if(minLength == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(totalLength - minLength);
        }
    }

    static public int prim(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        pq.add(new Edge(0,0));
        int sum = 0;
        int count = n;

        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(visited[edge.to]){
                continue;
            }

            visited[edge.to] = true;
            sum += edge.length;
            count--;

            if(count == 0){
                return sum;
            }

            for(Edge next : computer[edge.to]){
                if(!visited[next.to]){
                    pq.add(new Edge(next.to, next.length));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int length;

        public Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.length, o.length);
        }
    }
}