import java.util.*;

/*
    특정 범위의 보석 모두 구매 (모든 보석의 종류를 적어도 1개 이상 구매)
    짧은 구간 (길이 최소)
    
    효율성 테스트..
    슬라이딩 윈도우
*/
class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;
        
        // 중복제거
        Set<String> set = new HashSet<>();
        for(String gem : gems){
            set.add(gem);
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        int gemCnt = set.size();
        int minRange = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int startIdx = 0;
        
        while(end < n){
            
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            end++;
            
            // 슬라이딩 윈도우
            while(map.size() == gemCnt){
                if(end - start < minRange){
                    minRange = end - start;
                    startIdx = start;
                }
                
                // 윈도우 이동
                map.put(gems[start], map.get(gems[start]) - 1);
                
                if(map.get(gems[start]) == 0){
                    map.remove(gems[start]);
                }
                start++;
            }
        }
        return new int[] {startIdx + 1, startIdx + minRange};
    }
}