package programmers.level2.k진수에서소수개수구하기;

import java.util.*;

/*
    solution
    1. 정수 n을 k진수로 변환
    2. 조건에 맞는 숫자 찾기
    3. 그 수가 소수인지 판별
    4. 개수 리턴


    bigO: nlog
    return: 소수의 개수
*/
class Solution {
    public int solution(int n, int k) {
        String binary = Integer.toString(n, k);

        return findPrimeInConstarin(binary);
    }

    // 소수판별
    public boolean isPrime(Long number){
        if(number == 0){
            return false;
        }

        if(number == 1){
            return false;
        }

        for(int i = 2 ; i <= Math.sqrt(number); i++){
            if(number % i == 0){
                return false;
            }
        }

        return true;
    }

    // 조건탐지
    public int findPrimeInConstarin(String binary){
        int count = 0;
        int start = 0;

        for(int i = 0; i < binary.length(); i++){
            if(binary.charAt(i) != '0'){
                continue;
            }

            Long number = Long.parseLong(binary.substring(start, i));
            start = i;

            if(!isPrime(number)){
                continue;
            }

            count++;
        }

        // 마지막 문자 소수 판별
        if(start != binary.length() - 1){
            Long number = Long.parseLong(binary.substring(start, binary.length()));

            if(isPrime(number)){
                count++;
            }
        }

        return count;
    }
}