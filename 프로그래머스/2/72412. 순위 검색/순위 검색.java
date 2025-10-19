import java.util.*;

/*
    일치하는 거 찾기
    효율성 테스트
    쿼리? -> 인덱싱 -> hash
    모든 정보의 모든 경우에 대한 인덱싱 만들기
*/
class Solution {
    static HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for(int i = 0 ; i < info.length; i++){
            String[] spiltedInfo = info[i].split(" ");
            indexingInfo(0,"",spiltedInfo);
        }
        
        // 이진탐색용 정렬
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        
        for(int i = 0 ; i < query.length; i++){
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");
            answer[i] = map.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
        }
        
        return answer;
    }
    // 이진탐색
    int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        
        int start = 0;
        int end = list.size() - 1;
 
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }
 
        return list.size() - start;
    }
    
    // indexing
    void indexingInfo(int depth, String sentence, String[] spiltedInfo){
        // 종료조건
        if(depth == 4){
            if (!map.containsKey(sentence)) {
                List<Integer> list = new ArrayList<Integer>();
                map.put(sentence, list);
            }
            
            map.get(sentence).add(Integer.parseInt(spiltedInfo[4]));
            return;
        }
        
        indexingInfo(depth + 1, sentence + "-", spiltedInfo);
        indexingInfo(depth + 1, sentence + spiltedInfo[depth], spiltedInfo);
    }
}