
import java.util.*;

/**
 * @author 지종권
 * @date 2024. 1. 31.
 * @link https://www.acmicpc.net/problem/11659
 * @keyword_solution 누적합 구하기
 * @input 
 * @output
 * 이중 반복문 사용 시 시간초과   
 * @time_complex  n
 * @perf 
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] numbers = new int[n + 1];
		
		for(int i = 1; i <= n ; i++) {
			int num = sc.nextInt();
			numbers[i] = numbers[i - 1] + num;
		}
		
		for(int i = 1; i <= m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			System.out.println(numbers[end] - numbers[start - 1]);
		}
		
	}

}
