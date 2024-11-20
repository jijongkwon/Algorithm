/*
목표 : 특정 시간 동안 알림이 울린 횟수

초침이 시침 or 분침과 겹칠때 마다 알람이 울림
- 둘 다 겹친다면 한 번으로 체크

-> 정각 이외는 정확히 일치할 수 없음
즉 3분이라고 했을 때, 3초 보다 커야함

풀이

시간은 12시간에 360 -> 1시간에 30 -> 1분에 1/2 -> 1초 -> 1/120
분은 60분 360 -> 1분 6-> 1초 1/10
초 60초 360 -> 1초 6

-> 각도 계산이 가능하다
h_angle = (sec_time / 120) % 360;
m_angle = (sec_time / 10) % 360;
s_angle = (sec_time * 6 ) % 360;

*/
import java.util.*;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer =0;
        
        // 시간 -> 초
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        // 00시 12시
        if(startTime % 360 == 0 || startTime % 360 == 12){
            answer++;
        }
        
        while(startTime < endTime){
            
            // 현재 시각의 각도
            double hAngle = (startTime / (double)120) % 360;
            double mAngle = (startTime / (double)10) % 360;
            double sAngle = (startTime * 6 ) % 360;
            
            // 다음 시각의 각도
            double nhAngle = ((startTime + 1) / (double)120) % 360;
            double nmAngle = ((startTime + 1) / (double)10) % 360;
            double nsAngle = ((startTime + 1) * 6 ) % 360;
            
            if(nhAngle == 0){
                nhAngle = 360;
            }
            
            if(nmAngle == 0){
                nmAngle = 360;
            }
            
            if(nsAngle == 0){
                nsAngle = 360;
            }
            
            // 현재 초침이 현재 분침보다 작고, 다음 초침이 다음 분침보다 각도가 크거나 같을 때
            if(sAngle < mAngle && nsAngle >= nmAngle){
                answer++;
            }
            
            // 현재 초침이 현재 시침보다 작고, 다음 초침이 다음 시침보다 각도가 크거나 같을 때
            if(sAngle < hAngle && nsAngle >= nhAngle){
                answer++;
            }
            
            // 시침 분침이 겹치면 하나 빼기
            if(nmAngle == nhAngle){
                answer--;
            }
            
            startTime++;
        }
        
        return answer;
    }
}