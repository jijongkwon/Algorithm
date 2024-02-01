import java.util.*;

import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 1.
 * @link https://www.acmicpc.net/problem/12891
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int checks[] = new int[4]; // A, C, G, T
	static int[] copyStrs = new int[4];
	static int s, p;
	static char[] str;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextInt();
		p = sc.nextInt();
		str = sc.next().toCharArray();
		int sum = 0;
		
		for(int i = 0; i < 4; i++) {
			checks[i] = sc.nextInt();
		}
		
		for(int i = 0; i < p; i++) {		
			switch (str[i]) {
			case 'A': copyStrs[0]++; break;
			case 'C': copyStrs[1]++; break;
			case 'G': copyStrs[2]++; break;
			case 'T': copyStrs[3]++; break;
			}
		}
		
		for(int i = 0; i <= s - p; i++) {
			boolean flag = false;
			for(int j = 0 ; j < 4; j++) {
				if(copyStrs[j] < checks[j]) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				sum++;
			}
			
			if(i == s - p) {
				break;
			}
			
			copyStrs[position(str[i])]--;
			copyStrs[position(str[i + p])]++;
		}
		
		System.out.println(sum);
	}
	
	private static int position(char alph) {
		switch(alph) {
		case 'A' : return 0;
		case 'C' : return 1;
		case 'G' : return 2;
		case 'T' : return 3;
		}
		return -1;
	}
}
