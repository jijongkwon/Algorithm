package programmers.level1.실패율;

import java.util.*;

public class Solution {
    /*
        1. 실패율 계산
        2. 정렬 후 스테이지 출력 (더블 리스트 활용)
    */
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        List<List<Double>> missRate = new ArrayList<>();
        int remainPeople = stages.length;

        for(int i = 1; i < N + 1; i++){
            int passPeople = 0;

            // 통과한 사람 수 세기
            for(int j = 0; j < stages.length; j++){
                if(i == stages[j]){
                    passPeople++;
                }
            }

            // 스테이지와 실패율 넣기
            missRate.add(Arrays.asList((double) i, (double) passPeople / remainPeople));
            // 통과한 사람 빼기
            remainPeople -= passPeople;
        }

        // 정렬
        for(int i = 0; i < missRate.size(); i++){
            for(int j = i + 1; j < missRate.size(); j++){
                List<Double> temp = missRate.get(j);
                if(missRate.get(i).get(1) < missRate.get(j).get(1)){
                    missRate.set(j,missRate.get(i));
                    missRate.set(i,temp);
                }

                // 작은 번호 스테이지가 먼저 오도록 정렬

                if(Double.compare(missRate.get(i).get(1), missRate.get(j).get(1)) == 0){
                    if(missRate.get(i).get(0) > missRate.get(j).get(0)){
                        temp = missRate.get(i);
                        missRate.set(i, missRate.get(j));
                        missRate.set(j, temp);
                    }
                }
            }
        }

        for(int i = 0; i < missRate.size(); i++){
            answer[i] = (int) Math.round(missRate.get(i).get(0));
        }

        return answer;
    }
}
