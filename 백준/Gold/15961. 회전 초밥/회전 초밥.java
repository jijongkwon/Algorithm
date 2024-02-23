import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 23.
 * @link https://www.acmicpc.net/problem/15961
 * @keyword_solution 1. 벹르 위에는 같은 종류의 초밥이 둘 이상 가능 2. k개의 접시를 연속해서 먹을 경우 할인된 정액
 *                   가격 3. 1번 행사에 참가할 경우, 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공
 * 
 *                   완탐시 시간초과 슬라이딩 윈도우 기법
 * 
 * @input 조건 : 1 초 512 MB 2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000
 * @output
 * @time_complex
 * @perf
 */
public class Main {
	static int beltNum, sushiNum, sequenceNum, couponNum;
	static int[] belts;
	static int[] window;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// input number of data
		beltNum = Integer.parseInt(st.nextToken());
		sushiNum = Integer.parseInt(st.nextToken());
		sequenceNum = Integer.parseInt(st.nextToken());
		couponNum = Integer.parseInt(st.nextToken());

		// input data in belt
		belts = new int[beltNum];
		for (int i = 0; i < beltNum; i++) {
			belts[i] = Integer.parseInt(br.readLine());
		}

		// init
		window = new int[sushiNum + 1];

		// slide window
		System.out.println(slide());
	}

	static int slide() {
		int count = 0;
		
		for(int i = 0; i < sequenceNum; i++) {
			if(window[belts[i]]++ == 0) {
				count++;
			}
		}
		
		int max = count;

		for(int i = 0; i < beltNum; i++) {
			// left
			if(--window[belts[i]] == 0) {
				count--;
			}
			
			// right
			if(window[belts[(i + sequenceNum)  % beltNum]]++ == 0) {
				count++;
			}
			
			// find max
			max = Math.max(max, count + (window[couponNum] == 0 ? 1 : 0));
		}
		
		return max;
	}
}