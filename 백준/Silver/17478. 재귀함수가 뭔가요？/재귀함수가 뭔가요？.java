import java.util.Scanner;

/**
 * @author 지종권
 * @date 2024-01-29
 * @link https://www.acmicpc.net/problem/17478
 * @keyword_solution  재귀
 * @input 교수님이 출력을 원하는 재귀 횟수 N(1 ≤ N ≤ 50)이 주어진다.
 * @output 출력 예시를 보고 재귀 횟수에 따른 챗봇의 응답을 출력한다.
 * @time_complex BigO L
 * @perf 
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		
		req(n, "");
	}
	
	static void req(int n, String underBar) {		
		if(n == 0) {
			System.out.println(underBar + "\"재귀함수가 뭔가요?\"");
			System.out.println(underBar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(underBar + "라고 답변하였지.");
			return;
		}
		
		System.out.println(underBar + "\"재귀함수가 뭔가요?\"");
		System.out.println(underBar + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(underBar + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(underBar + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		
		req(n - 1, underBar + "____");
		System.out.println(underBar + "라고 답변하였지.");
	}
}
