import java.util.*;
import java.io.*;

/**
 * 양방향 연결 
 * 연결 여부 확인
 * 
 * 그래프 탐색
 * return 1번 사람을 통해 소문을 알게 되는 사람의 수
 */
public class Main {
	static int v, e;
	static List<Integer>[] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		v = Integer.parseInt(br.readLine());
		
		// 초기화
		graph = new ArrayList[v + 1];
		for(int i = 1; i <= v; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 그래프 연결
		e = Integer.parseInt(br.readLine());
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			graph[to].add(from);
		}
		
		// 탐색설정
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[v + 1];
		int count = -1;
		queue.add(1);
		
		// 탐색시작
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(visited[cur]) {
				continue;
			}
			
			visited[cur] = true;
			count++;
			
			for(int next : graph[cur]) {
				if(!visited[next]) {
					queue.add(next);
				}
			}
		}
		
		System.out.println(count);
	}
}