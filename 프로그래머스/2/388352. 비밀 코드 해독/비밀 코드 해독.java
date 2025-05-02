/*
 1 - n 서로 다른 정수 5개가 오름차순을 정렬된 비밀 코드
 m 번 시도 가능
 
 return m 번의 시도 후, 비밀코드로 가능한 정수 조합의 개수
*/
import java.util.*;

class Solution {
    int answer = 0;
    int[] numbers;

    public int solution(int n, int[][] q, int[] ans) {
        numbers = new int[n];
        for(int i = 1 ; i <= n; i++){
            numbers[i - 1] = i;
        }
        
        comb(0, new ArrayList<>(), q, ans);
        
        return answer;
    }
    
    // 조합
    public void comb(int index, List<Integer> combList, int[][] q, int[] ans){
        
        // 종료조건
        if(combList.size() == 5){
            // 답 비교
            for(int i = 0; i < q.length; i++){
                int count = 0;
                
                for(int j = 0; j < q[i].length; j++){
                    if(combList.contains(q[i][j])){
                        count++;
                    }
                }
                
                if(count != ans[i]){
                    return;
                }
            }
            
            answer++;
            return;    
        }
        
        // 조합
        for(int i = index; i < numbers.length; i++){
            combList.add(numbers[i]);
            comb(i + 1, combList, q, ans); 
            combList.remove(combList.size() - 1);
        }
    }
}