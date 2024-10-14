import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static List<Node> stars = new ArrayList<>();
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // 별 입력
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars.add(new Node(i, x, y));
            graph[i] = new ArrayList<>();
        }

        // graph 연결
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(stars.get(i).x - stars.get(j).x, 2) + Math.pow(stars.get(i).y - stars.get(j).y, 2));
                graph[i].add(new Edge(j, dist));
                graph[j].add(new Edge(i, dist));
            }
        }

        // prim
        double answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        pq.add(new Edge(1,0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if(visited[edge.to]){
                continue;
            }

            visited[edge.to] = true;
            answer += edge.cost;

            for (int i = 0; i < graph[edge.to].size(); i++) {
                Edge next = graph[edge.to].get(i);
                if (!visited[next.to]) {
                    pq.add(new Edge(next.to, next.cost));
                }
            }
        }

        System.out.println(answer);
    }

    static class Node{
        int num;
        double x;
        double y;

        public Node(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int to;
        double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}