import java.util.*;

/*
    그냥 우선순위 큐 (pq)
    
    [우선순위]
    1. 작업 소요시간이 짧은 것
    2. 작업 요청 시각이 빠른 것
    3. 작업의 번호가 작은 것
    
    작업은 안 멈춤
    
    return 작업에 대한 반환시간 평균시간
*/
class Solution {
    PriorityQueue<Job> pq;
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        pq = new PriorityQueue<>((o1, o2) -> {
            // 소요시간 짧은 거
            if(o1.executeTime != o2.executeTime){
                return Integer.compare(o1.executeTime, o2.executeTime);
            }
            
            // 작업 요청시간
            else if(o1.startTime != o2.startTime){
                return Integer.compare(o1.startTime, o2.startTime);
            }
            
            // 번호
            return Integer.compare(o1.id, o2.id);
        });
        
        
        int time = 0;
        int idx = 0;
        int totalWait = 0;
        int count = 0;

        while (count < jobs.length) {
            // 현재 시각 이전에 요청된 작업만 PQ에 추가
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.add(new Job(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            if (pq.isEmpty()) {
                time = jobs[idx][0];
                continue;
            }

            Job job = pq.poll();
            time += job.executeTime;
            totalWait += time - job.startTime;
            count++;
        }
        
        return totalWait / jobs.length;
    }
    
    class Job{
        int id;
        int startTime;
        int executeTime;
        
        Job(int id, int startTime, int executeTime){
            this.id = id;
            this.startTime = startTime;
            this.executeTime = executeTime;
        }
    }
}