import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 21.
 * @link https://www.acmicpc.net/problem/1922
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static class Node{
		int cur;
		int next;
		int value;

		public Node(int cur, int next, int value) {
			this.cur = cur;
			this.next = next;
			this.value = value;
		}
	}
	
	static int n, m;
	static PriorityQueue<Node> pq;
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		
		parent = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		pq = new PriorityQueue<>((o1, o2) -> {
			return o1.value - o2.value;
		});
		
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(cur,next,value));
		}
		
		int sum = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(find(node.cur) == find(node.next)) {
				continue;
			}
			
			union(node.cur, node.next);
			sum += node.value;
		}
		
		System.out.println(sum);
		
	}
	
	static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX == rootY) {
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