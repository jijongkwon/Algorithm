import java.util.*;

/*
    return 최소 카메라 설치 개수
    
    -20  ~  -15
         -18  ~ -13
               -14 ~ -5
                     -5 ~ -3
                 
    1. 일단 정렬 ( 뒤에 거 정렬)
    2. 겹치면 통과, 안 겹치면 + 1
    3. 끝점만 체크
*/
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (int[] o1, int[] o2) -> {
            return o1[1] - o2[1];
        });
        
        int cur = Integer.MIN_VALUE;
        int answer = 0;
        
        for(int i = 0 ; i < routes.length; i++){
            if(cur < routes[i][0]){
                answer++;
                cur = routes[i][1];
            }
        }
        
        return answer;
    }
}