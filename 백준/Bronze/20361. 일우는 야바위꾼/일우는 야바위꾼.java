import java.util.*;
import java.io.*;

/*
 * 배열로 간식의 위치를 저장
 * return 사탕의 위치
 */
public class Main {
	static int N, X, K;
	static int[] cups;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		// 입력
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 초기화
		cups = new int[N + 1];
		cups[X] = 1;

		// 야바위
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			swap(a, b);
		}

		// 위치 찾기
		int index = 1;
		for (int i = 1; i <= N; i++) {
			if (cups[i] == 1) {
				break;
			}

			index++;
		}

		System.out.println(index);
	}

	static void swap(int a, int b) {
		int tmp = cups[a];
		cups[a] = cups[b];
		cups[b] = tmp;
	}
}