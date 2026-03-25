import java.util.*;

/*
    09:00부터 총 n회 t분 간격으로 역도착
    최대 m 탑승 가능
    
    return 가장 늦은 도착시간
    
    제한
    1. 콘은 맨 뒤에 섬
    2. 모든 크루는 23:59에 집에 돌아간다.
    
    풀이..
    1. 우선 정렬
      - ":" 로 나누고 h * 60 + m 해서 int 배열에 정렬
      
    2. n for문
      - t, m 고려하여 인원 태우기
      - 마지막 for문의 경우 콘 태우기 (꽉 찬 경우 -1)
      
*/
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 분으로 변환
        int[] times = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            times[i] = toMin(timetable[i]);
        }
        Arrays.sort(times);

        int idx = 0;
        int cnt = 0;
        int lastPerson = -1;

        for (int i = 0; i < n; i++) {
            int busTime = 540 + t * i;
            cnt = 0;

            while (cnt < m && idx < times.length && times[idx] <= busTime) {
                lastPerson = times[idx];
                idx++;
                cnt++;
            }
        }

        int answer = 0;
        if (cnt < m) {
            answer = 540 + t * (n - 1); // 자리 있으면 마지막 셔틀 시간
        } else {
            answer = lastPerson - 1; // 꽉 찼으면 마지막 탑승자 -1분
        }

        return toTime(answer);
    }

    int toMin(String time) {
        String[] s = time.split(":");
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }

    String toTime(int min) {
        return String.format("%02d:%02d", min / 60, min % 60);
    }
}