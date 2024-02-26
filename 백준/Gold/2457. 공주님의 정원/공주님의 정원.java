import java.util.*;
import java.io.*;

/* 
 *종료일이 5월 25일이라면 ? -> 5월 24일까지 진행 가능
 *
 *4, 6, 9, 11월은 30일까지 있고,
 *1, 3, 5, 7, 8, 10, 12월은 31일까지 있으며,
 *2월은 28일까지 만 있다고 가정한다
 *
 * 조건 1: 3월 1일부터 11월 30일까지 매일 한 가지 이상의 프로젝트 참여
 * 조건 2: 참여하는 프로젝트의 수를 가능한 적게
 * 
 * 그리디 알고리즘
 * 앞 정렬
 * 그 후 가장 긴 프로젝트 선택
 */
public class Main {	
	static final int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		
		// 달, 일 입력
		int[] endDay = new int[366];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int startM = Integer.parseInt(st.nextToken());
			int startD = Integer.parseInt(st.nextToken());
			int endM = Integer.parseInt(st.nextToken());
			int endD = Integer.parseInt(st.nextToken());
			
			int start = getDay(startM, startD);
			int end = getDay(endM, endD);
			endDay[start] = Math.max(endDay[start], end); 
		}
		
		int lastDay = 1;
		int count = 0;
		int day = 60; // 3월 1일부터
		while(day < 335) {
			int maxDay = 0;
			for(int i = lastDay; i <= day; i++) {
				maxDay = Math.max(maxDay, endDay[i]);
			}
			
			if(maxDay == 0) {
				break;
			}
			
			lastDay = day;
			day = maxDay;
			count++;
		}
		
		if(day >= 335) {
			System.out.println(count);
		}
		else {
			System.out.println(0);
		}
	}
	
    static int getDay(int month, int day) {
        int ret = 0;
        for (int i = 1; i < month; i++) {
            ret += days[i];
        }

        return ret + day;
    }
}