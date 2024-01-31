import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 1. 31.
 * @link https://www.acmicpc.net/problem/17136
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int[] colorPapers = {0,5,5,5,5,5};
	static int[][] map = new int[10][10];
	static int min = Integer.MAX_VALUE;
	static int endDepth = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				int number = sc.nextInt();
				if(number == 1) {
					endDepth++;
				}
				map[i][j] = number;
			}
		}
		
		if(endDepth == 0) {
			System.out.println(0);
			return;
		}
		
		req(0,0,0);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	}
	
	static void req(int x,int y, int count) {
		// base
		if(x >= 9 && y > 9) {	
			min = Math.min(min, count);
			return;
		}
		
		if(count >= min) {
			return;
		}
		
		if(y > 9) {
			req(x + 1, 0, count);
			return;
		}
		
		// inductive
		if(map[x][y] == 1) {
			for(int k = 5; k >= 1; k--) {
				if(colorPapers[k] > 0 && isPaint(x,y,k)) {
					paint(x, y, k, 0);
					colorPapers[k]--;
					req(x, y + 1, count + 1);
					colorPapers[k]++;
					paint(x, y, k, 1);
				}
			}
		}
		else {
			req(x, y + 1, count);
		}
		
	}
	
	static boolean isPaint(int x, int y, int k) {
		for(int i = x; i < x + k; i++) {
			for(int j = y; j < y + k; j++){
				if(i < 0 || j < 0 || i >= 10 || j >= 10) {
					return false;
				}
				
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void paint(int x, int y, int k, int state) {
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                map[i][j] = state;
            }
        }
    }
}
