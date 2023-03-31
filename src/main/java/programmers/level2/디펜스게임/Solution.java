package programmers.level2.디펜스게임;

import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        int remainSoldier = n;
        int remainDefence = k;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < enemy.length; i++){
            // 남은 병사가 있는 경우
            remainSoldier -= enemy[i];
            maxHeap.add(enemy[i]);

            // 남은 병사가 없는 경우
            if(remainSoldier < 0){
                if(!maxHeap.isEmpty() && remainDefence > 0){
                    remainSoldier += maxHeap.poll();
                    remainDefence--;
                }
                else{
                    return i;
                }
            }
        }

        return answer;
    }
}
