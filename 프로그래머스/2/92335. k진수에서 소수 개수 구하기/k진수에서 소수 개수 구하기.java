/*
1. 진수변환 10 -> k
2. "0" 기준으로 나눔
4. size만큼 반복문
5. 소수 판별
6. 개수 리턴
*/
import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String changedNumber = change(k, n, "");        
        String[] numbers = changedNumber.split("0");
        System.out.println(Arrays.toString(numbers));
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i].equals("")){
                continue;
            }
            
            long number = Long.parseLong(numbers[i]);
            if(isPrimeNumber(number)){
                answer++;
            }
        }
        
        return answer;
    }
    
    // 진수변환 10 -> k (재귀)
    String change(int base, int number, String data){
        data = String.valueOf(number % base) + data;
        
        // 종료조건
        if(number / base == 0){
            return data;
        }
        
        return change(base, number / base, data);
    }
    
    // 소수판별
    private boolean isPrimeNumber(long targetNumber) {
        if(targetNumber < 2) {
            return false;
        }
        
        for(int i = 2 ; i <= Math.sqrt(targetNumber); i++){
            if(targetNumber % i == 0 ) {
                return false;
            }
        }
        return true;
    }
}