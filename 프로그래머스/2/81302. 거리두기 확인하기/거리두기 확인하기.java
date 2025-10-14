import java.util.*;

/*
    p 주위 O or X
    O 주위 P 2개 이상 불가
    X 신경 X
*/
class Solution {
    int[] dx = {0,0,-1,1};
    int[] dy = {1,-1,0,0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i = 0; i < 5; i++){
            answer[i] = isDistanced(places[i]) ? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean isDistanced(String[] place){
        // 1. P 주위에 P가 있는지 체크
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                if(place[r].charAt(c) == 'P'){
                    
                    for(int i = 0; i < 4; i++){
                        int nr = r + dx[i];
                        int nc = c + dy[i];
                        
                        if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5){
                            continue;
                        }
                        
                        if(place[nr].charAt(nc) == 'P'){
                            return false;
                        }
                    }
                }
            }
        }
        
        // 2. O 주위에 P가 2개 이상 있는지 체크
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                if(place[r].charAt(c) == 'O'){
                    int count = 0;
                    
                    for(int i = 0; i < 4; i++){
                        int nr = r + dx[i];
                        int nc = c + dy[i];
                        
                        if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5){
                            continue;
                        }
                        
                        if(place[nr].charAt(nc) == 'P'){
                            count++;
                        }
                    }
                    
                    if(count >= 2){
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}