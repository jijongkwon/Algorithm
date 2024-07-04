import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 맥세권 : 맥도날드와 집 사이의 최단거리가 x이하 집
 * 스세권 : 스타벅스와 집 사이의 최단거리가 y이하 집
 * 두 개를 만족하는 집 중 최단거리의 합이 최소인 집
 *
 * 맥도날드와 스타벅스의 위치가 정점 번호로 주어짐
 *
 * 원하는 집의 최단거리의 합을 출력하는 프로그램
 *
 * 조건
 * 양방향
 * 정점의 개수 3 ~ 10,000
 * 간선의 개수 0 ~ 300,000
 * 가중치 1 ~ 10,000
 *
 * 풀이
 * 맥도날드에서 집까지 다익
 * 스타벅스에서 집까지 다익
 *
 * 조건 확인해서 최소값 구하기
 */
public class Main {
    static List<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점, 간선
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 그래프 연결
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, weight));
            graph[end].add(new Node(start, weight));
        }

        // 맥도날드 다익
        int[] macDist = new int[V+1];
        for(int i = 0; i <= V; i++)
            macDist[i] = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());

        int macCount = Integer.parseInt(st.nextToken());
        int macMax = Integer.parseInt(st.nextToken());
        String[] macNode = br.readLine().split(" ");

        for(int i = 0; i < macCount; i++){
            int mac = Integer.parseInt(macNode[i]);
            macDist[mac] = 0;
            pq.add(new Node(mac, 0));
        }
        Dijkstra(macDist);

        // 스타벅스 다익
        int[] startDist = new int[V+1];
        for(int i = 0; i <= V; i++)
            startDist[i] = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());

        int starCount = Integer.parseInt(st.nextToken());
        int starMax = Integer.parseInt(st.nextToken());
        String[] starNode = br.readLine().split(" ");

        for(int i = 0; i < starCount; i++){
            int star = Integer.parseInt(starNode[i]);
            startDist[star] = 0;
            pq.add(new Node(star, 0));
        }
        Dijkstra(startDist);

        // 조건에 맞는 최소값 구하기
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= V; i++){
            if(macDist[i] > 0 && macDist[i] <= macMax && startDist[i] > 0 && startDist[i] <= starMax){
                ans = Math.min(ans, macDist[i] + startDist[i]);
            }
        }

        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }

        else{
            System.out.println(ans);
        }
    }

    static public void Dijkstra(int[] distance){
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.to]){
                if(distance[next.to] > next.weight + cur.weight){
                    distance[next.to] = next.weight + cur.weight;
                    pq.add(new Node(next.to, distance[next.to]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }
}