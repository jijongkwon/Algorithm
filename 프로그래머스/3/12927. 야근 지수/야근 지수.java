import java.util.*;

/*
    피로도: 야근시작시점에 남의 일의 작업량 ^ 2
    1시간 -> 작업량 1 처리

    풀이
    1. 큰수를 먼저 줄이는게 이득
    2. 따라서 정렬 후 가장 큰 수 -1
    3. 다시 정렬
    
    n * nlogn
    200,000,000,000 * 141
    
    불가
    
    1.pq 힙정렬
    
    return 최소 피로도
*/
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> {
            return o1.compareTo(o2) * -1;
        });
        
        for(int i = 0; i < works.length; i++) {
            queue.offer(works[i]);
        }
        
        while(n > 0) {
            int tmp = queue.poll();
            if(tmp == 0) break;
            tmp--;
            n--;
            queue.offer(tmp);
        }
        
        while(!queue.isEmpty()) {
            int t = queue.poll();
            answer += t * t;
        }
        
        return answer;
    }
}