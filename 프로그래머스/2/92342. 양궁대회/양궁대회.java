import java.util.*;

/*
어파치 n발 소진 후, 라이언 n발 발사
과녁은 0 ~ 10
k점은 많이 맞춘사람이 점수를 가져감, 동일 시 어파치가 가져감
점수 동일시 어파치 승

어파치 정보가 주어짐
가장 큰 점수차이로 이기려면 n발의 화살을 어떤 과녁에 맞춰야하나

우승 못하는 경우 -1

시간복잡도.. 고려안해도 됨

풀이
완탐.. 재귀
*/
class Solution {
    int max = 0;
    int[] answer = {-1};
    
    public int[] solution(int n, int[] info) {
        recur(0,n,info, new int[11]);
        return answer;   
    }
    
    // 순열
    public void recur(int depth, int arrows, int[] apachScores, int[] lyanScores){
        // 종료조건
        if(depth == 11){
            lyanScores[10] = arrows;
            
            int diff = diffScore(apachScores, lyanScores);
            
            // System.out.println(diff);
            if(diff > 0){
                if(diff > max){
                    max = diff;
                    answer = lyanScores.clone();
                }
                else if(diff == max){
                    if(isLowerScoreMore(lyanScores)){
                        answer = lyanScores.clone();
                    }
                }    
            }
            
            lyanScores[10] = 0;
            return;
        }
        
        // 쏘는 경우
        int neededArrows = apachScores[depth] + 1;
        
        if(arrows >= neededArrows){
            lyanScores[depth] = neededArrows;
            recur(depth + 1, arrows - neededArrows, apachScores, lyanScores);   
            lyanScores[depth] = 0;
        }
        
        // 안쏘는 경우
        recur(depth + 1, arrows, apachScores, lyanScores);   
    }
    
    // 점수차 계산
    public int diffScore(int[] apachScores, int[] lyanScores){
        int apachTotal = 0;
        int lyanTotal = 0;
        
        for(int i = 0; i < 11; i++){
            int apachScore = apachScores[i];
            int lyanScore = lyanScores[i];
            
            if(apachScore == 0 && lyanScore == 0){
                continue;
            }
            
            if(apachScore < lyanScore){
                lyanTotal += (10 - i);
            }
            else{
                apachTotal += (10 - i);
            }
        }
        
        return lyanTotal - apachTotal;
    }
    
    // 더 낮은 점수 맞췄는지 확인
    public boolean isLowerScoreMore(int[] ryanScores){
        for(int i = 10; i >= 0; i--){
            if(ryanScores[i] > answer[i]){
                return true;  
            } 
            
            if(ryanScores[i] < answer[i]){
                return false;
            }
        }
        
        return false;
    }
}