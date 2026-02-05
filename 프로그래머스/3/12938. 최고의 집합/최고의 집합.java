import java.util.*;

/* 
    최고중복집합
    1. 각 원소 합 S
    2. 1번조건 만족하면서 원소 곱이 최대가 되는 집합
    
    풀이
    1. 어차피 중간지점이 가장 원소 곱이 클 거다.
    2. s 를 n 만큼 공평하게 나눈다
    3. 나머지 값을 적절히 분배한다.
    
*/
class Solution {
    public int[] solution(int n, int s) {
        if(n > s){
            return new int[] {-1};
        }
        
        int divValue = s / n;
        int modValue = s % n;
        
        int[] answer = new int[n];
        
        for(int i = 0 ; i < n; i++){
            answer[i] = divValue;
        }
        
        int idx = n - 1;
        while(modValue > 0){
            answer[idx--]++;
            modValue--;
        }
        
        return answer;
    }
}