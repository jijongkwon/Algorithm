import java.util.*;
import java.io.*;

/**
 * '.' 비어있는 곳 '*' 물 차있는 곳 'X' 돌 'D' 비버굴 'S' 고슴도치 위치 물은 비어있는 칸으로 확장 ( 돌, 고슴도치,
 * 소굴로 확장 x )
 *
 * return : 최소시간
 */
public class Main {
	static int row, col;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static char[][] map;
	static int[] endPoint = new int[2];
	static Queue<Water> water = new LinkedList<>();
	static Queue<Hog> hog = new LinkedList<>();
	static boolean flag = false;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];

		// input data in map;
		for (int i = 0; i < row; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < col; j++) {
				char c = tmp.charAt(j);

				if (c == '*') {
					water.add(new Water(i, j));
				}

				if (c == 'S') {
					hog.add(new Hog(i, j, 0));
				}

				if (c == 'D') {
					endPoint[0] = i;
					endPoint[1] = j;
				}

				map[i][j] = c;
			}
		}
		while(!hog.isEmpty()) {
			// 물먼저 퍼트리기
			spreadWater(water.size());
			
			// 고슴도치 움직이기
			moveHog(hog.size());
			
			
			if(flag) {
				break;
			}
		}

		if(flag) {
			System.out.println(min);
		}
		else {
			System.out.println("KAKTUS");
		}
	}

	static void spreadWater(int size) {
		for(int i = 0 ; i < size; i++) {
			Water w = water.poll();
			
			for(int j = 0 ; j < 4; j++) {
				int nx = w.x + dx[j];
				int ny = w.y + dy[j];
				
				if(nx < 0 || ny < 0 || nx >= row || ny >= col) {
					continue;
				}
				
				if(map[nx][ny] == 'D' || map[nx][ny] == '*' || map[nx][ny] == 'X') {
					continue;
				}
				
				map[nx][ny] = '*';
				water.add(new Water(nx, ny));
			}
		}
	}

	static void moveHog(int size) {
		for(int i = 0 ; i < size; i++) {
			Hog h = hog.poll();
			
			for(int j = 0 ; j < 4; j++) {
				int nx = h.x + dx[j];
				int ny = h.y + dy[j];
				
				if(nx < 0 || ny < 0 || nx >= row || ny >= col) {
					continue;
				}
				
				if(map[nx][ny] == 'D') {
					flag = true;
					min = h.time + 1;
					return;
				}
				
				if(map[nx][ny] == 'X') {
					continue;
				}
				
				if(map[nx][ny] == '*') {
					continue;
				}
				
				if(map[nx][ny] == 'S') {
					continue;
				}
				
				map[nx][ny] = 'S';
				hog.add(new Hog(nx, ny, h.time + 1));
			}
		}
	}

	static class Water {
		int x;
		int y;

		public Water(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Hog {
		int x;
		int y;
		int time;

		public Hog(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}