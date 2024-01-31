import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 1. 31.
 * @link https://www.acmicpc.net/problem/11660
 * @keyword_solution 누적합
 * @input 
 * @output   
 * 시간초과 날 수 있음
 * @time_complex  
 * @perf 
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] map = new int[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1 ; j <= n; j++) {
				int number = sc.nextInt();
				map[i][j] = map[i][j - 1] + number;
			}
			
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1 ; j <= n; j++) {
				map[j][i] += map[j - 1][i];
			}
		}
		
		for(int i = 0; i < m; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			int sum = map[x2][y2] - map[x2][y1 - 1] - map[x1 - 1][y2] + map[x1 - 1][y1 - 1];
			System.out.println(sum);
		}
	}

}
