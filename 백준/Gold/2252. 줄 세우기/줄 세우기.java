import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 20.
 * @link https://www.acmicpc.net/problem/2252
 * @keyword_solution  
 * 1. 
 * @input 
 *  N(1 ≤ N ≤ 32,000)
 *  M(1 ≤ M ≤ 100,000)
 *  n log 이하
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n, m;
	static List<Integer>[] graph;
	static int[] degree;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n + 1];
		degree = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			
			graph[parent].add(child);
			degree[child]++;
		}
		
		sortGraph();
		
		System.out.println(sb.toString());
	}
	
	static void sortGraph() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= n ; i++) {
			if(degree[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int number = queue.poll();
			sb.append(number + " ");
			
			for(int idx : graph[number]) {
				if(--degree[idx] == 0) {
					queue.add(idx);
				}
			}
		}
	}
}