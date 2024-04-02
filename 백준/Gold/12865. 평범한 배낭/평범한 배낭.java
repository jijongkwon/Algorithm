import java.util.*;
import java.io.*;

/**
 * 	제한
 * 	n <= 100
 * 	k <= 100,000
 * 	w <= 100,000
 * 	v <= 1,000
 * 	풀이
 *	return 물건 가치의 최대값 
 */
public class Main {
	static int n, k;
	static int[][] dp;
	static int[] weights;
	static int[] values;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[n + 1][k + 1];
		weights = new int[n + 1];
		values = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			weights[i] = weight;
			values[i] = value;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= k; j++) {
				dp[i][j] = dp[i - 1][j];
				// 무게가 남으면
				if(j - weights[i] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i]] + values[i]);
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}