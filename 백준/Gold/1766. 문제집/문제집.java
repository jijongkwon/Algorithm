import java.util.*;
import java.io.*;

/**
 *	문제의 난이도는 순서대로 출제 
 *	
 *	조건
 *	1. n개의 문제 모두 풀어야 함
 *	2. 좋은 문제는 먼저 풀어야 함
 *	3. 가능하면 쉬운 문제부터 풀어야 함
 *
 *	1 <= n <= 32,000
 *	1 <= m <= 100,000
 */
public class Main {
	static int n, m;
	static PriorityQueue<Integer> pq;
	static List<Integer>[] nodes;
	static int[] degrees;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// set
		nodes = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<>();
		}
		degrees = new int[n + 1];
		pq = new PriorityQueue<>();
		
		// 차수 정하기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nodes[a].add(b);
			degrees[b]++;
		}
		
		for(int i = 1; i <= n; i++) {
			if(degrees[i] == 0) {
				pq.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			int node = pq.poll();
			sb.append(node).append(" ");
			
			for(int degree : nodes[node]) {
				degrees[degree]--;
			}
			
			for(int degree : nodes[node]) {
				if(degrees[degree] == 0) {
					pq.add(degree);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}