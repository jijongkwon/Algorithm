/*
n 개의 퍼즐
각 퍼즐은 난이도, 소요시간 정해져있음
숙련도에 따라 틀리는 횟수가 변경

난이도 diff
현재 퍼즐 소요 시간 time_cur
이전 퍼즐 소요 시간 time_prev
숙련도 level

조건
int totaltime = 0;

if(diff <= level){
    totaltime += time_cur;
}

int wrongCount = 0;
if(diff > level){
    wrongCount = diff - level;
    totaltime += wrongCount * (time_cur + time_prev)
}

300,000 배열

제한시간안에 모든것을 해결하기 위한 숙련도의 최솟값

-> 그냥 완탐 아님 ?
-> n^2 하면 3초 되나 ?
-> 시간초과 발생
-> 그러면 어떻게 해결해야하나 ?
-> 이분탐색 
-> 조건
-> limit 에 가깝나 ?
*/
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {      
        int answer = 0;
        int start = 1;
        int end = 100_000;
        
        while(start < end){
            int mid = (start + end) / 2;
            long totalTime = findTime(mid, diffs, times);
            long diffTime = limit - totalTime;
            
            
            if(diffTime >= 0){
                answer = mid;
                end = mid;
                continue;
            }
            
            start = mid + 1;
            answer = mid + 1;
        }
        
        return answer;
    }
    
    // 총 시간 구하기
    long findTime(int level, int[] diffs, int[] times){
        long totalTime = 0;
        int prevTime = 0;
        
        for(int i = 0 ; i < diffs.length; i++){
            if(diffs[i] <= level){
                totalTime += times[i];
                prevTime = times[i];
                continue;
            }
            
            int wrongCount = diffs[i] - level;
            
            totalTime += wrongCount * (times[i] + prevTime) + times[i];
            prevTime = times[i];
        }
        // System.out.println("total: " + totalTime + ", limit : " + limit);
        
        return totalTime; 
    }
}