import java.util.*;
import java.io.*;

/**
 *	보유 금액 m
 *	같은 숫자를 여러개 구매할 수 있다
 *	
 */
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] prices = new int[n];
		
		// 값이 젤 작은 값 찾기
		int min = 50; // 비용
		int minIdx = -1; // 숫자
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
			
			if(min > prices[i]) {
				min = prices[i];
				minIdx = i;
			}
		}

		// 값 초기화
		int money = Integer.parseInt(br.readLine());
		int cnt = 0;
		char[] result = new char[51];
		
		// 가장 작은 비용을 자리수에 담음
		while(money >= min) {
			result[cnt++] = (char) (minIdx + '0');
			money -= min;
		}
		
		
		// 시작 인덱스
		int start = 0;
		
		// 자릿수 만큼 반복
		for(int i = 0 ; i < cnt; i++) {
			
			// 끝에서 부터 비용을 확인
			for(int j = n - 1; j >= 0; j--) {
				// 남은돈 + 최소 비용보다, 숫자의 비용이 작다면
				if(prices[j] <= money + min) {
					
					// 숫자가 더 큰 것을 대입
					result[i] = (char)(j + '0');
					
					// 비용 재설정
					money += min - prices[j];
					break;
				}
			}
			
			// 현재 돈으로 살 수 없다면, 자릿수 반납
			if(result[start] == '0') {
				start++;
				money += min;
			}
		}
		
		// 오직 0만 구매할 수 있음
		if(start == cnt) {
			System.out.println(0);
			return;
		}
		
		String ans = "";
		for(int i = start; i < cnt; i++) {
			ans += result[i];
		}
		
		System.out.println(ans);
	}
}