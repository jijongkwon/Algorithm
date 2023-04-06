package programmers.level2.롤케이크자르기;

import java.util.*;

class Solution {
    /*
        문제 : 공평하게 자르는 방법의 수
        1. 철수가 먼저 토핑을 다 가지고 있고, 개수가 같은지 확인하기 위해 동생이 뺐는다.
        2. 철수와 동생 토핑종류 개수가 똑같은지 확인
    */
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer,Integer> oldBro = new HashMap<>();
        HashMap<Integer,Integer> youngBro = new HashMap<>();

        // 철수 토핑 추가
        for(int i = 0; i < topping.length;i++){
            oldBro.put(topping[i], oldBro.getOrDefault(topping[i], 0) + 1);
        }

        // 동생 토핑 추가
        for(int i = 0; i < topping.length; i++){
            youngBro.put(topping[i], 1);
            oldBro.put(topping[i], oldBro.get(topping[i]) - 1);

            if(oldBro.get(topping[i]) == 0){
                oldBro.remove(topping[i]);
            }

            // 개수 비교
            if(oldBro.size() == youngBro.size()){
                answer++;
            }
        }

        return answer;
    }
}