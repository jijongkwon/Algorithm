import java.util.*;

/*
    디딤돌 밟을 수 있는 횟수 정해짐
    갈 수 있는 경우의 수가 여러개인 경우 가장 가까운 디딤돌로만 이동 가능
    
    한 번에 한명씩
    
    return 최대 건넌 인원수
    
    제한
    1 ~ 200,000
    원소값 1 ~ 2억
    -> big0 n
    
    풀이
    1. 일단 무조건 가까운 곳으로 이동..
    -> 전체 -1 시켜보기
    2. 돌 사이의 거리가 k 이상인지 보기
    -> 연속된 0이 k이상인지..
    
    불가 20만 * 2억 ?
    절대 불가
    
    1. 돌을 밟을 수 있는 수 = x 건너는 사람 수
    2. 돌 - x 는 가능한 횟수
    3. 해당 부분이 k 이상이면 불가
    4. 건너는 사람 수를 기준으로 이분탐색하여 최대값 찾기 (20만 * log(2억))
*/
class Solution {
    public int solution(int[] stones, int k) {
        int start = 0;
        int end = 200_000_000;
        int mid = 0;
        
        while(start < end){
            mid = (start + end) / 2;
            if(isGo(stones, k, mid)){
                start = mid + 1;
            }
            else{
                end = mid;
            }
        }
       
        return end;
    }
    
    boolean isGo(int[] stones, int k, int m){
        int count = 0;
        
        for(int stone : stones){
            if(stone - m <= 0){
                count++;
            }
            else{
                count = 0;
            }
            
            if(count >= k){
                return false;
            }
        }
        
        return true;
    }
}