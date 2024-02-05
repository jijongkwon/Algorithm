import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 5.
 * @link https://www.acmicpc.net/problem/1158
 * @keyword_solution 
 * for(int i = 0; i < k - 1; i++)
 * k - 1 번째까지 앞의 문자, 뒤에 추가하기
 * @input 
 * @output   
 * @time_complex  n ^ 2
 * @perf 298640kb	688ms
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
