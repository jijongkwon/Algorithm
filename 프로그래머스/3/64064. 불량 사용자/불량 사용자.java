import java.util.*;

/*
    조합
    
    순서 관계없이 .. 동일하다면 같은 것으로 처리하여 하나로 셈 ==> 중복제거
    -> Set..
*/
class Solution {
    Set<Set<String>> resultSet = new HashSet<>();
    String[] userId;
    String[] bannedId;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.userId = user_id;
        this.bannedId = banned_id;
        
        dfs(0, new HashSet<>());
        
        return resultSet.size();
    }
    
    void dfs(int depth, Set<String> banned){
        if(depth == bannedId.length) {
            resultSet.add(new HashSet<>(banned));
            return;
        }
        
        for(int i = 0; i < userId.length; i++) {
            if(banned.contains(userId[i])) {
                continue;
            }
            
            if(isSame(userId[i], bannedId[depth])) {
                banned.add(userId[i]);
                dfs(depth + 1, banned);
                banned.remove(userId[i]);
            }
        }
    }
    
    // a: user_id, b: banner_id
    boolean isSame(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        
        for(int i = 0; i < a.length(); i++){
            if(b.charAt(i) == '*'){
                continue;
            }
            
            if(b.charAt(i) != a.charAt(i)){
                return false;
            }
        }
        
        return true;
    }
}