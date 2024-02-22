import java.util.*;
import java.io.*;

/**
 * @author 지종권
 * @date 2024. 2. 21.
 * @link https://www.acmicpc.net/problem/14891
 * @keyword_solution 
 * 1. 회전은 한칸 기준 (시, 반시계) 
 * 2. 같은 극일 때는 회전 x 
 * 3. 반대 극일 때는 톱니가 회전한 방향의 반대 방향으로 회전
 * return 최종 톱니바퀴 상태
 * @input
 * @output
 * @time_complex
 * @perf
 */
public class Main {
	static final int NUMBER_OF_TEETH = 8;
	static final int NUMBER_OF_GEARS = 4;
	static List<Integer>[] list = new LinkedList[NUMBER_OF_GEARS + 1];
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i <= 4; i++) {
			String tmp = br.readLine();
			list[i] = new LinkedList<>();
			for (int j = 0; j < NUMBER_OF_TEETH; j++) {
				list[i].add(tmp.charAt(j) - '0');
			}
		}

		// 회전
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int[] directions = new int[NUMBER_OF_GEARS + 1];
			int cycleNum = Integer.parseInt(st.nextToken());
			int cycleDir = Integer.parseInt(st.nextToken());

			// 회전 유무 판단 배열
			directions[cycleNum] = cycleDir;

			// 왼쪽 회전 유무 판단
			int prior = cycleNum;
			int left = cycleNum - 1;
			while (left > 0) {
				if (list[left].get(2) != list[prior].get(6)) {
					if (directions[prior] == 1) {
						directions[left] = -1;
					}
					else if(directions[prior] == -1){
						directions[left] = 1;
					}
				}

				prior = left;
				left--;
			}

			// 오른쪽 회전 유무 판단
			prior = cycleNum;
			int right = cycleNum + 1;
			while (right <= 4) {
				if (list[prior].get(2) != list[right].get(6)) {
					if (directions[prior] == 1) {
						directions[right] = -1;
					}
					else if (directions[prior] == -1) {
						directions[right] = 1;
					}
				}

				prior = right;
				right++;
			}

			// 회전
			rotation(directions);
		}

		System.out.println(cal());
	}

	static int cal() {
		int sum = 0;
		for (int i = 1; i <= 4; i++) {
			if (list[i].get(0) == 1) {
				sum += (int) Math.pow(2, i - 1);
			}
		}

		return sum;
	}

	static void rotation(int[] directions) {
		// 회전
		for (int j = 1; j <= 4; j++) {
			if (directions[j] == 1) {
				int last = list[j].get(list[j].size() - 1);
				list[j].remove(list[j].size() - 1);
				list[j].add(0, last);
			} 
			else if (directions[j] == -1) {
				int first = list[j].get(0);
				list[j].remove(0);
				list[j].add(first);
			}
		}
	}
}