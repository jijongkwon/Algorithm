import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 1.
 * @link https://www.acmicpc.net/problem/2023
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n;
	static int numbers[] = {1,2,3,4,5,6,7,8,9};
	static List<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		list = new ArrayList<>();
		n = sc.nextInt();
	
		req(0,"");
		
		for(int ans : list) {
			System.out.println(ans);
		}
	}
	
	static void req(int depth, String number) {
		if(depth == n) {
			list.add(Integer.parseInt(number));
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			String tmpString = number + i;
			int tmpInt = Integer.parseInt(tmpString);
			boolean flag = false;
			
			if(tmpInt == 1 && depth == 0) {
				continue;
			}
			
			if(tmpInt == 2 && depth == 0) {
				req(depth + 1, tmpString);
				continue;
			}
			
			for(int j = 2; j <= Math.sqrt(tmpInt); j++) {
				if(tmpInt % j == 0) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				continue;
			}
			
			req(depth + 1, tmpString);
		}
	}
}
