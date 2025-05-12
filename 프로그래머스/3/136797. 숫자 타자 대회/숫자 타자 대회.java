/*
  긴 문자열 빠르게 입력하기 대회
  
  초기위치
  4, 6
  
  가중치
  이동하지 않고 제자리에서 누르기 1
  상하좌우 인접한 숫자로 이동하여 누르는 것 2
  대각선 인접한 숫자로 이동하여 누르는 것 3
  그 외는 가중치합
  
  return 최소시간 타이핑
  
  조건
  시간복잡도 nlogN
  
  풀이
  1. 가중치 구하기
  2. dp
  
*/
import java.util.*;

class Solution {
    int[][][] dp;
    char[] nums;
    int len;
    
    public int solution(String numbers) {
        nums = numbers.toCharArray();
        
        // dp 초기화
        len = numbers.length();
        dp = new int[len][10][10];
        for(int i = 0; i < len; i++){
            for(int j = 0; j < 10; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
    
        return memo(0,4,6);
    }
    
    // dp
    public int memo(int index, int left, int right){
        // 끝까지 다 입력
        if(index == len){
            return 0;
        }
        
        // 이미 기억된 최솟값이라면
        if(dp[index][left][right] != Integer.MAX_VALUE){
            return dp[index][left][right];
        }
        
        // 그 외 최솟값 구하기
        int number = nums[index] - '0';
        int result = Integer.MAX_VALUE;
        
        // 왼손 입력
        if(right != number){
            result = Math.min(result, memo(index + 1, number, right) + calValue(left, number));
        }
        
        // 오른손 입력
        if(left != number){
            result = Math.min(result, memo(index + 1, left, number) + calValue(right, number));
        }
        
        dp[index][left][right] = result;
        return result;
    }

    // 가중치 구하기
    int[][] pads = {
        {1,2,3},
        {4,5,6},
        {7,8,9},
        {-1,0,-1}
    };
    
    public int calValue(int s, int e){
        // 같은 위치인 경우
        if(s == e){
            return 1;
        }
        
        int[] startLoc = null;
        int[] endLoc = null;
        
        // 좌표를 찾기
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(pads[i][j] == s){
                    startLoc = new int[]{i, j};
                }
                
                if(pads[i][j] == e){
                    endLoc = new int[]{i, j};
                }
            }
        }
        
        // 가로 세로를 계산
        int width = Math.abs(startLoc[1] - endLoc[1]);
        int heigth = Math.abs(startLoc[0] - endLoc[0]);
        
        // 대각선 이동후, 남은 상하좌우 이동
        return Math.min(width, heigth) * 3 + Math.abs(width - heigth) * 2;
    }
}