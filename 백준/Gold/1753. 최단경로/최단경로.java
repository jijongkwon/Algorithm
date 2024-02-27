import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 27.
 * @link https://www.acmicpc.net/problem/1753
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int V, E;
	static int start;
	static List<Edge>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// input V, E start
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
		// set graph
		graph = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// link graph
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, weight));
		}
		
		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
				continue;
			}
			sb.append(dist[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			for(Edge next : graph[edge.to]) {
				if(dist[next.to] > edge.weight + next.weight) {
					dist[next.to] = edge.weight + next.weight;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}