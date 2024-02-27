import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 27.
 * @link https://www.acmicpc.net/problem/1149
 * @keyword_solution  
 * 점화식 : 
 * 
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// set number of the house
		n = Integer.parseInt(br.readLine());
		
		// set color
		map = new int[n + 1][3];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// set dp
		int[][] dp = new int[n + 1][3];		
		for(int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// red
		dp[1][0] = map[1][0];
		
		// green
		dp[1][1] = map[1][1];
		
		// blue
		dp[1][2] = map[1][2];
		
		// find min
		for(int i = 2; i <= n; i++) {
			// red
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][0];
			
			// green
			dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][0]) + map[i][1];
			
			// blue
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + map[i][2];
		}
		
		int min = Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
		
		System.out.println(min);
	}
}