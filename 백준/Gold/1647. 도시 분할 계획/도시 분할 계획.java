import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 23.
 * @link https://www.acmicpc.net/problem/1647
 * @keyword_solution  
 * 1. MST (prim)
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
	
	static List<Edge>[] graph;
	static int V, E;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// input V, E
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// set graph
		graph = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// input data for link, and like graph
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// two-way
			graph[from].add(new Edge(to, weight));
			graph[to].add(new Edge(from, weight));
		}
		
		System.out.println(prim());
	}
	
	static int prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		int sum = 0;
		int bigWeight = Integer.MIN_VALUE;
		
		// start
		pq.add(new Edge(1, 0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			// check visited V
			if(visited[edge.to]) {
				continue;
			}
			
			visited[edge.to] = true;
			
			// calculate min
			sum += edge.weight;
			
			bigWeight = Math.max(bigWeight, edge.weight);
			
			// find linked V
			for(Edge e : graph[edge.to]) {
				if(!visited[e.to]) {
					pq.add(new Edge(e.to, e.weight));
				}
			}
		}
		
		return sum - bigWeight;
	}
}