import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 23.
 * @link https://www.acmicpc.net/problem/17281
 * @keyword_solution  
 * 1. 선수 9명
 * 
 * 안타: 타자와 모든 주자가 한 루씩 진루한다.
 * 2루타: 타자와 모든 주자가 두 루씩 진루한다.
 * 3루타: 타자와 모든 주자가 세 루씩 진루한다.
 * 홈런: 타자와 모든 주자가 홈까지 진루한다.
 * 아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
 * 
 * 1. 순열
 * 2. 이닝정보에 따라 진행
 * 3. 이전 타석 정보 저장
 * 
 * @input 
 * 1 초 512 MB
 * 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int playerNum = 9, n;
	static boolean[] visited;
	static int[][] games;
	static int priorIdx;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// input data
		n = Integer.parseInt(br.readLine());
		games = new int[n][playerNum];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < playerNum; j++) {
				games[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// set player1
		int[] player = new int[playerNum];
		visited = new boolean[playerNum];
		player[3] = 0;
		visited[3] = true;
		
		// permutation in player
		permutation(1, player);
		
		System.out.println(max);
	}
	
	static void permutation(int depth, int[] player) {
		// base
		if(depth == playerNum) {
			// play game
			int sum = 0;
			priorIdx = 0;
			
			for(int i = 0; i < n; i++) {
				sum += game(priorIdx, games[i], player);
			}
				
			max = Math.max(max, sum);
			return;
		}
		
		// inductive
		for(int i = 0; i < playerNum; i++) {
			if(!visited[i]) {
				visited[i] = true;
				player[i] = depth;
				permutation(depth + 1, player);
				visited[i] = false;
			}
		}
	}
	
	static int game(int cur, int[] hitPlayer, int[] player){
		int outCount = 0;
		int run = 0;
		Stack<Integer> stack = new Stack<>();
		
		while(outCount < 3) {
			cur %= playerNum;
			int p =player[cur];
			int hit = hitPlayer[p];
			
			if(hit == 0) {
				outCount++;
				cur++;
				continue;
			}
			
			// hit
			run++;
			stack.push(hit);
			
			// next player
			cur++;
		}
		
		int k = 0;
		int remain = 0;
		while(!stack.isEmpty()) {
			k += stack.pop();
			if(k > 3) break;
			remain++;
		}
		run -= remain;
		
		// save idx
		priorIdx = cur;
		
		return run;
	}
}