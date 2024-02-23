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
	static int[] check;

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
		check = new int[sushiNum + 1];

		// slide window
		System.out.println(slide());
	}

	static int slide() {
		// number of type
		int inSlide = 0;
		int total = 0;
		for (int i = 0; i < sequenceNum; i++) {
			if (check[belts[i]] == 0) {
				inSlide++;
			}
			check[belts[i]]++;
		}

		// number of final total
		total = inSlide;

		for (int i = 1; i < beltNum; i++) {
			// Coupons can be applied
			if (total <= inSlide) {
				if (check[couponNum] == 0) {
					total = inSlide + 1;
				} else {
					total = inSlide;
				}
			}

			// left window
			check[belts[i - 1]]--;

			// no more type
			if (check[belts[i - 1]] == 0) {
				inSlide--;
			}

			// right window
			if (check[belts[(i + sequenceNum - 1) % beltNum]] == 0) {
				inSlide++;
			}

			// check visited sushi
			check[belts[(i + sequenceNum - 1) % beltNum]]++;
		}

		// Coupons can be applied
		if (total <= inSlide) {
			if (check[couponNum] == 0) {
				total = inSlide + 1;
			} else {
				total = inSlide;
			}
		}

		return total;
	}
}