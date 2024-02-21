import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 21.
 * @link https://www.acmicpc.net/problem/1759
 * @keyword_solution  
 * 1. 한 개의 모음, 최소 두개의 자음
 * 2. 오름차순
 * 3. 일단 알파벳을 정렬
 * 4. 그리고 조합
 * 5. 끝
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */
public class Main {
	static int L, C;
	static char[] alpahbets;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alpahbets = br.readLine().replaceAll(" ", "").toCharArray();
		
		// 정렬
		Arrays.sort(alpahbets);
		
		// 조합
		req(0,0, new char[L]);
		
		System.out.println(sb.toString());
	}
	
	static void req(int depth, int start, char[] combAlpha) {
		// base
		if(depth == L) {
			if(check(combAlpha)) {
				for(char c : combAlpha) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		
		// inductive
		for(int i = start; i < C; i++) {
			combAlpha[depth] = alpahbets[i];
			req(depth + 1, i + 1, combAlpha);
		}
	}
	
	static boolean check(char[] combAlpha) {
		int mo = 0;
		int ja = 0;
		for(int i = 0; i < combAlpha.length; i++) {
			if(combAlpha[i] == 'a' || combAlpha[i] == 'e' || combAlpha[i] == 'i' || combAlpha[i] == 'o' || combAlpha[i] == 'u') {
				mo++;				
			}
			else {
				ja++;
			}
		}
		
		return mo >= 1 && ja >= 2;
	}
}