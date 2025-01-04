import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

public class Main {
    static int n, m;
    static List<Node>[] graph;
    static int[] dist;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1];
        graph = new ArrayList[n + 1];

        // graph 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 거리 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        // graph 연결
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, v));
        }

        // 출발지 도착지
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(prim());
    }

    static int prim(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(visited[node.next]){
                continue;
            }

            visited[node.next] = true;

            for(Node n : graph[node.next]){
                if(!visited[n.next] && dist[n.next] > dist[node.next] + n.value){
                    dist[n.next] = dist[node.next] + n.value;
                    pq.add(new Node(n.next, dist[n.next]));
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node>{
        int next;
        int value;

        public Node(int next, int value) {
            this.next = next;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}