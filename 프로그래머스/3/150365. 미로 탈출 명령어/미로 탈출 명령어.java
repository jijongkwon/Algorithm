/*
풀이
사전순이라면
l 왼쪽
r 오른쪽
u 위쪽
d 아래쪽

d - l - r - u 순으로 진행

그리고 dfs 돌림

방문처리 해도 됨 그냥 도착해서 k % 2 인지 확인

이미 도착했는데 k 가 아니면 남은 k % 2 == 0 라면

기본
du

아래가 막혀 있으면
lr

왼쪽이 막혀 있으면
rl

k가 홀수면 impossible

*/
import java.util.*;

class Solution {
    String answer = "";
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    String[] dir = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int count = k - (Math.abs(x - r) + Math.abs(y - c));
        
        if(count % 2 != 0 || count < 0){
            return "impossible";
        }
        
        dfs(n, m, x - 1, y - 1, r - 1, c - 1, k, "");
        
        return answer != "" ? answer : "impossible";
    }
    
    void dfs(int n, int m, int x, int y, int r, int c, int k, String str){
        // 정답 찾음 
        if(answer != ""){
            return;
        }
        
        // 정답
        if(x == r && y == c && str.length() == k){
            answer = str;
            return;
        }
        
        // 예외
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if(k - str.length() < dist){
            return;
        }
        
        // 찾기
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                continue;
            }
            
            dfs(n, m, nx, ny, r, c, k, str + dir[i]);
        }
    }
}