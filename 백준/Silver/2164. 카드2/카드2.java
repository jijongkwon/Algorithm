import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 2.
 * @link https://www.acmicpc.net/problem/2164
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  1
 * @perf 
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Deque<Integer> stack = new LinkedList<>();
		
		for(int i = 1; i <= n; i++) {
			stack.addLast(i);
		}
		
		while(stack.size() > 1) {
			stack.pollFirst();
			stack.addLast(stack.pollFirst());
		}
		
		System.out.println(stack.pop());
	}
}
