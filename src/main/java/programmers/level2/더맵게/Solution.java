package programmers.level2.더맵게;

import java.util.*;

/*
    1. 정렬 필요
    2. 자료구조 이용 (우선수위 큐)
*/
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for(int i = 0; i < scoville.length; i++){
            heap.add(scoville[i]);
        }

        // 최솟값 초기화
        int minValue = heap.peek();

        // 작은 거 꺼내기
        while(minValue < K){
            // k 이상으로 만들 수 없을 때
            if(heap.size() < 2){
                return -1;
            }

            int low = heap.poll();
            int nextLow = heap.poll();

            heap.add(culScoville(low, nextLow));
            minValue = heap.peek();
            answer++;
        }


        return answer;
    }

    // 스코빌 계산
    public int culScoville(int low, int nextLow){
        return low + (nextLow * 2);
    }
}
