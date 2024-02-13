import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 13.
 * @link https://www.acmicpc.net/problem/13905
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	
	static class Edge{
		int from;
		int to;
		int weight;
		
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static int[] parents;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int s = sc.nextInt();
		int e = sc.nextInt();
		
		parents = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		
		pq = new PriorityQueue<>((o1, o2) ->{
			return (o1.weight - o2.weight) * -1;
		});
		
		for(int i = 0; i < m; i++) {
			int h1 = sc.nextInt();
			int h2 = sc.nextInt();
			int k = sc.nextInt();
			
			pq.offer(new Edge(h1, h2, k));
		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			int fa = find(edge.from);
			int fb = find(edge.to);
			
			if(fa != fb) {
				union(fa, fb);
				
				if(find(s) == find(e)) {
					answer = edge.weight;
					break;
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
	
		return parents[x] = find(parents[x]);
	}
	
	static void union(int fa, int fb) {
		if(fa > fb) {
			parents[fb] = fa;
		}
		else {
			parents[fa] = fb;
		}
	}
}