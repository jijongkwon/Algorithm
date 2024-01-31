import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 지종권
 * @date 2024. 1. 31.
 * @link https://www.acmicpc.net/problem/15650
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int[] nums;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		nums = new int[n];

		for(int i = 1; i <= n ; i++) {
			nums[i - 1] = i;
		}
		
		req(0,new int[m], 0);
	}
	
	private static void req(int depth, int[] choosed, int start) {
		// base
		if(depth == choosed.length) {
			for(int i = 0; i < choosed.length; i++) {
				System.out.print(choosed[i] + " ");
			}
			System.out.println();
			return;
		}
		
		// inductive
		for(int i = start; i < nums.length; i++) {
			choosed[depth] = nums[i];
			req(depth + 1, choosed, i + 1);
		}
	}

}
