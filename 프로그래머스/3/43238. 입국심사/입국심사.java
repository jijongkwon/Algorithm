import java.util.*;

/*
 한 심사대에서는 한 명만 심사 가능
 앞에 있는 사람은 빨리 끝나는 심사대로 이동
 목표: 모든 사람이 심사를 받는데 걸리는 시간 최소
 
 시간복잡도
 logN
 
 풀이 
 그리드 + 이분탐색
 
 시간이 빠른 심사대 찾기 (이분탐색)
 
 땡
 
 시간 기준으로 이진탐색
 
*/
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        int max = 0;
        for(int i = 0; i < times.length; i++){
            max = Math.max(max, times[i]);
        }
        
        long left = 1;
        long right = (long) max * n;
        
        while(left <= right){
            long mid = (left + right) / 2;
            
            long count = 0;
            for(int time : times){
                count += mid / time;
                if(count >= n) {
                    break;
                }
            }
            
            if(count >= n){
                answer = mid;
                right = mid - 1;
            } 
            else{
                left = mid + 1;   
            }
        }
        
        return answer;
    }
}