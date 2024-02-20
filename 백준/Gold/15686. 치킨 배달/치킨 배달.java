import java.util.*;
/**
 * @author 지종권
 * @date 2024. 2. 20.
 * @link https://www.acmicpc.net/problem/15686
 * @keyword_solution  
 * 1. 치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리
 * 2. 도시의 치킨 거리 = 모든 치킨 거리의 합
 * 3. m개의 치킨 집을 고른 후, 도시의 치킨 거리가 가장 작게 나오는지 구하는 것
 * 
 * 조합으로 풀기
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n, m;
	static int cityDistance = Integer.MAX_VALUE;
	static List<int[]> chickens = new ArrayList<>();
	static List<int[]> homes = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < n; j++) {
				int number = sc.nextInt();
				
				if(number == 2) {
					chickens.add(new int[] {i,j});
				}
				
				if(number == 1) {
					homes.add(new int[] {i,j});
				}
			}
		}
		
		dfs(0, new boolean[chickens.size()], 0);
		
		System.out.println(cityDistance);
	}
	
	static void dfs(int depth, boolean[] choosed, int chooseNum) {
		// base
		if(depth == choosed.length) {
			
			if(chooseNum != m) {
				return;
			}
			int sum = 0;
			
			for(int i = 0; i < homes.size(); i++) {
				int[] home = homes.get(i);
				int minD = Integer.MAX_VALUE;
				for(int j = 0; j < chickens.size(); j++) {
					if(choosed[j]) {
						int[] chicken = chickens.get(j);
						int d = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
						minD = Math.min(minD, d);
					}
				}
				
				sum += minD;
			}
			
			cityDistance = Math.min(cityDistance, sum);
			return;
		}
		
		// inductive
		choosed[depth] = true;
		dfs(depth + 1, choosed, chooseNum + 1);
		choosed[depth] = false;
		dfs(depth + 1, choosed, chooseNum);
	}
}