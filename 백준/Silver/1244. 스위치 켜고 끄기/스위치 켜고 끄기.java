import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 지종권 
 * @date 2024-01-30
 * @link https://www.acmicpc.net/problem/1244
 * @keyword_solution  
 * 1. 남자는 받은 수의 배수일 때 스위치 상태르 바꿈
 * 2. 여자는 받은 수의 양 옆이 좌우 대칭이면 스위치 상태를 모두 바꿈
 * @input 
 * 첫째 줄에는 스위치 개수가 주어진다. 스위치 개수는 100 이하인 양의 정수이다. 둘째 줄에는 각 스위치의 상태가 주어진다. 켜져 있으면 1, 꺼져있으면 0이라고 표시하고 사이에 빈칸이 하나씩 있다. 셋째 줄에는 학생수가 주어진다. 학생수는 100 이하인 양의 정수이다. 넷째 줄부터 마지막 줄까지 한 줄에 한 학생의 성별, 학생이 받은 수가 주어진다. 남학생은 1로, 여학생은 2로 표시하고, 학생이 받은 수는 스위치 개수 이하인 양의 정수이다. 학생의 성별과 받은 수 사이에 빈칸이 하나씩 있다.
 * @output
 * 스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력한다. 예를 들어 21번 스위치가 있다면 이 스위치의 상태는 둘째 줄 맨 앞에 출력한다. 켜진 스위치는 1, 꺼진 스위치는 0으로 표시하고, 스위치 상태 사이에 빈칸을 하나씩 둔다.
 * @time_complex  
 * @perf 
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] switchs = new int[n + 1];
		
		for(int i = 1 ; i <= n ; i++) {
			switchs[i] = sc.nextInt();
		}
		
		int num = sc.nextInt();
		
		for(int i = 0; i < num; i++) {
			int studentSex = sc.nextInt();
			int studentNumber = sc.nextInt();
			
			if(studentSex == 1) {
				for(int j = studentNumber; j <= n; j += studentNumber) {
					switchs[j] = Math.abs((switchs[j] - 1));
				}
				continue;
			}
			
			int move = 1;
			while(true) {
				if(studentNumber - move <= 0 || studentNumber + move > n) {
					move--;
					break;
				}
				
				if(switchs[studentNumber - move] == switchs[studentNumber + move]) {
					move++;
					continue;
				}
				
				move--;
				break;
			}
			
			if(move == 0) {
				switchs[studentNumber] = Math.abs((switchs[studentNumber] - 1));
				continue;
			}
			
			for(int j = studentNumber - move; j <= studentNumber + move; j++) {
				switchs[j] = Math.abs((switchs[j] - 1));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			if(i % 20 == 0) {
				sb.append(switchs[i] + "\n");
				continue;
			}
			sb.append(switchs[i] + " ");
		}
		
		System.out.println(sb.toString());
	}

}
