import java.util.*;
import java.io.*;

/**
 * 분단 위 업무 추가 마감 1분기 끝날 때 까지
 * 
 * 업무는 가장 최근에 주어진 순서대로 한다. 또한 업무를 받으면 바로 시작한다. 업무를 하던 도중 새로운 업무가 추가 된다면, 하던 업무를
 * 중단하고 새로운 업무를 진행한다. 새로운 업무가 끝났다면, 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다. 김삼성은 기억력이 좋기
 * 때문에 아무리 긴 시간이 지나도 본인이 하던 부분을 기억할 수 있다.
 * 
 * n <= 1,000,000 a <= 100 t <= 1,000,000
 * 
 * 알고리즘 stack
 * 
 * return 시간이 끝날 때의 점수
 */
public class Main {
	static class Job {
		int score;
		int time;

		public Job(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}

	static int n;
	static int totalScore = 0;
	static int time = 0;
	static Stack<Job> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());

			// 일이 있음
			if (number == 1) {
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				if(time == 1) {
					totalScore += score;
					continue;
				}
				
				stack.push(new Job(score, time - 1));
				continue;
			}
			
			// 일이 없음, 기존일 계속
			if(stack.isEmpty()) {
				continue;
			}
			Job job = stack.pop();
			job.time -= 1;

			if(job.time == 0) {
				totalScore += job.score;
			}
			else {
				stack.push(job);
			}
		}
		
		System.out.println(totalScore);
	}
}