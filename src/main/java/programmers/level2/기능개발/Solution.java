package programmers.level2.기능개발;

import java.util.*;

/*
    1. 순서대로 배포 또한 동시에 배포 가능(앞이 끝나야 뒤를 배포할 수 있음)
    2. 배포가 끝난 서비스는 제거 (일수는 상관 없음)
*/
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        int count = 0;
        int index = 0;

        int[] addSpeeds = new int[speeds.length];

        for(int i = 0; i < speeds.length; i++){
            addSpeeds[i] = speeds[i];
        }

        List<Integer> countList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < progresses.length; i++){
            queue.add(progresses[i]);
        }

        // 100% 구현까지 돌림
        while(!queue.isEmpty()){

            // 진행률 증가
            for(int i = 0; i < speeds.length;i++){
                addSpeeds[i] = addSpeeds[i] + speeds[i] ;
            }

            // 100%라면 큐에서 빼기
            if(queue.peek() + addSpeeds[index] >= 100){
                while(!queue.isEmpty() && queue.peek() + addSpeeds[index] >= 100){
                    queue.remove();
                    count++;
                    index++;
                }

                countList.add(count);
                count = 0;
            }
        }

        answer = new int[countList.size()];

        for(int i = 0; i < countList.size(); i++){
            answer[i] = countList.get(i);
        }

        return answer;
    }
}