import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 5.
 * @link https://www.acmicpc.net/problem/1158
 * @keyword_solution   
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Queue<Integer> input = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			input.offer(i);
		}
		
		sb.append("<");
		while(input.size() != 1) {
			for(int i = 0; i < k - 1; i++) {
				input.offer(input.poll());
			}
			
			sb.append(input.poll() + ", ");
		}
		
		sb.append(input.poll() + ">");
		System.out.println(sb.toString());
	}

}
