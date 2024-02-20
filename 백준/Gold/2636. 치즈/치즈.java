import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 20.
 * @link https://www.acmicpc.net/problem/2636
 * @keyword_solution  
 * 1. 치즈에는 하나 이상의 구멍이 있을 수 있다.
 * 2. 공기와 접촉된 칸은 1시간 이상 지나면 녹음
 * 3. 모두 녹아 없어지는 데 걸리는 시간 , 녹기 한 시간 전 치즈조각의 개수
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int n, m;
	static int[][] map;
	static int cheese;
	static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		cheese = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheese++;
				}
			}
		}

		int cheeseCount = 0;
		int time = 0;
		
		while(cheese > 0) {
			cheeseCount = cheese;
			time++;
			visited = new boolean[n][m];
			bfs();
		}
		
		System.out.println(time);
		System.out.println(cheeseCount);
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}
				
				if(visited[nx][ny]) {
					continue;
				}
				
				visited[nx][ny] = true;
				if(map[nx][ny] == 0) {
					queue.add(new Point(nx, ny));
				}
				else {
					cheese--;
					map[nx][ny] = 0;					
				}
			}
		}
	}
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}