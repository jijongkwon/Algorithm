import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 지종권
 * @date 2024. 2. 14.
 * @link https://www.acmicpc.net/problem/6987
 * @keyword_solution 
 * 1. 승리 횟수 == 패배 횟수
 * 2. 국가별 한 번씩 5번의 경기를 치룸
 * 3. 재귀를 통해 확인
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static final int MAX_TEAM_COUNT = 6;
	static int[][] matches;
	static boolean isEndGame = false;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int size = 0;
		for(int i = 1; i < MAX_TEAM_COUNT; i++) {
			size += i;
		}
		
		matches = new int[size][2];
		int index = 0;
		for(int i = 0; i < MAX_TEAM_COUNT - 1; i++) {
			for(int j = i + 1; j < MAX_TEAM_COUNT; j++) {
				matches[index][0] = i;
				matches[index][1] = j;
				index++;
			}
		}
		
		for(int j = 0 ; j < 4; j++)
		{
			st = new StringTokenizer(br.readLine());
			int[][] worldCup = new int[MAX_TEAM_COUNT][3];
			boolean isPossible = true;
			
			for(int i = 0; i < MAX_TEAM_COUNT; i++) {
				int win = Integer.valueOf(st.nextToken());
				int draw = Integer.valueOf(st.nextToken());
				int lose = Integer.valueOf(st.nextToken());
				
				worldCup[i][0] = win;
				worldCup[i][1] = draw;
				worldCup[i][2] = lose;
				
				if(win + draw + lose != 5) {
					isPossible = false;
					break;
				}
			}
			
			if(isPossible) {
				backTracking(worldCup, 0, size);
				if(isEndGame) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
			}
			else {
				sb.append(0);
			}
			
			sb.append(" ");
			isEndGame = false;
		}
		
		System.out.print(sb.toString());
	}
	
	static void backTracking(int[][] worldCup, int matchCount, int size) {
		if(isEndGame) {
			return;
		}
		
		if(matchCount == size) {
			isEndGame = true;
			return;
		}
		
		int myTeam = matches[matchCount][0];
		int enemyTeam = matches[matchCount][1];
		
		if(worldCup[myTeam][0] > 0 && worldCup[enemyTeam][2] > 0) {
			worldCup[myTeam][0]--;
			worldCup[enemyTeam][2]--;
			backTracking(worldCup, matchCount + 1, size);
			worldCup[myTeam][0]++;
			worldCup[enemyTeam][2]++;
		}

		if(worldCup[myTeam][1] > 0 && worldCup[enemyTeam][1] > 0) {
			worldCup[myTeam][1]--;
			worldCup[enemyTeam][1]--;
			backTracking(worldCup, matchCount + 1, size);
			worldCup[myTeam][1]++;
			worldCup[enemyTeam][1]++;
		}

		if(worldCup[myTeam][2] > 0 && worldCup[enemyTeam][0] > 0) {
			worldCup[myTeam][2]--;
			worldCup[enemyTeam][0]--;
			backTracking(worldCup, matchCount + 1, size);
			worldCup[myTeam][2]++;
			worldCup[enemyTeam][0]++;
		}
	}
}