import java.util.*;

/**
 * @author 지종권
 * @date 2024. 1. 30.
 * @link https://www.acmicpc.net/problem/15659
 * @keyword_solution  
 * 1. 
 * @input 
 * 1. 주어진 수열의 순서를 바꾸면 안됨
 * 2. 식은 연산자 우선순위에 의해서 주어진다.
 * 3. n 값이 적다 (2초 제한)
 * 4. 메모리 512 MB로 넉넉
 * @output   
 * 1. -10억 < 최소, 최대 < 10억
 * 식의 최댓값, 최솟값
 * @time_complex  
 * @perf 
 */
public class Main {
	static int[] cals = new int[4];
	static int[] numbers;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		numbers = new int[n];
		
		for(int i = 0 ; i < n; i++) {
			numbers[i] = sc.nextInt();
		}
		
		for(int i = 0; i < 4; i++) {
			cals[i] = sc.nextInt();
		}
		
		req(0, new int[n - 1]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void req(int depth, int[] comb) {
		// base
		if(depth == numbers.length - 1) {
			totalCal(comb);
			return;
		}
		
		for(int j = 0; j < 4; j++) {
			if(cals[j] > 0) {
				comb[depth] = j;
				cals[j]--;
				req(depth + 1, comb);
				cals[j]++;
			}
		}
	}
	
	static void totalCal(int[] operators) {
		Deque<Integer> numberStack = new LinkedList<>();
		Deque<Integer> calStack = new LinkedList<>();
		
		numberStack.addLast(numbers[0]);
		for(int i = 0; i < numbers.length - 1; i++) {
			int operator = operators[i];
			
			if(operator == 2 || operator == 3) {
				int a = numberStack.pollLast();
				int b = numbers[i + 1];
				numberStack.addLast(cal(operator, a, b));
				continue;
			}
			
			numberStack.addLast(numbers[i + 1]);
			calStack.addLast(operators[i]);
		}
		
		
		int sum = numberStack.pollFirst();
		
		while(!numberStack.isEmpty()) {
			sum = cal(calStack.pollFirst(), sum, numberStack.pollFirst());
		}
		
		max = Math.max(max, sum);
		min = Math.min(min, sum);
	}
	
	static int cal(int c, int a, int b) {
		if(c == 0) {
			return a + b;
		}
		
		if(c == 1) {
			return a - b;
		}
		
		if(c == 2) {
			return a * b;
		}
		
		return a / b;
	}
}
