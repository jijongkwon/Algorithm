import java.util.*;

/*
    한 번에 한개의 알파벳 교환 가능
    words에 있는 단어로만 변환
    
    그냥 재귀
*/
class Solution {
    int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        findCntForChng(0, begin, target, words, new boolean[words.length]);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    void findCntForChng(int depth, String cur, String target, String[] words, boolean[] visited){
        System.out.println("depth: " + depth + ", cur: " + cur);
        // 종료조건
        if(depth > visited.length){
            return;
        }
        
        if(cur.equals(target)){
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int i = 0 ; i < words.length; i++){
            if(visited[i]){
                continue;
            }
            
            if(!isChng(cur, words[i])){
                continue;
            }
            visited[i] = true;
            findCntForChng(depth + 1, words[i], target, words, visited);
            visited[i] = false;
        }
    }
    
    boolean isChng(String cur, String word){
        int totalLen = cur.length();
        int count = 0;
        for(int i = 0; i < totalLen; i++){
            if(cur.charAt(i) == word.charAt(i)){
                count++;
            }    
        }
        
        return totalLen - count == 1 ? true : false;
    }
}