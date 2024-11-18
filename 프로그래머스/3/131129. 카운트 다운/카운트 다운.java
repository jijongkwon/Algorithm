import java.util.*;
import java.io.*;

class Solution {    
    public int[] solution(int target) {
        // single, ball 일 때의 경우 추가
        int[][] dp = new int[2][target + 1];
        Arrays.fill(dp[0], 100_000);
        Arrays.fill(dp[1], 100_000);
        
        dp[0][0] = 0;
        dp[1][0] = 0;
        
        for(int i = 0 ; i < target; i++){
            // single
            for(int j = 1; j <= 20; j++){
                if(i + j > target){
                    break;
                }
                
                if(dp[0][i] + 1 < dp[0][i + j]){
                    dp[0][i + j] = dp[0][i] + 1;
                    dp[1][i + j] = dp[1][i] + 1;
                } 
                else if(dp[0][i] + 1 == dp[0][i + j] && dp[1][i] + 1 > dp[1][i + j]){
                    dp[1][i + j] = dp[1][i] + 1;    
                }
            }
            
            // double
            for(int j = 2; j <= 40; j+=2){
                if(i + j > target){
                    break;
                }
                
                if(dp[0][i] + 1 < dp[0][i + j]){
                    dp[0][i + j] = dp[0][i] + 1;
                    dp[1][i + j] = dp[1][i];
                } 
                else if(dp[0][i] + 1 == dp[0][i + j] && dp[1][i] > dp[1][i + j]){
                    dp[1][i + j] = dp[1][i];    
                }
            }
            
            // tripple
            for(int j = 3; j <= 60; j+=3){
                if(i + j > target){
                    break;
                }
                
                if(dp[0][i] + 1 < dp[0][i + j]){
                    dp[0][i + j] = dp[0][i] + 1;
                    dp[1][i + j] = dp[1][i];
                } 
                else if(dp[0][i] + 1 == dp[0][i + j] && dp[1][i] > dp[1][i + j]){
                    dp[1][i + j] = dp[1][i];    
                }
            }
            
            // ball
            if(i + 50 > target){
                continue;
            }
            
            if(dp[0][i] + 1 < dp[0][i + 50]){
                    dp[0][i + 50] = dp[0][i] + 1;
                    dp[1][i + 50] = dp[1][i] + 1;
            } 
            else if(dp[0][i] + 1 == dp[0][i + 50] && dp[1][i] + 1 > dp[1][i + 50]){
                dp[1][i + 50] = dp[1][i] + 1;
            }
        }
        
//         for(int i = 0; i < 2; i++){
//             System.out.println(Arrays.toString(dp[i]));   
//         }
        
        int[] answer = new int[2];
        answer[0] = dp[0][target];
        answer[1] = dp[1][target];
        
        return answer;
    }
}