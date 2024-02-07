
import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 7.
 * @link https://www.acmicpc.net/problem/2563
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static boolean[][] map = new boolean[100][100];
	static int count = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int paperNum = sc.nextInt();
		
		for(int i = 0 ; i < paperNum; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			paint(x,y);
		}
		
		System.out.println(count);
	}

	static void paint(int x, int y) {
		
		for(int i = x; i < x + 10; i++) {
			for(int j = y; j < y + 10; j++) {
				if(!map[i][j]) {
					map[i][j] = true;
					count++;
				}
			}
		}
	}
}
