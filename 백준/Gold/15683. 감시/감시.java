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

	static int N;
	static int[][] map;
	static int[][] dirs = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 상, 좌, 우, 하
	static int[][][] lookIdx = { { { 0 }, { 1 }, { 2 }, { 3 } }, // 상, 좌, 우, 하
							   { { 0, 3 }, { 1, 2 } }, // 상하, 좌우
							   { { 0, 2 }, { 2, 3 }, { 3, 1 }, { 1, 0 } }, // 상우, 우하, 하좌, 좌상
							   { { 0, 1, 2 }, { 0, 2, 3 }, { 1, 2, 3 }, { 0, 1, 3 } }, // 상좌우, 상우하, 좌하우, 하좌상
							   { { 0, 1, 2, 3 } } }; // 상좌우하
	static int M;
	static ArrayList<int[]> cctv;
	static int cctvSize;
	static int result = Integer.MAX_VALUE;
	static int zeroCnt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// cctv 리스트 넣기
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctv.add(new int[] { i, j });
				}
				
				// 사각지대 개수 세기
				else if(map[i][j]==0) {
					zeroCnt++;
				}
			}
		}
		
		// 시시티비 크기
		cctvSize = cctv.size();
		
		// 감시 시작
		dfs(0,0);

		// 출력
		System.out.println(result);
	}

	private static void dfs(int idx,int cnt) {
		// base
		if (idx == cctvSize) {
			result = Math.min(result, zeroCnt-cnt);
			return;
		}
		
		// inductive
		int end = 4;
		int[] cv = cctv.get(idx);
		int cctvNum = map[cv[0]][cv[1]];

		// 4 2 4 4 1 cctv에 따른 방향 개수
		if (cctvNum == 5) {
			end = 1;
		} else if (cctvNum == 2)
			end = 2;
		for (int i = 0; i < end; i++) {
			// 맵에 감시하는 곳 채우기
			dfs(idx + 1, cnt+see(cctvNum, i, -1, cv));
			// 초기화
			see(cctvNum, i, 1, cv);
		}

	}

	private static int see(int cctvNum, int turnIdx, int flag, int[] loc) {
		int[] turn = lookIdx[cctvNum - 1][turnIdx]; 
		int cnt = 0;
		
		for (int d = 0; d < turn.length; d++) { //4방향
			int r = loc[0];
			int c = loc[1];
			while (true) {//한방향으로 쭉
				r += dirs[turn[d]][0];
				c += dirs[turn[d]][1];
				if (r < 0 || r > N - 1 || c < 0 || c > M - 1 || map[r][c] == 6)
					break;
				if (map[r][c] > 0 && map[r][c] < 6)
					continue;
				if (map[r][c] == 0)
					cnt++;

				map[r][c] += flag;
			}

		}
		
		return cnt;
	}

}