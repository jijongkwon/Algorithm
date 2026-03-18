import java.util.*;

/*
    앞뒤가 똑같은 문자열
    가장긴 문자열
    
    2,500자
    
    dp..?
    
*/
class Solution
{
    public int solution(String s)
    {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int answer = 1;
        
        // 1
        for(int i = 0; i < n; i++){
            dp[i][i] = true;
        }
        
        // 2
        for(int i = 0; i < n - 1; i++){
            if(s.charAt(i) == s.charAt(i + 1)){
                dp[i][i + 1] = true;
                answer = 2;
            }
        }
        
        // 3
        for(int len = 3; len <= n; len++){
            for(int i = 0; i <= n - len; i++){
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]){
                    dp[i][j] = true;
                    answer = Math.max(answer, len);
                }
            }
        }

        return answer;
    }
}