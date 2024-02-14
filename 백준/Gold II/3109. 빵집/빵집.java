import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 14.
 * @link https://www.acmicpc.net/problem/3109
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int count = 0;
	static int r, c;
	static int[] dx = {-1,0,1};
	static int[] dy = {1,1,1};
	static boolean flag;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();

		map = new char[r][c];
		visited = new boolean[r][c];
		
		for(int i = 0; i < r; i++) {
			String temp = sc.next();
			for(int j = 0; j < c; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		for(int i = 0; i < r; i++) {
			flag = false;
			dfs(i, 0);
		}
		
		System.out.println(count);
	}
	
	static void dfs(int x, int y) {
		// base
		if(flag) {
			return;
		}
		
		if(y == c - 1) {
			flag = true;
			count++;
			return;
		}
		
		// inductive
		for(int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(ny >= c || nx >= r || nx < 0) {
				continue;
			}
			
			if(visited[nx][ny] || map[nx][ny] == 'x') {
				continue;
			}
			
			visited[nx][ny] = true;
			dfs(nx, ny);
			
			if(flag) {
				return;
			}
		}
	}

}