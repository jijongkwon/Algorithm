import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 1.
 * @link https://www.acmicpc.net/problem/2961
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int[] ssin;
	static int[] ssen;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ssin = new int[n];
		ssen = new int[n];
		
		
		for(int i = 0; i < n; i++) {
			ssin[i] = sc.nextInt();
			ssen[i] = sc.nextInt();
		}
		
		req(0, new boolean[n]);
		
		System.out.println(min);
	}
	
	static void req(int depth, boolean[] choosed) {
		// base
		if(depth == n) {
			if(isEmpty(choosed)) {
				return;
			}
			
			int ssinSum = 1;
			int ssenSum = 0;
			
			for(int i = 0; i < choosed.length; i++) {
				if(choosed[i]) {
					ssinSum *= ssin[i];
					ssenSum += ssen[i];
				}
			}
			
			int diff = Math.abs(ssenSum - ssinSum);
			
			min = Math.min(min, diff);
			return;
		}
		
		// inductive
		choosed[depth] = true;
		req(depth + 1, choosed);
		choosed[depth] = false;
		req(depth + 1, choosed);
	}
	
	static boolean isEmpty(boolean[] choosed) {
		for(int i = 0; i < choosed.length; i++) {
			if(choosed[i]) {
				return false;
			}
		}
		
		return true;
	}

}
