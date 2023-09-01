package programmers.level2.귤고르기;

import java.util.*;

/*
    solution: 중복이 가장 적은 귤 개수 제거
    BigO : nlog 이하
    return : 크기가 서로 다른 종류의 수 최솟값
*/
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Arrays.sort(tangerine);

        List<Integer> counts = new ArrayList<>();

        int count = 1;

        for(int i = 0; i < tangerine.length - 1; i++){
            int current = tangerine[i];
            int next = tangerine[i + 1];

            if(current != next){
                counts.add(count);
                count = 1;
                continue;
            }

            count++;
        }

        counts.add(count);

        int elimination = tangerine.length - k;

        Collections.sort(counts);

        while(elimination != 0){
            int size = counts.get(0);

            if(elimination >= size){
                elimination -= size;
                counts.remove(0);
                continue;
            }
            break;
        }

        answer = counts.size();
        return answer;
    }
}