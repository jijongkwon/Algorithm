import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 지종권
 * @date 2024-02-25
 * @link https://www.acmicpc.net/problem/21924
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
    static class Edge implements Comparable<Edge>{
        int next;
        long weight;

        public Edge(int next, long weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    static int V, E;
    static long total;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input V, E
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // set graph
        graph = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++){
            graph[i] = new ArrayList<>();
        }

        // link graph
        total = 0;
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to,weight));
            graph[to].add(new Edge(from,weight));

            // total weight;
            total += weight;
        }

        // prim
        System.out.println(prim());
    }

    static long prim(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));

        // find min value
        long sum = 0;
        int checkNode = 0;
        boolean[] visited = new boolean[V + 1];
        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(visited[edge.next]){
                continue;
            }

            visited[edge.next] = true;
            sum += edge.weight;
            checkNode++;

            for(Edge next : graph[edge.next]){
                if(!visited[next.next]){
                    pq.add(next);
                }
            }
        }

        if(checkNode == V){
            return total - sum;
        }

        return -1;
    }
}