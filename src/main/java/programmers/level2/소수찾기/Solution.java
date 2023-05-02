package programmers.level2.소수찾기;

import java.util.*;

class Solution {
    static Set<Integer> numberList = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;

        makeCombination("", numbers);

        for(int number : numberList){
            if(checkPrime(number)){
                answer++;
            }
        }
        return answer;
    }

    // 소수 판별
    public boolean checkPrime(int number){

        if (number == 1 || number == 0){
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    // 경우의 수 만들기
    static public void makeCombination(String combination, String other){
        if(!combination.equals("")){
            numberList.add(Integer.parseInt(combination));
        }

        for(int i = 0; i < other.length(); i++){
            makeCombination(combination + other.charAt(i), other.substring(0,i) + other.substring(i+1));
        }
    }
}