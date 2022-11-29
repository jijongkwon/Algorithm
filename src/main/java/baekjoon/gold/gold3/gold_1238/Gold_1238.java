package baekjoon.gold.gold3.gold_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold_1238 {
    static int stoi(String s) { return Integer.parseInt(s);}
    static final int INF = 987654321;
    static int n, m, x;
    static int[] dist, revDist;
    static List<List<Node>> list, revList;

    //comparable 정렬기능 -> 우선 순위 큐를 사용하려면 comparable interface 사용
    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input data
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        x = stoi(st.nextToken());

        // 인스턴스 생성
        dist = new int[n+1];
        revDist = new int[n+1];
        list = new ArrayList<List<Node>>();
        revList = new ArrayList<List<Node>>();

        //초기화
        init();

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = stoi(st.nextToken());
            int v2 = stoi(st.nextToken());
            int dist = stoi(st.nextToken());

            list.get(v1).add(new Node(v2, dist));
            revList.get(v2).add(new Node(v1, dist));
        }

        // 최솟값 구하기
        dijkstra(list,dist,x);
        dijkstra(revList,revDist,x);

        // 비교
        int max = -1;
        for(int i = 1; i <= n; i++){
            if(max < dist[i] + revDist[i]){
                max = dist[i] + revDist[i];
            }
        }

        //출력
        System.out.println(max);
    }

    static void init() {
        Arrays.fill(dist, INF);
        Arrays.fill(revDist, INF);

        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<Node>());
            revList.add(new ArrayList<Node>());
        }
    }

    static void dijkstra(List<List<Node>> list, int distance[], int start){
        boolean visited[] = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            int idx = pq.poll().index;

            // 방문 체크
            if(visited[idx]){
                continue;
            }
            visited[idx] = true;

            // 최소 거리 구하기
            for(Node node : list.get(idx)){
                if(distance[node.index] > distance[idx] + node.distance){
                    distance[node.index] = distance[idx] + node.distance;
                    pq.add(new Node(node.index,distance[node.index]));
                }
            }
        }
    }
}
