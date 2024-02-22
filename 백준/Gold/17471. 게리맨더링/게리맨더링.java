import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 22.
 * @link https://www.acmicpc.net/problem/17471
 * @keyword_solution  
 * 1. 구역을 두 개의 선거구로 나눔
 * 2. 각 구역은 두 선거구 중 하나에 포함
 * 3. 선거구는 적어도 하나의 구역 포함
 * 4. 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 함
 * 5. a - > b 이게 연결되어 있다는 뜻
 * 6. 중간에 통하는 인접구역 0개 이상, 모두 같은 선거구에 포함
 * 
 * 2개의 구역으로 나눈다 ? -> 부분집합
 * 그 구역이 연결되어 있는지 확인
 * 둘 다 연결되어 있으면 구역 인구의 차이값 구함
 * 그리고 최솟값 구함
 * 
 * return 인구 차이의 최솟값
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	
	static int n;
	static int[] peopleInCity;
	static List<Integer>[] graph;
	static int diff = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// input area number
		n = Integer.parseInt(br.readLine());
		
		// input people number in area
		peopleInCity = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			peopleInCity[i] = Integer.parseInt(st.nextToken());
		}
		
		// input link between area
		graph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int linkNumber = Integer.parseInt(st.nextToken());
			for(int j = 0; j < linkNumber; j++) {
				int next = Integer.parseInt(st.nextToken());
				
				graph[i].add(next);
			}
		}

		// output
		makePowerSet(1, new boolean[n + 1]);
		
		if(diff == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(diff);
	}
	
	// make power set
	static void makePowerSet(int depth, boolean[] choosed) {
		// base
		if(depth == choosed.length) {
			List<Integer> area1 = new LinkedList<>();
			List<Integer> area2 = new LinkedList<>();
			
			// divide area
			for(int i = 1; i < choosed.length; i++) {
				if(choosed[i]) {
					area1.add(i);
					continue;
				}
				
				area2.add(i);
			}
			
			// 0 check
			if(area1.size() == 0 || area2.size() == 0) {
				return;
			}
			
			// check correct
			if(checkLinked(area1) && checkLinked(area2)) {
				diff = Math.min(diff, calDiff(area1, area2));
			}
			
			return;
		}
		
		// inductive
		choosed[depth] = true;
		makePowerSet(depth + 1, choosed);
		choosed[depth] = false;
		makePowerSet(depth + 1, choosed);
	}	
	
	// check linked area
	static boolean checkLinked(List<Integer> area) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		
		int start = area.get(0);
		queue.add(start);
		
		// visit area
		while(!queue.isEmpty()){
			int n = queue.poll();
			visited[n] = true;
			
			for(int next : graph[n]) {
				if(!visited[next] && area.contains(next)) {
					queue.add(next);
				}
			}
		}
		
		// check visited all area
		for(int i = 0; i < area.size(); i++) {
			if(!visited[area.get(i)]) {
				return false;
			}
		}
		
		return true;
	}
	
	// calculate different between people of area1, people of area2
	static int calDiff(List<Integer> area1, List<Integer> area2) {
		int sum1 = 0;
		for (int area : area1) {
			sum1 += peopleInCity[area];
		}
		
		int sum2 = 0;
		for (int area : area2) {
			sum2 += peopleInCity[area];
		}
		
		return Math.abs(sum1 - sum2);
	}
}