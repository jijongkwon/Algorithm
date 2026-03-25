import java.util.*;

/*
    풍선이 1개 남을 때까지 반복
    1. 인접한 두 풍선 중 하나의 풍선 터뜨림
    2. 빈공간 발생시 중앙으로 밀착
    
    조건
    1. 번호가 더 작은 풍선을 터드리는 행위는 최대 1번
    
    return 남아있을 수 있는 풍선의 개수
    
    제한
    1,000,000...
    
    풀이
    1. 왼쪽/오른쪽 a[i] 보다 작은 값은 1개 이하만 있어야 살아남기 가능
    2. 반복문 2번 사용시 시간 초과..
    3. 누적합 이용 (왼쪽, 오른쪽)
*/
class Solution {
    public int solution(int[] a) {
        int n = a.length;
        // 왼쪽 최소
        int[] left = new int[n];
        left[0] = Integer.MAX_VALUE;
        
        for(int i = 1; i < n; i++){
            left[i] = Math.min(left[i - 1], a[i - 1]);
        }
        
        // 오른쪽 최소
        int[] right = new int[n];
        right[n - 1] = Integer.MAX_VALUE;
        for(int i = n - 2; i >= 0; i--){
            right[i] = Math.min(right[i + 1], a[i + 1]);
        }
        
        // 값 비교
        int answer = 0;
        for(int i = 0; i < n; i++){
            if(a[i] <= left[i] || a[i] <= right[i]){
                answer++;
            }    
        }
        
        return answer;
    }
}