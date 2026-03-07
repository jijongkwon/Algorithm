import java.util.*;

/*
    번갈아가며 나오는 수열
    
    return 연속 펄스 부분 수열의 합 중 가장 큰 것
    
    제한
    500,000
    200,000
    
    dp..?
    
    풀이
    1. -1, 1 배열, 1, -1 배열 각각 dp
    2. max
    
*/
class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        long dp1 = 0, dp2 = 0;
        
        for (int i = 0; i < sequence.length; i++) {
            long pulse = (i % 2 == 0) ? 1 : -1;
            
            long val1 = sequence[i] * pulse;   
            long val2 = sequence[i] * (-pulse);
            
            dp1 = Math.max(val1, dp1 + val1);
            dp2 = Math.max(val2, dp2 + val2);
            
            answer = Math.max(answer, Math.max(dp1, dp2));
        }
        
        return answer;
    }
}