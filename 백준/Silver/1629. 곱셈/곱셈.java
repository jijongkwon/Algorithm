import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 13.
 * @link
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static long a, b, c;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();

		System.out.println(partition(b));
	}
	
	static long partition(long b) {
		if(b == 1) {
			return a % c;
		}
		
		long answer = partition(b / 2);
		
		if(b % 2 == 1) {
			return (answer * answer % c) * a % c;
		}
		
		return answer * answer % c;
	}
}