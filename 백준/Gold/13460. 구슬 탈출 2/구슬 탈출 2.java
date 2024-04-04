import java.util.*;
import java.io.*;

/**
 *	직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣고, 빨간 구슬을 구멍을 통해 빼내는 게임
 *	보드의 크기 n *m
 *	바깥 행 열 모두 막힘
 *	파란 구슬이 구멍에 들어가면 안됨
 *	4방 탐색 (기울기라 벽 끝까지 감)
 *	공은 동시에 움직임
 *	빨간 구슬과 파란구슬은 동시에 같은 칸에 있을 수 없음
 *	
 *	return 최소 몇 번 만에 탈출 (10번 이하면 -1)
 *
 *	제한
 *	2초
 *	3 <= n, m <= 10
 *	'.' 빈칸
 *	'#' 장애물
 *	'o' 구멍의 위치
 *	'r' 빨간 구슬
 *	'b' 파란 구슬
 *
 *	풀이
 *	그냥 bfs 
 */
public class Main {
	static int n, m;
	static char[][] map;
	static int[] redLoc;
	static int[] blueLoc;
	static int[] endPoint;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static Queue<Ball> red;
	static Queue<Ball> blue;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		red = new LinkedList<>();
		blue = new LinkedList<>();
		
		// map 입력
		for(int i = 0 ; i < n; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < m; j++) {
				char c = tmp.charAt(j);
				map[i][j] = c;
				
				// red 좌표
				if(c == 'R') {
					red.add(new Ball(i, j, 0));
					map[i][j] = '.';
					continue;
				}
				
				// blue 좌표
				if(c == 'B') {
					blue.add(new Ball(i, j, 0));
					map[i][j] = '.';
					continue;
				}
			}
		}
		
		// 기울이기
		System.out.println(move());
	}
	
	static int move() {
		// 빨간 공, 파란공 둘 다 비지 않을 때 까지 돌림
		while(!red.isEmpty() && !blue.isEmpty()) {
			Ball rb = red.poll();
			Ball bb = blue.poll();
			
			// 못 빠져 나왔을 시
			if(rb.moveNum > 10) {
				return -1;
			}
			
			// 파란공이 빠져나간 경우
			if(map[bb.x][bb.y] == 'O') {
				continue;
			}
			
			// 빨간공이 빠져나간 경우
			if(map[rb.x][rb.y] == 'O') {
				return rb.moveNum;
			}
			
			// 4면으로 움직임
			for(int i = 0; i < 4; i++) {
				
				// 빨간공 움직임
				int nrx = rb.x;
				int nry = rb.y;
				while(true) {
					nrx += dx[i];
					nry += dy[i];
					
					// 벽을 만나면 안됨
					if(map[nrx][nry] == '#') {
						nrx -= dx[i];
						nry -= dy[i];
						break;
					}
					
					// 탈출 구멍 만남
					if(map[nrx][nry] == 'O') {
						break;
					}
				}
				// 파란공 움직임
				int nbx = bb.x;
				int nby = bb.y;
				while(true) {
					nbx += dx[i];
					nby += dy[i];
					
					// 벽을 만나면 안됨
					if(map[nbx][nby] == '#') {
						nbx -= dx[i];
						nby -= dy[i];
						break;
					}
					
					// 탈출 구멍 만남
					if(map[nbx][nby] == 'O') {
						break;
					}
				}
				
				// 같은 선상에 있으면 -> 누가 길이가 기냐에 따라 줄여야함
				if(nrx == nbx && nry == nby && map[nrx][nry] != 'O') {
					// 거리계산
					int redDistance = Math.abs(rb.x - nrx) + Math.abs(rb.y - nry);
					int blueDistance = Math.abs(bb.x - nbx) + Math.abs(bb.y - nby);
					
					// 만약 빨간공의 거리가 길다면 ?
					if(redDistance > blueDistance) {
						nrx -= dx[i];
						nry -= dy[i];
					}
					// 파란공의 거리가 길다면 ?
					else {
						nbx -= dx[i];
						nby -= dy[i];
					}
				}
				
				// 큐에 집어넣기
				red.add(new Ball(nrx, nry, rb.moveNum + 1));
				blue.add(new Ball(nbx, nby, bb.moveNum + 1));
			}
		}
		
		return -1;
	}
	
	static class Ball{
		int x;
		int y;
		int moveNum;
		
		public Ball(int x, int y, int moveNum) {
			this.x = x;
			this.y = y;
			this.moveNum = moveNum;
		}
		
		@Override
		public String toString() {
			return "Ball [x=" + x + ", y=" + y + ", moveNum=" + moveNum + "]";
		}
	}
}