import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 22.
 * @link https://www.acmicpc.net/problem/17144
 * @keyword_solution  
 * 1. 공기청정기는 1번열 위치, 2행 차지
 * 2. 미세먼지 확산, 인접한 4 방향으로 확산
 * 3. 인접한 공간에 공기청전기 or 칸x 면 확산 x
 * 4. 확산된 양 Ar,c/5이고 소수점은 버린다. = ⌊Ar,c/5⌋
 * 5. (r, c) 에 남은 미세먼지 양 Ar,c - ⌊Ar,c/5⌋ * 확산된 방향의 개수
 * 6. 위쪽 공기청정기는 반시계, 아래쪽은 시계
 * 7. 바람이 불 때, 바람의 방향대로 한 칸씩 이동
 * 8. 공기청정기로 들어간 미세먼지 정화
 * 
 * 미세먼지 확장 후 -> 공기청정기 동작
 * 단순 시뮬레이션
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int r, c, time;
	static int[][] map;
	static int[][] dx = {{0,-1,0,1}, {0,1,0,-1}};
	static int[][] dy = {{1,0,-1,0}, {1,0,-1,0}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// input map size and time
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		// input map elements
		map = new int[r][c];
		AirCleaner airCleaner = new AirCleaner();
		boolean flag = false;
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				int number = Integer.parseInt(st.nextToken());
				map[i][j] = number;
				
				// set air cleaner loc
				if(number == -1 && !flag) {
					airCleaner.setTop(i, j);
					flag = true;
				}
				
				else if(number == -1 && flag) {
					airCleaner.setBottom(i, j);
				}
			}
		}
		
		for(int t = 0; t < time; t++) {
			// input gas
			List<int[]> gas = new ArrayList<>();
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					
					// impossible spread
					if(map[i][j] < 5) {
						continue;
					}
					
					// possible spread
					gas.add(new int[] {i,j,map[i][j]});
				}
			}
			
			// spread
			for(int[] loc : gas) {
				spread(loc[0], loc[1], loc[2]);
			}
			
			// operate air cleaner
			airCleaner.rotation();
		}
		
		System.out.println(calGas());
	}
	
	static void spread(int x, int y, int gas) {
		int amountGas = gas / 5;
		int spreadCount = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[0][i];
			int ny = y + dy[0][i];
			
			if(check(nx, ny)) {
				continue;
			}
			
			spreadCount++;
			map[nx][ny] += amountGas;
		}
		
		map[x][y] -= (amountGas * spreadCount);
	}
	
	// possible check
	static boolean check(int x, int y) {
		return x < 0 || y < 0 || x >= r || y >= c || map[x][y] == -1;
	}
	
	// cal gas
	static int calGas() {
		int sum = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] == 0 || map[i][j] == -1) {
					continue;
				}
				
				sum += map[i][j];
			}
		}
		
		return sum;
	}

	static class AirCleaner{
		int topX;
		int topY;
		int bottomX;
		int bottomY;
		
		public void setTop(int topX, int topY) {
			this.topX = topX;
			this.topY = topY;
		}

		public void setBottom(int bottomX, int bottomY) {
			this.bottomX = bottomX;
			this.bottomY = bottomY;
		}	
		
		public void rotation() {
			int tx = topX;
			int ty = topY;
			int topValue = 0;
			int bx = bottomX;
			int by = bottomY;
			int bottomValue = 0;
			
			// 4 direction move
			for(int i = 0; i < 4; i++) {	
				// move gas
				int[] topMove = move(tx,ty,0,i,topValue);
				int[] bottomMove = move(bx,by,1,i,bottomValue);
				
				// set next top location
				tx = topMove[0];
				ty = topMove[1];
				topValue = topMove[2];
				
				//set next bottom location
				bx = bottomMove[0];
				by = bottomMove[1];
				bottomValue = bottomMove[2];
			}
		}
		
		public int[] move(int x, int y, int dir, int move, int value) {
			int cur = value;
			while(true) {
				x += dx[dir][move];
				y += dy[dir][move];
				
				if(check(x,y)) {
					x -= dx[dir][move];
					y -= dy[dir][move];
					break;
				}
				
				int tmp = map[x][y];
				map[x][y] = cur;
				cur = tmp;
			}
			
			return new int[] {x,y,cur};
		}
	}
}