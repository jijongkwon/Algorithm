import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 20.
 * @link https://www.acmicpc.net/problem/1197
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static class pq{
		int current;
		int next;
		int value;
		
		public pq(int current, int next, int value) {
			this.current = current;
			this.next = next;
			this.value = value;
		}
	}
	
	static int V, E;
	static PriorityQueue<pq> pq;
	static int[] parent;
	static int answer = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		
		pq = new PriorityQueue<>((o1, o2) -> {
			return o1.value - o2.value;
		});
		
		parent = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < E; i++) {
			int cur = sc.nextInt();
			int next = sc.nextInt();
			int value = sc.nextInt();
			
			pq.add(new pq(cur, next, value));
		}
		
		while(!pq.isEmpty()) {
			pq n = pq.poll();
			
			if(find(n.current) == find(n.next)) {
				continue;
			}
			
			union(n.current, n.next);
			answer += n.value;
		}
		
		System.out.println(answer);
	}
	
	static void union(int x, int y){
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX == rootY) {
			return;
		}
		
		if(rootX > rootY) {
			parent[rootX] = rootY;
		}
		else {
			parent[rootY] = rootX;
		}
		
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}