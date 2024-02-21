import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 21.
 * @link https://www.acmicpc.net/problem/15683
 * @keyword_solution  
 * 1. n * m 직사각형
 * 2. k개의 5 종류의 cctv ( k <= 8 )
 * 3. 회전방향 90' , 방향이 가로 or 세로
 * 4. 적절히 돌려서 사각지대를 최소로 만들자!
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	public static int N, M;
	public static int[][] map;
	public static int[][] copyMap;
	public static int[] output;
	public static ArrayList<CCTV> cctvList;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		cctvList = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if(map[i][j] != 0 &&  map[i][j] != 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
			}
		}

		// 순열
		output = new int[cctvList.size()];
		permutation(0, cctvList.size());

		System.out.println(answer);
	}

	// 순열
	public static void permutation(int depth, int r) {
		// base
		if(depth == r) {
			copyMap = copyMap();
						
			// cctv번호와 순열로 뽑혀진 방향에 맞는 상하좌우 방향 설정 
			for(int i = 0; i < cctvList.size(); i++) {
				direction(cctvList.get(i), output[i]);
			}
			
			// 사각 지대 구하기 
			getBlindSpot();

			return;
		}
		
		// inductive
		for(int i = 0; i < 4; i++) {
			output[depth] = i;
			permutation(depth+1, r);
		}
	}

	// 각 cctv 번호와 순열로 뽑혀진 방향에 맞게 감시 
	public static void direction(CCTV cctv, int d) {
		int cctvNum = cctv.num;

		if(cctvNum == 1) {
			if(d == 0) watch(cctv, 0); // 상 
			else if(d == 1) watch(cctv, 1); // 우 
			else if(d == 2) watch(cctv, 2); // 하  
			else if(d == 3) watch(cctv, 3); // 좌 
		} else if(cctvNum == 2) {
			if(d == 0 || d == 2) {
				watch(cctv, 0); watch(cctv, 2); // 상하 
			} else {
				watch(cctv, 1); watch(cctv, 3); // 좌우 
			}
		} else if(cctvNum == 3) {
			if(d == 0) {
				watch(cctv, 0); // 상우 
				watch(cctv, 1);
			} else if(d == 1) { 
				watch(cctv, 1); // 우하 
				watch(cctv, 2);
			} else if(d == 2) { 
				watch(cctv, 2); // 하좌 
				watch(cctv, 3);
			} else if(d == 3) { 
				watch(cctv, 0); // 좌상 
				watch(cctv, 3);
			}
		} else if(cctvNum == 4) {
			if(d == 0) {
				watch(cctv, 0); // 좌상우 
				watch(cctv, 1);
				watch(cctv, 3);
			} else if(d == 1) {
				watch(cctv, 0); // 상우하 
				watch(cctv, 1);
				watch(cctv, 2);
			} else if(d == 2) {
				watch(cctv, 1); // 좌하우 
				watch(cctv, 2);
				watch(cctv, 3);
			} else if(d == 3) {
				watch(cctv, 0); // 상좌하 
				watch(cctv, 2);
				watch(cctv, 3);
			}
		} else if(cctvNum == 5) { // 상우하좌
			watch(cctv, 0);
			watch(cctv, 1);
			watch(cctv, 2);
			watch(cctv, 3);
		}
	}
	
	// BFS로 방향에 맞게 감시 
	public static void watch(CCTV cctv, int d) {
		Queue<CCTV> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		queue.add(cctv);
		visited[cctv.x][cctv.y] = true;

		while(!queue.isEmpty()) {
			int nx = queue.peek().x + dx[d];
			int ny = queue.poll().y + dy[d];

			// 범위를 벗어나거나 벽을 만나면 끝 
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) { 
				break;
			}

			// 감시
			if(copyMap[nx][ny] == 0) { 
				copyMap[nx][ny] = -1; 
				queue.add(new CCTV(cctv.num, nx, ny));
			} else {  
				queue.add(new CCTV(cctv.num, nx, ny)); 
			}
		}
	}
	
	// 사각 지대 구하기 
	public static void getBlindSpot() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		answer = Math.min(answer, cnt);
	}
	
	static int[][] copyMap(){
		copyMap = new int[N][M];
		for(int i = 0; i < map.length; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
		}
		
		return copyMap;
	}

	static class CCTV {
		int num;
		int x;
		int y;

		CCTV(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
}