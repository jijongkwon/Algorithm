import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 13.
 * @link https://www.acmicpc.net/problem/16435
 * @keyword_solution  
 * 1. 자신의 길이 보다 작거나 or 같은 높이에 있는 과일을 먹을 수 있음
 * 2. 최대길이 
 * @input 
 * @output   
 * @time_complex  
 * @perf 21204kb	284ms
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int l = sc.nextInt();
		
		int[] fs = new int[n];
		for(int i = 0; i < n; i++) {
			int f = sc.nextInt();
			
			fs[i] = f;
		}
		Arrays.sort(fs);
		
		for(int i = 0; i < n; i++) {
			if(fs[i] <= l) {
				l++;
			}
		}
		
		System.out.println(l);
	}
}