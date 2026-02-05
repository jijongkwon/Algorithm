import java.util.*;

/*
    최소 설치할 기지국 개수
    
    n : 2억..
    
    풀이
    1.일단 기존 기지국 먼저 신호 전파
    2. W 만큼 앞에가서 설치
    
    끝?
*/
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int cur = 1;
        int sIdx = 0;
        
        while(cur <= n){
            if(sIdx < stations.length && stations[sIdx] - w <= cur){
                cur = stations[sIdx] + w + 1;
                sIdx++;
                continue;
            }
            
            cur += 2 * w + 1;    
            answer++;
        }

        return answer;
    }
}