import java.io.*;
import java.util.*;
import org.w3c.dom.Node;


/**
 * 방향성이 없는 그래프 ( 양방향 )
 *
 * 1 -> n 번까지 최단 거리로 이동
 *
 * 조건
 * 1. 임의로 주어진 두 정점은 반드시 통과
 * 2. 방문조건에 재한이 없음 ( 왔다 갔다 가능 )
 *
 * 풀이
 * 프림인데 2개의 점을 지났는지 확인
 *
 */
public class Main {
    static int n, e;
    static int v1, v2;
    static List<Node>[] graph;
    static int intMax = 200_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // start -> v1 -> v2 -> end
        int res1 = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, n);

        // start -> v2 -> v1 -> end
        int res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, n);

        if(res1 >= intMax && res2 >= intMax){
            System.out.println(-1);
            return;
        }

        System.out.println(Math.min(res2, res1));
    }

    static int dijkstra(int start, int end){
        int[] dp = new int[n + 1];
        Arrays.fill(dp, intMax);
        dp[start] = 0;
        boolean [] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();
            visited[cur.next] = true;

            for(Node next : graph[cur.next]){
                if(!visited[next.next] && dp[next.next] > dp[cur.next] + next.value){
                    dp[next.next] = dp[cur.next] + next.value;
                    pq.add(new Node(next.next, + dp[next.next]));
                }
            }
        }

        return dp[end];
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