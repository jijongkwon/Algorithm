import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * n * n 동굴
 * 링크는 잃는 금액을 최소로 하여 동굴 건넌편으로 이동
 * 
 * 풀이 누적합
 * 
 * return 최소 금액
 */
public class Main {
	static int n;
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int index = 0;
		while(true) {
			
			// map size
			n = Integer.parseInt(br.readLine());
			
			// end game
			if(n == 0) {
				break;
			}
			
			// input data in map
			map = new int[n][n];
			copyMap = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					copyMap[i][j] = map[i][j];
				}
			}
			
			// stack sum
			visited = new boolean[n][n];
			bfs();
			
			System.out.println("Problem " + ++index + ": " + map[n - 1][n - 1]);
		}
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0 ; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					map[nx][ny] += map[p.x][p.y];
					queue.add(new Point(nx, ny));
					continue;
				}
				
				if(map[nx][ny] > copyMap[nx][ny] + map[p.x][p.y]) {
					map[nx][ny] = copyMap[nx][ny] + map[p.x][p.y];
					queue.add(new Point(nx, ny));
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