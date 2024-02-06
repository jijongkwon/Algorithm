import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 6.
 * @link https://www.acmicpc.net/problem/16926
 * @keyword_solution  
 * 1. 반시계로 회전
 * 2. r만큼 돌린 결과 출력
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n, m, r;
	static int[][] map;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();
		
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int depth = Math.min(n, m);
		for(int i = 0; i < r; i++) {
			visited = new boolean[n][m];
			for(int j = 0; j < depth / 2; j++) {				
				rotation(j,j);
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int [] t : map) {
			for(int output : t) {
				sb.append(output + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void rotation(int x, int y) {
		int px = x;
		int py = y;
		int priorValue = map[px][py];
		
		for(int i = 0; i < 4; i++) {
			while(true) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx >= n || ny >= m || nx < 0 || ny < 0) {
					break;
				}
				
				if(visited[nx][ny]) {
					break;
				}
				
				visited[nx][ny] = true;
				
				int temp = map[nx][ny];
				map[nx][ny] = priorValue;
				priorValue = temp;
				px = nx;
				py = ny;
			}
		}
	}
}
