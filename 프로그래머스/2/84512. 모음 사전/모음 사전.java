import java.util.*;

/*
    모든 단어 만들고 번호 찾기
*/
class Solution {
    String[] words = {"A", "E", "I", "O", "U"};
    List<String> makedWords = new ArrayList<>();
        
    public int solution(String word) {
        makeWord(0,"");
        
        for(int i = 0; i < makedWords.size(); i++){
            if(makedWords.get(i).equals(word)){
                return i;
            }
        }
        return -1; 
    }
    
    void makeWord(int depth, String makedWord){
        makedWords.add(makedWord);
        
        // 종료조건
        if(depth == 5) {
            return;
        }
        
        for(int i = 0 ; i < 5; i++) {
            makeWord(depth + 1, makedWord + words[i]);
        }
    }
}