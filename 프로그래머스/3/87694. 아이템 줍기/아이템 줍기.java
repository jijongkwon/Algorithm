/*
  풀이
  맵 2배 확장
  
  bfs를 진행한다
  1 이거나 2일 때 이동한다
  다음 이동하는 좌표가 1인 경우 상하좌우를 탐색했을 때 값이 0인 공간이 나와야 한다.
  1로 항상 이동하는데 더 이상 이동할 공간이 없는 경우 2로 이동한다.
*/
import java.util.*;

class Solution {
    int[][] map = new int[101][101];
    boolean[][] visited = new boolean[101][101];
    
    int[] dx = {0,0,-1,1};
    int[] dy = {1,-1,0,0};
    
    int answer = 0;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        fillRectangle(rectangle);
        removeRectangle(rectangle);
        
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return answer / 2;
    }
    
    // 최소값 찾기
    public void bfs(int characterX, int characterY, int itemX, int itemY){
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person(characterX, characterY, 0));
        visited[characterX][characterY] = true;
        
        while(!queue.isEmpty()){
            Person p = queue.poll();
            
            // 아이템 만남 종료
            if(p.x == itemX && p.y == itemY){
                answer = p.count;
                return;
            }
            
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= 101 || ny >= 101){
                    continue;
                }
                
                if(visited[nx][ny]){
                    continue;
                }
                
                if(map[nx][ny] == 0){
                    continue;
                }
                
                visited[nx][ny] = true;
                queue.add(new Person(nx,ny,p.count + 1));
            }
        }
    }
    
    // 도형 채우기
    public void fillRectangle(int[][] rectangle){
        for(int[] rect : rectangle){
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 1;
                }
            }
        }
    }
    
    // 내부 지우기
    public void removeRectangle(int[][] rectangle){
        for(int[] rect : rectangle){
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    map[i][j] = 0;
                }
            }
        }
    }
    

    
    class Person{
        int x;
        int y;
        int count;
        
        Person(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}