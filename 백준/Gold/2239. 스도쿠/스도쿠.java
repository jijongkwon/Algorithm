import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] map = new int[9][9];
	static List<int[]> loc = new ArrayList<>();
	static boolean complete = false;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input data in map
		for (int i = 0; i < 9; i++) {
			String[] tmp = br.readLine().split("");
			
			for (int j = 0; j < 9; j++) {
				int data = Integer.parseInt(tmp[j]);
				
				if(data == 0) {
					loc.add(new int[] {i, j});
				}
				
				map[i][j] = data;
			}
		}
		
		dfs(0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(Integer.valueOf(map[i][j]));
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int depth) {
		// 이미 완성
		if(complete) {
			return;
		}
		
		// base
		if(depth == loc.size()) {
			complete = true;
			return;
		}
		
		// inductive
		int[] location = loc.get(depth);
		for(int value = 1; value <= 9; value++) {
			// 행 중복 체크
			if(checkRow(location, value)) {
				continue;
			}
			
			// 열 중복 체크
			if(checkCol(location, value)) {
				continue;
			}
			
			// 3*3 중복 체크
			if(checkNN(location, value)) {
				continue;
			}
			
			map[location[0]][location[1]] = value;
			dfs(depth + 1);
			if(!complete) {
				map[location[0]][location[1]] = 0;
			}
		}
	}
	
	static boolean checkRow(int[] loc, int value) {
		int row = loc[0];
		
		for(int i = 0; i < 9; i++) {
			// 중복 발생
			if(value == map[row][i] && map[row][i] != 0) {
				return true;
			}
		}
		
		return false;
	}
	
	static boolean checkCol(int[] loc, int value) {
		int col = loc[1];
		
		for(int i = 0; i < 9; i++) {
			// 중복 발생
			if(value == map[i][col] && map[i][col] != 0) {
				return true;
			}
		}
		
		return false;
	}
	
	static boolean checkNN(int[] loc, int value) {
		int row = checkRangeInMap(loc[0]);
		int col = checkRangeInMap(loc[1]);
		
		for(int i = row; i < row + 3; i++) {
			for (int j = col; j < col + 3; j++) {
				if(map[i][j] == value && map[i][j] != 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	static int checkRangeInMap(int data) {
		int startLoc = 0;
		if(data < 3) {
			startLoc = 0;
		}
		else if(data < 6) {
			startLoc = 3;
		}
		else if(data < 9) {
			startLoc = 6;
		}
		
		return startLoc;
	}
}