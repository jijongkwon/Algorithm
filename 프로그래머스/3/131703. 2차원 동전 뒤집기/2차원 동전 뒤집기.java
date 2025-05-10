/*
  풀이
  행 -> 열 순서로 뒤집기
  열 -> 행 순서로 뒤집기
  
  목표 값이 있다면 둘 중 최솟값 출력
  없으면 -1
*/
class Solution {
    int[][] map;
    int n, m;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        
        map = new int[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = beginning[i][j];
            }
        }

        dfs(0,0,target);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    // 탐색
    public void dfs(int r, int cnt, int[][] target){
        //모든 행의 경우의 수를 확인했다면
        if(r==n){
            for(int i=0; i<m; i++){
                int result = checkTarget(i, target);
                
                if(result == -1){
                    return;
                }
                
                cnt += result; 
            }
            
            answer = Math.min(answer, cnt); 
            return;
        }
        
        changeRow(r);
        dfs(r + 1, cnt + 1, target); 
        changeRow(r); 
        dfs(r + 1, cnt, target); 
    }
    
    // 비교 확인
    public int checkTarget(int c, int[][] target){
        int check = 0;
        
        for(int i = 0 ; i < n; i++){
            if(map[i][c] == target[i][c]){
                check++;
            }
        }
        
        if(check == 0) return 1;
        if(check == n) return 0;
        return -1;
    }
    
    // 행 뒤집기
    public void changeRow(int x){
        for(int i = 0; i < m; i++){
            map[x][i] = (map[x][i] + 1) % 2;
        }
    }
}