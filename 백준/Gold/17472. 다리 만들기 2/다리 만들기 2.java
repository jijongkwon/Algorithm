import java.util.*;
import java.io.*;

/**
 * 다리 길이 2 이상 다리 방향이 바뀌면 안됨
 *
 * 제한 1 <= n, m <= 10 3 <= n * m <= 100 2 <= 섬의 개수 <= 6
 * 
 * 풀이 1. bfs로 섬의 개수와 좌표를 저장 2. 백트래킹 사용 2-1 ) 만약 연결 실패 ? -> 돌아감 2-2 ) 성공 > -> 재귀
 * 3. 모두 연결에 성공했다면 MST 확인하고 4. 확인됐다면 최솟값 비교
 */
public class Main {
	static int n, m;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] check;
	
	// mst
	static PriorityQueue<Point> pq;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// map 에 데이터 넣기
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 탐색
		int islandNum = 2;
		check = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				if (map[i][j] != 0 && !check[i][j]) {
					findIslands(i, j, islandNum);
					islandNum++;
				}
			}
		}
//		
//		for(int[] m : map) {
//			System.out.println(Arrays.toString(m));
//		}

		// 연결
		pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					connectIslands(i, j, map[i][j]);
				}
			}
		}
		
		// mst
		islandNum--;
		parent = new int[islandNum];
		for(int i = 1; i < islandNum; i++) {
			parent[i] = i;
		}
		
		int sum = shortestPath();
		System.out.println(sum == 0 ? -1 : sum);
	}

	static void findIslands(int x, int y, int islandNum) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(x, y));
		check[x][y] = true;
		map[x][y] = islandNum;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}

				if (check[nx][ny]) {
					continue;
				}

				if (map[nx][ny] == 0) {
					continue;
				}

				if(map[nx][ny] == 1) {
					check[nx][ny] = true;
					map[nx][ny] = islandNum;
					queue.add(new Point(nx, ny));
				}
			}
		}
	}

	static void connectIslands(int x, int y, int islandNum) {
		Queue<Point> queue = new LinkedList<>();
		check = new boolean[n][m];

		for (int i = 0; i < 4; i++) {
			queue.add(new Point(x, y, 0));
			check[x][y] = true;
			
			while (!queue.isEmpty()) {
				Point p = queue.poll();

				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}

				if (check[nx][ny]) {
					continue;
				}

				if(map[nx][ny] != islandNum) {
					// 섬이 나올 때 까지 탐색
					if(map[nx][ny] == 0) {
						check[nx][ny] = true;
						queue.add(new Point(nx, ny, p.value + 1));
					}
					
					// 섬을 만났을 때
					else {
						int from = islandNum - 1;
						int to = map[nx][ny] - 1;
						int length = p.value;
						
						if(length > 1) {
							pq.add(new Point(from, to, length));
							break;
						}
					}
				}
			}
			queue.clear();
		}
	}
	
	// mst
	static int shortestPath() {
		int sum = 0;
		int size = pq.size();
		
		for(int i = 0; i < size; i++) {
			Point p = pq.poll();
			
			if(find(p.x) != find(p.y)) {
				sum += p.value;
				union(p.x, p.y);
			}
		}
		
		// 연결 여부 확인
		int startIsland = parent[1];
		for(int i = 2; i < parent.length; i++) {
			if(startIsland != find(parent[i])) {
				return 0;
			}
		}
		
		return sum;
	}
	
	// union
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a < b) {
			parent[b] = a;
		}
		else {
			parent[a] = b;
		}
	}
	
	// find
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static class Point implements Comparable<Point>{
		int x;
		int y;
		int value;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", value=" + value + "]";
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.value, o.value);
		}
	}
}