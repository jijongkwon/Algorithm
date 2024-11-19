import java.util.*;
/*
풀이
1. 최단거리 정하기 (위 아래 이동 후, 좌 우 이동)
2. queue를 통해 빼서 그 때마다 충돌확인

*/
class Solution {
    Queue<int[]>[] queues;
    int robotCount;
    int answer = 0;
    
    public int solution(int[][] points, int[][] routes) {
        robotCount = routes.length;
        
        // queue 초기화
        queues = new LinkedList[robotCount];
        for(int i = 0 ; i < robotCount; i++){
            queues[i] = new LinkedList<>();
        }
        
        // 움직임
        move(points, routes);
        
        // 충돌 계산
        findAccident();
        
        return answer;
    }
    
    // 충돌횟수 계산
    void findAccident(){
        int count = 0;
        
        while(count != robotCount){
            count = 0;
            int[][] map = new int[101][101];
            
            // robot 꺼내기
            for(int i = 0 ; i < robotCount; i++){
                
                // 비면 다 꺼낸거
                if(queues[i].isEmpty()){
                    count++;
                    continue;
                }
                
                int[] point = queues[i].poll();
                map[point[0]][point[1]]++;
            }
            
            // 충돌확인
            for(int i = 0; i < 101; i++){
                for(int j = 0; j < 101; j++){
                    if(map[i][j] > 1){
                        answer++;
                    }
                }
            }
        }
    }
    
    // 최단거리 이동
    void move(int[][] points, int[][] routes){
        for(int i = 0; i < robotCount; i++){
            // 시작점
            int[] point = points[routes[i][0] - 1];
            int x = point[0];
            int y = point[1];
            queues[i].add(new int[]{x, y});
            
            // 이동
            for(int j = 1; j < routes[i].length; j++){
                int[] nPoint = points[routes[i][j] - 1];
                int nx = nPoint[0];
                int ny = nPoint[1];
                
                
                // 위/아래 먼저 이동
                int moveX = nx - x;
                while(moveX != 0){
                    // 음수일때 (위로 이동)
                    if(moveX < 0){
                        x--;
                        moveX = nx - x;
                        queues[i].add(new int[]{x, y});
                        continue;
                    }
                    
                    // 양수일때 (아래로 이동)
                    x++;
                    moveX = nx - x;
                    queues[i].add(new int[]{x, y});
                }
                
                // 왼쪽/오른쪽 이동
                int moveY = ny - y;
                while(moveY != 0){
                    // 음수일때 (왼쪽 이동)
                    if(moveY < 0){
                        y--;
                        moveY = ny - y;
                        queues[i].add(new int[]{x, y});
                        continue;
                    }
                    
                    // 양수일때 (오른쪽 이동)
                    y++;
                    moveY = ny - y;
                    queues[i].add(new int[]{x, y});
                }
            }
        }
    }
}